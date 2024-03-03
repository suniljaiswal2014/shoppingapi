package com.wheatroot.shoppingapi.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.wheatroot.shoppingapi.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	List<User> findAll();
}
