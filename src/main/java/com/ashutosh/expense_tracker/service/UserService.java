package com.ashutosh.expense_tracker.service;

import com.ashutosh.expense_tracker.entity.User;
import com.ashutosh.expense_tracker.entity.UserModel;

public interface UserService {
    User createUser(UserModel userModel);
    boolean existsByEmail(String email);

    User read(Long id);
    User updateUser(User user, Long id);
    void deleteUser(Long id);
}
