package com.jwt.turorial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jwt.turorial.model.User;
import com.jwt.turorial.repo.UserRepo;


@SpringBootApplication
public class TurorialApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(TurorialApplication.class, args);
	}

	@Autowired
	UserRepo userRepo;
	
	@Override
	
	public void run(String... args) throws Exception {
//		
//		User user = userRepo.findById(1).get();
//		
//		System.out.println(user.getRoles());
		
	}

}
