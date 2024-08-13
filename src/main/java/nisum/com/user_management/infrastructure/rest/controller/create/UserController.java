package nisum.com.user_management.infrastructure.rest.controller.create;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import nisum.com.user_management.application.create.UserCreator;
import nisum.com.user_management.infrastructure.rest.request.UserRequest;
import nisum.com.user_management.infrastructure.rest.mapper.UserMapper;
import nisum.com.user_management.infrastructure.rest.response.UserResponse;
import nisum.com.user_management.infrastructure.rest.util.UserRequestUtil;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserCreator userCreator;
    private final UserMapper userMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    @Operation(summary = UserRequestUtil.MS_SWAGGER_SUMMARY, description = UserRequestUtil.MS_SWAGGER_DESCRIPTION)
    public UserResponse createUser(@Valid @RequestBody UserRequest userRequest){
        return userMapper.toResponse(userCreator.create(userMapper.toDomain(userRequest)));
    }

}
