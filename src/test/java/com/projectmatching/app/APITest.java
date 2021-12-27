package com.projectmatching.app;

import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

@SpringBootTest
public class APITest {


    /**
     * JPA 적용되는 API test방법
     */

    @Autowired
    private TestRestTemplate restTemplate;

    @AfterEach
    public void tearDown() throws Exception{
        //DB 저장된거 삭제
    }

    @Test
    public void Posts_등록된다() throws Exception{
        //given
        //requst

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url,requestDto,Long.class);

        //then

    }

}
