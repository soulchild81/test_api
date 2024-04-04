package com.lezhin.api.evaluation.controller;

import com.lezhin.api.evaluation.model.CommentRequest;
import com.lezhin.api.evaluation.service.EvaluationService;
import com.lezhin.common.Exception.ApiException;
import com.lezhin.common.Exception.CommonErrorCode;
import com.lezhin.common.utils.ApiUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@Tag(name="컨텐츠 평가", description="컨탠츠 평가 API")
@Slf4j
@RequestMapping("/v1/content/evaluation/*")
@RequiredArgsConstructor
@RestController
public class EvaluationController {
    private final EvaluationService evaluationService;

    @Operation(summary = "컨텐츠 좋아요 , 싫어요 정보 등록")
    @PostMapping("/{type}/{contentSeq}")
    public Boolean addContentLike(@Parameter(description = "contentSeq", required = true) @PathVariable(value="contentSeq") long contentSeq,
                                  @Parameter(description = "type", required = true) @PathVariable(value="type") String type){
        if(contentSeq < 1){
            throw new ApiException(CommonErrorCode.COMMON_ILLEGAL_PARAM);
        }

        boolean result = true;
        if(type.equals("like")){
            result = evaluationService.addContentLike(1 , contentSeq);
        }else if(type.equals("dislike")){
            result = evaluationService.addContentDisLike(1 , contentSeq);
        }
        return result;
    }

    @Operation(summary = "컨텐츠 좋아요 , 싫어요 정보 삭제")
    @DeleteMapping("/{type}/{contentSeq}")
    public Boolean removeContentLike(@Parameter(description = "contentSeq", required = true) @PathVariable(value="contentSeq") long contentSeq,
                                     @Parameter(description = "type", required = true) @PathVariable(value="type") String type) {
        if(contentSeq < 1){
            throw new ApiException(CommonErrorCode.COMMON_ILLEGAL_PARAM);
        }

        // TO-DO session 조회 또는 유저정보를 조회해서 유저의 정보를 가져왔다고 가정 하고 1로 넣는다.

        boolean result = true;
        if(type.equals("like")){
            result = evaluationService.removeContentLike(1 , contentSeq);
        }else if(type.equals("dislike")){
            result = evaluationService.removeContentDisLike(1 , contentSeq);
        }
        return result;
    }

    @Operation(summary = "컨텐츠 댓글 등록")
    @PostMapping("/comment/{contentSeq}")
    public Boolean addContentComment(@RequestBody CommentRequest commentRequest ,
                                     @Parameter(description = "contentSeq", required = true) @PathVariable(value="contentSeq") long contentSeq) {
        if(contentSeq < 1){
            throw new ApiException(CommonErrorCode.COMMON_ILLEGAL_PARAM);
        }

        if(commentRequest.getComment() == null){
            throw new ApiException(CommonErrorCode.COMMON_ILLEGAL_PARAM);
        }

        // pseudocode User 정보 조회 : auth , session 1 로 가정
        String comment = ApiUtils.removeSpecialChar(commentRequest.getComment());
        return evaluationService.addContentComment(1 , contentSeq , comment);
    }

    @Operation(summary = "컨텐츠 댓글 삭제")
    @DeleteMapping("/comment/{contentSeq}")
    public Boolean removeContentComment(@Parameter(description = "contentSeq", required = true) @PathVariable(value="contentSeq") long contentSeq) {
        if(contentSeq < 1){
            throw new ApiException(CommonErrorCode.COMMON_ILLEGAL_PARAM);
        }
        return evaluationService.removeContentComment(1 , contentSeq);
    }
}
