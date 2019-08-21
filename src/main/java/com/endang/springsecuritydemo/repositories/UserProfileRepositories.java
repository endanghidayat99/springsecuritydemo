package com.endang.springsecuritydemo.repositories;

import com.endang.springsecuritydemo.models.UserProfile;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserProfileRepositories extends CrudRepository<UserProfile,Integer> {

    @Query("select c from UserProfile c where c.username = :username")
    UserProfile findByUsername(@Param("username") String username);
}
