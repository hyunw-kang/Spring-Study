package com.group.libraryapp.dto.fruit.requests;
public class FruitPriceState {
    private long saleprice;
    private long notsaleprice;
    public FruitPriceState(long saleprice, long notsaleprice) {
        this.saleprice = saleprice;
        this.notsaleprice = notsaleprice;
    }
    public long getSaleprice() {
        return saleprice;
    }
    public long getNotsaleprice() {
        return notsaleprice;
    }
}
