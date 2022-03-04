package com.projectmatching.app.controller.user;

import com.projectmatching.app.controller.ControllerTest;
import com.projectmatching.app.domain.user.QUserRepository;
import com.projectmatching.app.domain.user.UserRepository;
import com.projectmatching.app.service.user.UserService;
import com.projectmatching.app.service.user.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class UserController extends ControllerTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private QUserRepository qUserRepository;


    private UserService userService;


    @BeforeTestClass
    public void setup(){
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(qUserRepository);
    }



    @DisplayName("유저 회원가입 요청 테스트")
    @Test
    void UserJoinTest() throws Exception {

        mockMvc.perform(post("/user/join")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(HttpHeaders.EMPTY)
//                .content()
                )
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("code").value("200"))
                .andExpect(jsonPath("data").value(true));


    }

}
