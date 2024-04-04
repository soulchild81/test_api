package com.lezhin.api.search.controller;

import com.lezhin.api.search.model.Category;
import com.lezhin.api.search.model.CategoryResponse;
import com.lezhin.api.search.service.SearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name="ES 데이터 적재", description="상품 데이터적재 API")
@Slf4j
@RequestMapping("/v1/search/*")
@RequiredArgsConstructor
@RestController
public class SearchController {
    private final SearchService searchService;

    @Operation(summary = "카테고리 조회")
    @GetMapping("/category")
    public List<CategoryResponse> getCategory(@Parameter(description = "depth", required = true) @RequestParam(value="depth" , defaultValue="1") String depth){
        return searchService.getCategory(depth);
    }

    @Operation(summary = "상품 데이터 적재")
    @GetMapping("/product/indexing")
    public void setSearchIndexing(@Parameter(description = "depth", required = true) @RequestParam(value="depth" , defaultValue="1") String depth){
        searchService.searchIndexing(depth);
    }
}
