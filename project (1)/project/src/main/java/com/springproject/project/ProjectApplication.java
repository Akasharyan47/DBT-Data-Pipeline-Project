package com.springproject.project;

import com.springproject.project.entities.Role;
import com.springproject.project.entities.User;
import com.springproject.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public  class ProjectApplication implements CommandLineRunner {

	 @Autowired
	UserRepository userRepository ;

	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User adminAccount = userRepository.findByRole(Role.ADMIN);
		if(null == adminAccount){
			User user = new User() ;
			user.setEmail("admin@gmail.com");
			user.setUsername("admin");
			user.setRole(Role.ADMIN);

		}
	}
}
