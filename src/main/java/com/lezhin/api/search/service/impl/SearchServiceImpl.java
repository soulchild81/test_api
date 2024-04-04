package com.lezhin.api.search.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lezhin.api.search.feign.ApiClient;
import com.lezhin.api.search.feign.DisplayApiClient;
import com.lezhin.api.search.feign.EsClient;
import com.lezhin.api.search.mapper.SearchMapper;
import com.lezhin.api.search.model.CategoryResponse;
import com.lezhin.api.search.model.EsPutResult;
import com.lezhin.api.search.result.LotteOnResult;
import com.lezhin.api.search.service.SearchService;
import com.lezhin.api.search.result.Result;
import com.lezhin.common.Constants;
import jdk.swing.interop.SwingInterOpUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SearchServiceImpl implements SearchService {

    private final ObjectMapper mapper;
    private final EsClient esClient;
    private final ApiClient apiClient;
    private final DisplayApiClient displayApiClient;

    @Override
    public List<CategoryResponse> getCategory(String depth){
        Result categoryResponse = apiClient.getCategory(depth);
        return categoryResponse.getResult().stream().map(SearchMapper.INSTANCE::toCategoryResponse).toList();
    }

    @Override
    public void searchIndexing(String depth){
        long start = System.currentTimeMillis();
        System.out.println("START :" + start);
        apiClient.getCategory(depth).getResult().forEach(s -> this.getCategoryProduct(s.getDisplay_category_id()));
        long end = System.currentTimeMillis();
        System.out.println("END :" + (end - start));
    }

    @Override
    public void getCategoryProduct(String categoryId){
        System.out.println("#CATEGORY_ID :" + categoryId);
        for(int i=1;i <= Constants.PAGE_LIMIT;i++){
            LotteOnResult result = displayApiClient.getCategoryProduct(categoryId , i , Constants.SIZE);
            if(result.getData() != null && result.getData().getProduct_list()!=null){
                System.out.println("#RESULT : " + i + ":" + result.getData().getProduct_list().size());
                this.indexing(result);
            }else {
                System.out.println("#TOTAL : " + i * 100);
                break;
            }
        }
    }

    @Override
    public void indexing(LotteOnResult result){
        result.getData().getProduct_list().forEach(k -> {
            try {
                String str = mapper.writeValueAsString(k);
                EsPutResult esResult = esClient.setIndexing(k.getPd_id() , str);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        System.out.println("#FINISH :" + result.getData().getProduct_list().size());
    }
}
