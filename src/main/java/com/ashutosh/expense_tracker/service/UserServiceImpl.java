package com.ashutosh.expense_tracker.service;

import com.ashutosh.expense_tracker.custom_exceptions.ItemAlreadyExistsException;
import com.ashutosh.expense_tracker.entity.User;
import com.ashutosh.expense_tracker.entity.UserModel;
import com.ashutosh.expense_tracker.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.module.ResolutionException;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public User createUser(UserModel userModel){
        if(userRepository.existsByEmail(userModel.getEmail())){
            throw new ItemAlreadyExistsException("Email already exists");
        }
        User newUser = new User();
        BeanUtils.copyProperties(userModel, newUser);
        userRepository.save(newUser);
        System.out.println(newUser);
        return newUser;
    }

    @Override
    public boolean existsByEmail(String email){
        boolean emailExists = userRepository.existsByEmail(email);
        if(emailExists){
            return true;
        }
        return false;
    }

    @Override
    public User read(Long id){
        return userRepository.findById(id).orElseThrow(() -> new ResolutionException("User does not exist with id "+id));
    }

    @Override
    public User updateUser(User user, Long id){
        User existingUser = read(id);
        System.out.println("--------- INSIDE THE UPDATE USER  --------------");
        System.out.println(user.toString());
        existingUser.setAge(user.getAge() != null ? user.getAge() : existingUser.getAge());
        existingUser.setName(user.getName() != null ? user.getName(): existingUser.getName());
        existingUser.setEmail(user.getEmail() != null ? user.getEmail(): existingUser.getEmail());
        existingUser.setPassword(user.getPassword() != null ? user.getPassword() : existingUser.getPassword());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id){
        userRepository.deleteById(id);
    }

}
