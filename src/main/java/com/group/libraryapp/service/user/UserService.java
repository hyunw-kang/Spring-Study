package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(UserCreateRequest request){
        userRepository.saveUser(request.getName(), request.getAge());// 첫파라미터로 sql받고 나머지는 ?값 대신할거

    }
    public List<UserResponse> getUsers(){
        return userRepository.getUsers();
    }
    public void UpdateUser(UserUpdateRequest request){
        if(userRepository.isUserNotExist(request.getId())){ // 현재 user가 있는 없는지 확인하고 예외처리
            throw new IllegalArgumentException();
        }

        userRepository.updateUserName(request.getName(), request.getId());
    }

    public void DeleteUser(String name){
        if(userRepository.isUseotExist(name)){
            throw new IllegalArgumentException();
        }
        userRepository.DeleteUser(name);
    }
}
