package com.lezhin.api.search.service;

import com.lezhin.api.search.model.Category;
import com.lezhin.api.search.model.CategoryResponse;
import com.lezhin.api.search.result.LotteOnResult;

import java.util.List;

public interface SearchService {

    List<CategoryResponse> getCategory(String depth);

    void searchIndexing(String depth);

    void getCategoryProduct(String categoryId);

    void indexing(LotteOnResult result);
}
