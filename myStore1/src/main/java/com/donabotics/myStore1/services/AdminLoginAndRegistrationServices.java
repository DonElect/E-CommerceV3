package com.donabotics.myStore1.services;

import com.donabotics.myStore1.entity.Admin;

public interface AdminLoginAndRegistrationServices {
    boolean verifyLogin(Admin admin);
    Admin addNewAdmin(Admin admin);
}
