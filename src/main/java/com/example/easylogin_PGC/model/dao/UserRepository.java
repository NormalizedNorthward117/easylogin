package com.example.easylogin_PGC.model.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.easylogin_PGC.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	//findByUserNameAndPasswordメソッド
	List<User> findByUserNameAndPassword(String userName, String password);
}