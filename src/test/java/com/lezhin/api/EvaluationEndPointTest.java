package com.lezhin.api;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@AutoConfigureRestDocs()
@AutoConfigureMockMvc
@DisplayName("#평가")
@SpringBootTest
public class EvaluationEndPointTest {
    @Autowired
    private MockMvc mockMvc;

    @DisplayName("평가 - 컨텐츠 좋아요 , 싫어요 정보 등록")
    @Test
    public void evaluationLikeAddTest() throws Exception {
        ResultActions result = this.mockMvc.perform(RestDocumentationRequestBuilders.post("/v1/content/evaluation/{type}/{contentSeq}" , "like", 1)).andDo(print()).andExpect(status().isOk());
        result.andExpect(status().isOk()).andDo(document("evaluationLikeAdd",
                        pathParameters(
                                parameterWithName("type").description("type : 좋아요 타입(like , dislike)"),
                                parameterWithName("contentSeq").description("contentSeq : 컨텐츠 번호")
                        )
                )
        ).andDo(print());
    }

    @DisplayName("평가 - 컨텐츠 좋아요 , 싫어요 정보 삭제")
    @Test
    public void evaluationLikeRemoveTest() throws Exception {
        ResultActions result = this.mockMvc.perform(RestDocumentationRequestBuilders.delete("/v1/content/evaluation/{type}/{contentSeq}" , "like", 1)).andDo(print()).andExpect(status().isOk());
        result.andExpect(status().isOk()).andDo(document("evaluationLikeRemove",
                        pathParameters(
                                parameterWithName("type").description("type : 좋아요 타입(like , dislike)"),
                                parameterWithName("contentSeq").description("contentSeq : 컨텐츠 번호")
                        )
                )
        ).andDo(print());
    }

    @DisplayName("평가 - 컨텐츠 댓글 등록")
    @Test
    public void evaluationCommentAddTest() throws Exception {
        ResultActions result = this.mockMvc.perform(RestDocumentationRequestBuilders.post("/v1/content/evaluation/comment/{contentSeq}" , 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"comment\": \"blah~blah\"}"))
                .andDo(print()).andExpect(status().isOk());
        result.andExpect(status().isOk()).andDo(document("evaluationCommentAdd",
                        pathParameters(
                                parameterWithName("contentSeq").description("contentSeq : 컨텐츠 번호 ")
                        )
                )
        ).andDo(print());
    }

    @DisplayName("평가 - 컨텐츠 댓글 삭제")
    @Test
    public void evaluationCommentRemoveTest() throws Exception {
        ResultActions result = this.mockMvc.perform(RestDocumentationRequestBuilders.delete("/v1/content/evaluation/comment/{contentSeq}" , 1)).andDo(print()).andExpect(status().isOk());
        result.andExpect(status().isOk()).andDo(document("evaluationCommentRemove",
                pathParameters(
                        parameterWithName("contentSeq").description("contentSeq : 컨텐츠 번호 ")
                ))
        ).andDo(print());
    }
}
