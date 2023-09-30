package com.donabotics.myStore1.dao;

import com.donabotics.myStore1.entity.Admin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminDAO extends CrudRepository<Admin, Integer> {
}
