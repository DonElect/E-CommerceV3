package com.donabotics.myStore1.repository;

import com.donabotics.myStore1.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    void deleteByCustomerIdAndProductId(Integer customer_id, Integer product_id);
   // void removeByCustomerIdAndProductId();
}
