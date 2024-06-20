# * 벡엔드

## - 컨텐츠 API 개발 내용
    1. 컨텐츠 조회 ,컨텐츠 평가(좋아요 ,싫어요, 댓글) , 유저 조회 등의 API 를 작성

    2. 메모리 DB 인 H2 를 로컬 DB 로 사용 , 어플리케이션 구동될때 DB 생성 및 테스트 데이터 삽입
       삽입된 데이터를 기준으로 조회 API 수행
       src/main/java/com/lezhin/common/data/InitializeData.java 파일에 코드 작성

    3. JPA 를 사용하여 DB Entity 생성 및 데이터 조작 

    4. Http Method 와 URI 를 결합RESTFUL 한 API 구현

    5. 엔드투엔드 테스트를 통하여 restdocs 문서 빌드 추가 (src/test/java/com/lezhin/api/*) 
       빌드를 통하여 API 레퍼런스 문서 api-doc.html 문서 생성  
       API 의 스펙은 restdocs 참조 (http://localhost:8080/docs/api_doc.html)

    6. 유저정보의 조회가 필요한 부분은 session 조회 또는 Oauth 서버를 조회해서 유저의 정보를 가져왔다고 가정 하고 유저Seq는 1로 넣는다.

    7. @RestControllerAdvice 를 사용하여 공통 응답 처리 및 Exception 처리
        -- 공통 응답 처리 
        {
        "code": "SUCCESS",
        "message": "This response is successful",
        "timestamp": "2024-02-07T21:01:24.482+0900",
        "data": {
                    "contentSeq": 1,
                    "contentsName": "원피스",
                    "author": "오다",
                    "coin": 800,
                    "openDate": "2024-02-07T21:01:16.107725",
                    "likeCount": 0,
                    "disLikeCount": 0
                }
        }

        -- Exception 처리 
        {
            "code": "COMMON_ILLEGAL_PARAM",
            "message": "지원하지 않는 파라미터 형식입니다.",
            "timestamp": "2024-02-07T21:02:30.507+0900",
            "status": 404,
            "error": "NOT_FOUND"
        }
        

    8. mapstruct 를 통하여 Entity 영역과 Dto 영역을 구분하여 객체간의 매핑을 자동화 및 효율적으로 처리 
       레이어의 분리로 유지보수 용이성 확대 (맵핑규칙을 한곳에 일원화 Mapper 파일 참조) 
    
    9. 테스트 코드는 특별한 로직이 있는 API 가 없어서 작성하지 않음

    10. Controller 에서 요청을 받고 받은 파라미터로 Service 호출 
        Service 레이어에서는 전달받은 파라미터로 Reporitory 호출
        Repository 에서 데이터 조회 수행 및 리턴 
        Service 레이어 에서 Entity --> Dto 변환 하여 Controller 로 리턴 하여 클라이언트에 전달

## - 테이블
![](https://github.com/soulchild81/document/assets/54564644/ea7679b2-7c53-4085-9398-fd84403cb5ef)
    content : 작품 메타 정보 테이블 
    content_commnet : 작품 댓글 테이블 
    content_viewer : 작품 조회 이력 테이블 
    content_like : 작품 좋아요 정보 테이블 
    content_dis_kike : 작품 싫어요 정보 테이블 
    user_info : 유저 정보 테이블 

## - Application Spec
    SpringBoot 3.2.2
    SpringRestDocs 3.0.1
    Swagger3
    jdk17(Amazon Corretto 17 https://docs.aws.amazon.com/corretto/latest/corretto-17-ug/downloads-list.html)
    Lombok JPA H2 
    mapstruct 

## - 개발 관련 URL (어플리케이션 구동후 확인가능)
    1. restdocs 주소 : http://localhost:8080/docs/api_doc.html
   ![](https://github.com/soulchild81/document/assets/54564644/0b4367ad-e40e-47e9-b04e-74b39a3b5b59)

    2. swagger 주소 : http://localhost:8080/swagger-ui/index.html
   ![](https://github.com/soulchild81/document/assets/54564644/57b421b0-d102-4e98-a89f-a4b617dd011d)

    3. H2 웹콘솔 주소 : http://localhost:8080/h2-console    id:test pw: tes t
   ![](https://github.com/soulchild81/document/assets/54564644/ea7679b2-7c53-4085-9398-fd84403cb5ef)

## - 빌드
    압축을 풀고 IDE 상에 임포트 하여 빌드 한다.

## - 수행
    1. 전달된 jar 파일을 수행
    - java -jar lezhin_api-0.0.1-SNAPSHOT.jar 수행 (로컬환경에 jdk 17 이상 버전 있어야함 java -version 으로 확인)
   ![](https://github.com/soulchild81/document/assets/54564644/c3d4fb62-7c35-4e99-911b-7122dbebfadf)

    2. http://localhost:8080/swagger-ui/index.html 스웨거 접속후 API Test
   ![](https://github.com/soulchild81/document/assets/54564644/57b421b0-d102-4e98-a89f-a4b617dd011d)

    3. lezhin_api.http 파일 을 열고 Run 수행 , Run 아이콘 수행
   ![](https://github.com/soulchild81/document/assets/54564644/e97660dd-8434-41eb-9876-2e42f5b0bb35)

