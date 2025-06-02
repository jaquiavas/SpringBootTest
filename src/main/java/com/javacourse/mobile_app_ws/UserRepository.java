package com.javacourse.mobile_app_ws;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.javacourse.mobile_app_ws.io.entity.UserEntity;


@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
}
