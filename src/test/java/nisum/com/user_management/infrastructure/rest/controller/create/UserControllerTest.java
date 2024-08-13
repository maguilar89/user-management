package nisum.com.user_management.infrastructure.rest.controller.create;

import nisum.com.user_management.application.create.UserCreator;
import nisum.com.user_management.domain.User;
import nisum.com.user_management.infrastructure.rest.error.ControllerAdvisor;
import nisum.com.user_management.infrastructure.rest.mapper.UserMapper;
import nisum.com.user_management.infrastructure.rest.request.UserRequest;
import nisum.com.user_management.infrastructure.rest.util.JsonObjectUtil;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    private static final String USER_PATH = "/api/v1/users";
    @Mock
    private UserCreator userCreator;
    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setControllerAdvice(new ControllerAdvisor()).build();
    }

    @Test
    void whenValidateUser_GivenValidRequestBody_ThenResponseIsCreated() throws Exception {
        EasyRandom easyRandom = new EasyRandom();
        UserRequest userRequest =  UserRequest
                .builder()
                .name("Manuel")
                .password("Channel12*")
                .email("example@example.ex")
                .build();

        when(userCreator.create(any())).thenReturn(
                easyRandom.nextObject(
                        User.class));

        this.mockMvc.perform(post(USER_PATH).content(
                        JsonObjectUtil.asJsonString(userRequest)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isCreated());
    }

    @Test
    void whenValidateUser_GivenRequestBodyWithWrongEmail_ThenResponseIsBadRequest() throws Exception {
        UserRequest userRequest =  UserRequest
                .builder()
                .name("Manuel")
                .password("Channel12*")
                .email("example")
                .build();

        this.mockMvc.perform(post(USER_PATH).content(
                        JsonObjectUtil.asJsonString(userRequest)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    void whenValidateUser_GivenRequestBodyWithWrongPassword_ThenResponseIsBadRequest() throws Exception {
        UserRequest userRequest =  UserRequest
                .builder()
                .name("Manuel")
                .password("Chann")
                .email("example@example.ex")
                .build();

        this.mockMvc.perform(post(USER_PATH).content(
                        JsonObjectUtil.asJsonString(userRequest)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().isBadRequest());
    }

    @Test
    void whenValidateUser_GivenNotValidRequestBody_ThenReturnsBadRequestException()
            throws Exception {
        UserRequest userRequest =  UserRequest
                .builder()
                .name("Manuel")
                .password("example@example.ex")
                .email("example@example.ex")
                .build();

        this.mockMvc.perform(post(USER_PATH).content(
                        JsonObjectUtil.asJsonString(userRequest)).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)).andExpect(status().is4xxClientError());
    }

}
