package com.lezhin.api;


import jakarta.annotation.Resource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@AutoConfigureRestDocs()
@AutoConfigureMockMvc
@DisplayName("#컨텐츠")
@SpringBootTest
public class ContentEndPointTest {
    @Resource
    private MockMvc mockMvc;

//     @DisplayName("컨텐츠 - 컨텐츠 개별 조회")
//     @Test
//     public void contentDetailTest() throws Exception {
//         ResultActions result = this.mockMvc.perform(RestDocumentationRequestBuilders.get("/v1/content/{contentSeq}" , 1)).andDo(print()).andExpect(status().isOk());
//         result.andExpect(status().isOk()).andDo(document("contentDetail",
//                         pathParameters(
//                                 parameterWithName("contentSeq").description("contentSeq : 컨텐츠 번호")
//                         ),
//                         responseFields(
//                                 beneathPath("data") ,
//                                 fieldWithPath("contentSeq").type(JsonFieldType.NUMBER).description("컨텐츠 번호"),
//                                 fieldWithPath("contentsName").type(JsonFieldType.STRING).description("컨텐츠 명"),
//                                 fieldWithPath("author").type(JsonFieldType.STRING).description("작가"),
//                                 fieldWithPath("coin").type(JsonFieldType.NUMBER).description("가격"),
//                                 fieldWithPath("openDate").type(JsonFieldType.STRING).description("컨텐츠 오픈일"),
//                                 fieldWithPath("likeCount").type(JsonFieldType.NUMBER).description("좋아요"),
//                                 fieldWithPath("disLikeCount").type(JsonFieldType.NUMBER).description("싫어요")
//                         )
//                 )
//         ).andDo(print());
//     }

//     @DisplayName("컨텐츠 - 좋아요 / 싫어요 컨텐츠 상위 3개 랭킹 조회 API")
//     @Test
//     public void likeRankTest() throws Exception {
//         ResultActions result = this.mockMvc.perform(get("/v1/content/rank")
//                         .param("size" , "3")
//                         .contentType("application/json; charset=utf-8"))
//                 .andDo(print())
//                 .andExpect(status().isOk());

//         result.andExpect(status().isOk()).andDo(document("likeRank",
//                         queryParameters(
//                             parameterWithName("size").description("상위 n 개 : default 3")
//                         ),
//                         responseFields(
//                                 beneathPath("data") ,
//                                 fieldWithPath("likeRanking.[]contentSeq").type(JsonFieldType.NUMBER).description("좋아요 컨텐츠 번호"),
//                                 fieldWithPath("likeRanking.[]contentsName").type(JsonFieldType.STRING).description("좋아요 컨텐츠 명"),
//                                 fieldWithPath("likeRanking.[]author").type(JsonFieldType.STRING).description("좋아요 작가"),
//                                 fieldWithPath("likeRanking.[]coin").type(JsonFieldType.NUMBER).description("좋아요 가격"),
//                                 fieldWithPath("likeRanking.[]openDate").type(JsonFieldType.STRING).description("좋아요 컨텐츠 오픈일"),
//                                 fieldWithPath("likeRanking.[]likeCount").type(JsonFieldType.NUMBER).description("좋아요"),
//                                 fieldWithPath("likeRanking.[]disLikeCount").type(JsonFieldType.NUMBER).description("싫어요"),

//                                 fieldWithPath("disLikeRanking.[]contentSeq").type(JsonFieldType.NUMBER).description("싫어요 컨텐츠 번호"),
//                                 fieldWithPath("disLikeRanking.[]contentsName").type(JsonFieldType.STRING).description("싫어요 컨텐츠 명"),
//                                 fieldWithPath("disLikeRanking.[]author").type(JsonFieldType.STRING).description("싫어요 작가"),
//                                 fieldWithPath("disLikeRanking.[]coin").type(JsonFieldType.NUMBER).description("싫어요 가격"),
//                                 fieldWithPath("disLikeRanking.[]openDate").type(JsonFieldType.STRING).description("싫어요 컨텐츠 오픈일"),
//                                 fieldWithPath("disLikeRanking.[]likeCount").type(JsonFieldType.NUMBER).description("좋아요"),
//                                 fieldWithPath("disLikeRanking.[]disLikeCount").type(JsonFieldType.NUMBER).description("싫어요")
//                         )
//                 )
//         ).andDo(print());
//     }

//     @DisplayName("컨텐츠 - 컨텐츠 별 이력 조회")
//     @Test
//     public void contentViewerTest() throws Exception {
//         ResultActions result = this.mockMvc.perform(RestDocumentationRequestBuilders.get("/v1/content/{contentSeq}/history" , 1)
//                                     .param("start" , "0")
//                                     .param("length" , "10")
//                                     .contentType("application/json; charset=utf-8"))
//                             .andDo(print()).andExpect(status().isOk());
//         result.andExpect(status().isOk()).andDo(document("contentViewer",
//                         pathParameters(
//                                 parameterWithName("contentSeq").description("contentSeq : 컨텐츠 번호")
//                         ),
//                         queryParameters(
//                                 parameterWithName("start").description("시작시점 : default 0"),
//                                 parameterWithName("length").description("가져올 사이즈 : default 10")
//                         ),
//                         responseFields(
//                                 beneathPath("data") ,
//                                 fieldWithPath("contentSeq").type(JsonFieldType.NUMBER).description("컨텐츠 번호"),
//                                 fieldWithPath("userSeq").type(JsonFieldType.NUMBER).description("유저 번호"),
//                                 fieldWithPath("createdDate").type(JsonFieldType.STRING).description("생성시간"),
//                                 fieldWithPath("modifiedDate").type(JsonFieldType.STRING).description("수정시간")
//                         )
//                 )
//         ).andDo(print());
//     }

//     @DisplayName("컨텐츠 - 컨텐츠 유료 / 무료 정보 변경")
//     @Test
//     public void updateContentsPaymentTest() throws Exception {
//         ResultActions result = this.mockMvc.perform(put("/v1/content/payment" , 1)
//                         .contentType(MediaType.APPLICATION_JSON)
//                         .content("{\"paymentType\": \"FREE\", \"amount\": 0 , \"contentSeq\": 1}"))
//                 .andDo(print()).andExpect(status().isOk());

//         result.andExpect(status().isOk()).andDo(document("updateContentsPayment")).andDo(print());
//     }
}
