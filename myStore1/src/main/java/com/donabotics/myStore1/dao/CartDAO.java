package com.donabotics.myStore1.dao;

import com.donabotics.myStore1.entity.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartDAO extends CrudRepository<Cart, Integer> {
    void deleteByCustomerIdAndProductId(Integer customer_id, Integer product_id);
   // void removeByCustomerIdAndProductId();
}
