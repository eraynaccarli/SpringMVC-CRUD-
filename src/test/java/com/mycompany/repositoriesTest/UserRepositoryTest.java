package com.mycompany.repositoriesTest;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.mycompany.entities.User;
import com.mycompany.repositories.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

	@Autowired
	private UserRepository repo;
	
	
	@Test
	public void testAddNew() {
		User user =  new User();
		user.setEmail("alican@outlook.com");
		user.setPassword("ali123");
		user.setFirstName("Ali");
		user.setLastName("Kuş");
		
		User saveUser = this.repo.save(user);
		
		/*Assertion, program durumunu izleyen bir mekanizmadır. 
		Programda bir hata oluştuğunda programı hatasız bir şekilde sonlandırır. 
		 Hata öncesi yapılan tüm işlemler anında iptal edilecektir.*/
		Assertions.assertThat(saveUser).isNotNull();
		Assertions.assertThat(saveUser.getId()).isGreaterThan(0);
		
	}
	
	@Test
	public void testListAll() {
		Iterable<User> users = this.repo.findAll();
		Assertions.assertThat(users).hasSizeGreaterThan(0);
		
		for(User user : users) {
			System.out.println(user);
		}
	}
	
	@Test
	public void testUpdate() {
		Integer userId = 1;
		Optional<User> optionalUser = this.repo.findById(userId);
		User user = optionalUser.get();
		user.setPassword("hello123");
		this.repo.save(user);
		
		User updateUser = this.repo.findById(userId).get();
		Assertions.assertThat(updateUser.getPassword()).isEqualTo("hello123");
	}
	
	@Test
	public void testGet() {
		Integer userId = 2;
		Optional<User> optionalUser = this.repo.findById(userId);
		Assertions.assertThat(optionalUser).isPresent();
		System.out.println(optionalUser.get());
	}
	
	
	@Test
	public void testDelete() {
		Integer userId = 2;
		this.repo.deleteById(userId);
		
		Optional<User> optionalUser = this.repo.findById(userId);
		Assertions.assertThat(optionalUser).isNotPresent();
	}
}
