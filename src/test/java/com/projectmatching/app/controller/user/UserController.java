package com.projectmatching.app.controller.user;

import com.projectmatching.app.controller.ControllerTest;
import com.projectmatching.app.domain.user.QUserRepository;
import com.projectmatching.app.domain.user.UserRepository;
import com.projectmatching.app.service.user.UserServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
public class UserController extends ControllerTest {

    @InjectMocks
    private UserServiceImpl userService;

    @MockBean
    private QUserRepository  qUserRepository;

    @MockBean
    private UserRepository userRepository;

    @DisplayName("유저 회원가입 요청 테스트")
    @Test
    void UserJoinTest() throws Exception {

        mockMvc.perform(get("/user/join")
                .contentType(MediaType.APPLICATION_JSON)
                .headers(HttpHeaders.EMPTY))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("code").value("200"))
                .andExpect(jsonPath("data").value(true));


    }

}
