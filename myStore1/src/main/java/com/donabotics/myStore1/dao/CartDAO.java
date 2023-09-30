package com.donabotics.myStore1.dao;

import com.donabotics.myStore1.entity.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDAO extends CrudRepository<Cart, Integer> {
}