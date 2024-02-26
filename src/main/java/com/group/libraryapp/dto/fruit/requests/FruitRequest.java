package com.group.libraryapp.dto.fruit.requests;
import java.time.LocalDate;
public class FruitRequest {
    private String name;
    private LocalDate warehousingDate;
    private int price;
    public String getName() {
        return name;
    }

    public LocalDate getWarehousingDate() {
        return warehousingDate;
    }
    public int getPrice() {
        return price;
    }
}
