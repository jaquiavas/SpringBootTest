package com.javacourse.mobile_app_ws.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javacourse.mobile_app_ws.UserRepository;
import com.javacourse.mobile_app_ws.io.entity.UserEntity;
import com.javacourse.mobile_app_ws.shared.Utils;
import com.javacourse.mobile_app_ws.shared.dto.UserDto;

@Service
public class UserServiceImpl implements com.javacourse.mobile_app_ws.service.UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Override
    public UserDto createUser(UserDto userDto) {

        UserEntity findUserDetails = userRepository.findByEmail(userDto.getEmail());
        if (findUserDetails != null) {
            throw new RuntimeException("Record already exists");
        }

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);

        String publicUserID = utils.generateUserId(30);
        userEntity.setEncryptedPassword("testPassword");
        userEntity.setUserId(publicUserID);
        UserEntity storedUserDetails = userRepository.save(userEntity);
        

        BeanUtils.copyProperties(storedUserDetails, userDto);
        return userDto;
    }

}
