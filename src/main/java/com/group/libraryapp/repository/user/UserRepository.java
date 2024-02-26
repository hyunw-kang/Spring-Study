package com.group.libraryapp.repository.user;

import com.group.libraryapp.dto.user.response.UserResponse;
import org.aspectj.weaver.loadtime.Agent;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean isUserNotExist(long id){
        String readSql = "SELECT * FROM user WHERE id= ?"; // sql사용하여 db와 통신
        return jdbcTemplate.query(readSql, (rs,rowNum) -> 0, id).isEmpty(); //전체가 list /비어있다면?
    }
    public void updateUserName(String name,long id){
        String sql = "Update user SET name = ? WHERE id = ?"; //sql을 사용하여 database와 통신
        jdbcTemplate.update(sql, name, id);
    }
    public boolean isUseotExist(String name){
        String readSql = "SELECT * FROM user WHERE name= ?";
        return jdbcTemplate.query(readSql, (rs,rowNum) -> 0, name).isEmpty(); //전체가 list /비어있다면?
    }
    public void DeleteUser(String name){
        String sql = "DELETE FROM user WHERE name =?";
        jdbcTemplate.update(sql, name);
    }
    public void saveUser(String name,Integer age){
        String sql = "INSERT INTO user (name, age) VALUES(?, ?)"; //이름이랑 나이는 그때마다 다르기때문(유동적)
        jdbcTemplate.update(sql,name, age); // 첫파라미터로 sql받고 나머지는 ?값 대신할거

    }
    public List<UserResponse> getUsers(){
        String sql = "SELECT * FROM user";
        return jdbcTemplate.query(sql, new RowMapper<UserResponse>(){
            @Override
            public UserResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
                long id = rs.getLong("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                return new UserResponse(id, name, age);
            }
        });
    }


}
