package com.abc.todo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abc.todo.model.User;




public interface UserRepo extends JpaRepository<User, Long> {

	Optional<User> findByUserName(String userName);

}
