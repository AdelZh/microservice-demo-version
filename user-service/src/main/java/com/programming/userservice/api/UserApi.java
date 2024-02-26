package com.programming.userservice.api;
import com.programming.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserApi {

    private final UserService userService;

    @GetMapping("/get")
    public Long getUser(@RequestParam Long userId) {
        return userService.getUserById(userId);
    }

}

