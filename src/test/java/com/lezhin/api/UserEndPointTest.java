package com.lezhin.api;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;


import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;




@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@AutoConfigureRestDocs()
@AutoConfigureMockMvc
@DisplayName("#유저")
@SpringBootTest
class UserEndPointTest {
    @Resource
    private MockMvc mockMvc;

    @DisplayName("유저 - 유저 개별 조회")
    @Test
    public void userDetailTest() throws Exception {
        ResultActions result = this.mockMvc.perform(RestDocumentationRequestBuilders.get("/v1/user/{userSeq}" , 1)).andDo(print()).andExpect(status().isOk());
        result.andExpect(status().isOk()).andDo(document("userDetail",
                        pathParameters(
                                parameterWithName("userSeq").description("userSeq : 유저 번호")
                        ),
                        responseFields(
                                beneathPath("data") ,
                                fieldWithPath("userSeq").type(JsonFieldType.NUMBER).description("유저  번호"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("유저 이름"),
                                fieldWithPath("email").type(JsonFieldType.STRING).description("유저 이메일"),
                                fieldWithPath("gender").type(JsonFieldType.STRING).description("성별 : M(MALE) 남성, F(FEMALE) 여성"),
                                fieldWithPath("type").type(JsonFieldType.STRING).description("유저 타입 : ADULT(성인) , NORMAL(미성년자)")
                        )
                )
        ).andDo(print());
    }

    @DisplayName("유저 - 유저 정보 삭제")
    @Test
    public void userRemoveTest() throws Exception {
        ResultActions result = this.mockMvc.perform(RestDocumentationRequestBuilders.delete("/v1/user/{userSeq}" , 1)).andDo(print()).andExpect(status().isOk());
        result.andExpect(status().isOk()).andDo(document("userRemove",
                        pathParameters(
                                parameterWithName("userSeq").description("userSeq : 유저 번호")
                        )
                )
        ).andDo(print());
    }

    @DisplayName("유저 - 최근 {n} 일간 성인 작품을 3개이상 조회한 유저 리스트 조회")
    @Test
    public void adultSearchUserTest() throws Exception {
        ResultActions result = this.mockMvc.perform(RestDocumentationRequestBuilders.get("/v1/user/adult/content")
                        .param("days" , "7")
                        .contentType("application/json; charset=utf-8"))
                .andDo(print())
                .andExpect(status().isOk());

        result.andExpect(status().isOk()).andDo(document("adultUserSearch",
                        queryParameters(
                                parameterWithName("days").description("기간 : default 7일")
                        ),
                        responseFields(
                                beneathPath("data") ,
                                fieldWithPath("userSeq").type(JsonFieldType.NUMBER).description("유저 Seq"),
                                fieldWithPath("name").type(JsonFieldType.STRING).description("유저명"),
                                fieldWithPath("email").type(JsonFieldType.STRING).description("이메일"),
                                fieldWithPath("gender").type(JsonFieldType.STRING).description("성별"),
                                fieldWithPath("type").type(JsonFieldType.STRING).description("유저 타입"),
                                fieldWithPath("searchCnt").type(JsonFieldType.NUMBER).description("조회 수")
                        )
                )
        ).andDo(print());
    }
}

