package com.lezhin.common.data;

import com.lezhin.api.content.repository.ContentRepository;
import com.lezhin.api.evaluation.repository.EvaluationDisLikeRepository;
import com.lezhin.api.evaluation.repository.EvaluationLikeRepository;
import com.lezhin.api.user.repository.ContentViewerRepository;
import com.lezhin.api.user.repository.UserRepository;
import com.lezhin.entity.*;
import com.lezhin.entity.entityKey.ContentUserKey;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 초기 데이터 콜드 스타트를 방지하기 위해 테스트 데이터 삽입
 */
@Component
@RequiredArgsConstructor
public class InitializeData {
    private final ContentRepository contentRepository;
    private final UserRepository userRepository;
    private final EvaluationLikeRepository evaluationLikeRepository;
    private final EvaluationDisLikeRepository evaluationDisLikeRepository;
    private final ContentViewerRepository contentViewerRepository;

    private static long generateRandom(int num) {
        Random random = new Random();
        return random.nextInt(num) + 1;
    }

    @PostConstruct
    public void init(){
            System.out.println("################ Initialize DATA SET");

            String[] array1 = {"1", "원피스","오다","800"};
            String[] array2 = {"2", "베르세르크","미우라켄타로","300"};
            String[] array3 = {"3", "나루토","정우성","300"};
            String[] array4 = {"4", "블리치","황정민","500"};
            String[] array5 = {"5", "코난","허영만","100"};
            String[] array6 = {"6", "크로우즈","오구리슌","400"};
            String[] array7 = {"7", "슬램덩크","다케히코 이노우에","800"};
            String[] array8 = {"8", "드래곤볼","도리야마 아키라","700"};
            String[] array9 = {"9", "더파이팅","정찬성","700"};
            String[] array10= {"10","워스트","오구리슌","600"};
            List<String[]> lines = new ArrayList<>();
            lines.add(array1);
            lines.add(array2);
            lines.add(array3);
            lines.add(array4);
            lines.add(array5);
            lines.add(array6);
            lines.add(array7);
            lines.add(array8);
            lines.add(array9);
            lines.add(array10);

            lines.forEach(line -> {
                System.out.println(line[0]+":"+line[1]+":"+line[2]);
                String adult = generateRandom(2) == 1?"Y":"N";
                Content content = Content.builder()
                        .contentsName(line[1])
                        .author(line[2])
                        .adultContent(adult)
                        .openDate(LocalDateTime.now())
                        .coin(new BigDecimal(line[3]))
                        .build();

                contentRepository.save(content);

                ContentUserKey key = ContentUserKey.builder().contentSeq(generateRandom(10)).userSeq(Long.valueOf(line[0])).build();
                ContentLike like = ContentLike.builder().likeKey(key).build();
                evaluationLikeRepository.save(like);

                ContentUserKey disLikekey = ContentUserKey.builder().contentSeq(generateRandom(10)).userSeq(Long.valueOf(line[0])).build();
                ContentDisLike dislike = ContentDisLike.builder().disLikeKey(disLikekey).build();
                evaluationDisLikeRepository.save(dislike);

            });

            for(int i=0; i < 10; i++){
                String type = generateRandom(2) == 1?"NORMAL":"ADULT";
                UserInfo info = UserInfo.builder().userName("구종옥" + "00" + i).userEmail("soulchild81@naver.com").gender("M").type(type).build();
                userRepository.save(info);
            }

            for(int i=0; i < 100; i++){
                UserInfo info = userRepository.findByUserSeq(generateRandom(10));
                if(info != null){
                    ContentUserKey key = ContentUserKey.builder().userSeq(info.getUserSeq()).contentSeq(generateRandom(10)).build();
                    ContentViewer viewer  = ContentViewer.builder().viewerKey(key).build();
                    ContentViewer isView = contentViewerRepository.findContentViewerByViewerKey(key);
                    if(isView == null){
                        contentViewerRepository.save(viewer);
                    }
                }
            }

            System.out.println("################ DATA SET FINISH");
    }
}
