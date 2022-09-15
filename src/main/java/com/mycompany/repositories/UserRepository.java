package com.mycompany.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mycompany.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	public Long countById(Integer id);
}
