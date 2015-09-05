package com.hellokoding.account.service;

import com.hellokoding.account.model.User;
import com.hellokoding.account.repository.RoleRepository;
import com.hellokoding.account.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public void save(User user) {
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }
}
