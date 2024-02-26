package com.group.libraryapp.controller.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    //new UserController(jdbcTemplate); // 클래스정의를 안했는데 controller에서 jdbcTemplate을 어떻게 가져온걸까?
    //Repository에서 jdbcTemplate을 바로 가져올수는 없을까
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/user") //post /user
    public void saveUser(@RequestBody UserCreateRequest request){
        userService.saveUser(request);

    }
    @GetMapping("/user")
    public List<UserResponse> getUsers(){
        return userService.getUsers();

    }
    @PutMapping("/user") // api의 진입지점으로써 HTTP Body를 객체로 변환
    public void updateUser(@RequestBody UserUpdateRequest request){
        userService.UpdateUser(request);

    }
    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name){
        userService.DeleteUser(name);

    }

}

