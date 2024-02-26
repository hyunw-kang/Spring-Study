package com.group.libraryapp.controller.fruits;

import com.group.libraryapp.dto.fruit.requests.FruitPriceState;
import com.group.libraryapp.dto.fruit.requests.FruitRequest;
import com.group.libraryapp.dto.fruit.requests.FruitUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController
public class FruitsController {
    private final JdbcTemplate jdbcTemplate;

    public FruitsController(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }
    @PostMapping("/api/v1/fruits")
    public void createfruits(@RequestBody FruitRequest request){
        String sql = "insert into fruits (name, warehousingDate, price) Values(? , ? , ?)";
        jdbcTemplate.update(sql, request.getName(), request.getWarehousingDate(), request.getPrice());

    }
    @PutMapping("/api/v1/fruits")
    public void salefruits(@RequestBody FruitUpdateRequest request){
        String salesql = "select * from fruits where id =?";
        boolean isFruitsNotExist = jdbcTemplate.query(salesql, (rs, rowNum) ->0, request.getId()).isEmpty();
        if(isFruitsNotExist){
            throw new IllegalArgumentException("없는 과일입니다.");
        }
        String sql = "Update fruits set isSale = 1 where id =?";
        jdbcTemplate.update(sql, request.getId());
    }
    @GetMapping("/api/v1/fruits/stat")
    public FruitPriceState getstate(@RequestParam String name){
        String salesql = "select sum(price) from fruits where isSale = 1 group by having name =?";
        String notsalesql = "select sum(price) from fruits where isSale = 0 group by having name =?";

        long saleprice = jdbcTemplate.queryForObject(salesql, long.class, name);
        long notsaleprice = jdbcTemplate.queryForObject(notsalesql,long.class, name);
        return new FruitPriceState(saleprice, notsaleprice);
    }

}
