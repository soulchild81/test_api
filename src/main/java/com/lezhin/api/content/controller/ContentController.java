package com.lezhin.api.content.controller;

import com.lezhin.api.content.model.ContentResponse;
import com.lezhin.api.content.model.PaymentUpdateRequest;
import com.lezhin.api.content.service.ContentService;
import com.lezhin.api.evaluation.model.LikeRankResponse;
import com.lezhin.api.evaluation.service.EvaluationService;
import com.lezhin.api.user.model.ContentViewerResponse;
import com.lezhin.api.user.service.UserService;
import com.lezhin.common.Exception.ApiException;
import com.lezhin.common.Exception.CommonErrorCode;
import com.lezhin.entity.Content;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name="컨텐츠 정보", description="컨탠츠 정보 API")
@Slf4j
@RequestMapping("/v1/content/*")
@RequiredArgsConstructor
@RestController
public class ContentController {
    private final ContentService contentService;
    private final EvaluationService evaluationService;
    private final UserService userService;

    @Operation(summary = "컨텐츠 정보 상세 조회")
    @GetMapping("/{contentSeq}")
    public ContentResponse getContents(@Parameter(description = "contentSeq", required = true) @PathVariable(value = "contentSeq") long contentSeq){
        if(contentSeq < 1){
            throw new ApiException(CommonErrorCode.COMMON_ILLEGAL_PARAM);
        }
        return contentService.getContent(contentSeq);
    }

    @Operation(summary = "컨텐츠 유료 / 무료 정보 변경")
    @PutMapping("/payment")
    public Content updateContentsPayment(@Parameter(description = "size", required = true) @RequestBody PaymentUpdateRequest request) {
        if(request == null){
            throw new ApiException(CommonErrorCode.COMMON_BAD_REQUEST);
        }
        if(request.getContentSeq() < 1){
            throw new ApiException(CommonErrorCode.COMMON_ILLEGAL_PARAM);
        }
        return contentService.updateContentPayment(request);
    }

    @Operation(summary ="좋아요 / 싫어요 컨텐츠 상위 3개 랭킹 조회 API ")
    @GetMapping("/rank")
    public LikeRankResponse getLikeRank(@Parameter(description = "size", required = true) @RequestParam(value = "size" , defaultValue="3") int size){
        return evaluationService.getRankList(size);
    }

    @Operation(summary ="컨텐츠 별 이력 조회 API ")
    @GetMapping("/{contentSeq}/history")
    public List<ContentViewerResponse> getHistory(@Parameter(description = "contentSeq", required = true) @PathVariable(value = "contentSeq") long contentSeq ,
                                                  @Parameter(description = "start", required = true) @RequestParam(value = "start")  int start ,
                                                  @Parameter(description = "length", required = true) @RequestParam(value = "length")  int length){
        return userService.getContentViewList(contentSeq , start , length);
    }
}
