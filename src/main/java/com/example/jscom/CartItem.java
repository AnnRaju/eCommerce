package com.example.jscom;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private Long userId;

    public void setProductId(Long id){
        this.productId=id;
    }

    public Long getProductId()
    {
        return this.productId;
    }

    public void setUserId(Long id){
        this.userId=id;
    }

    public Long getUserId()
    {
        return this.userId;
    }
    public void setProductName(String productName) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setProductName'");
    }

}
