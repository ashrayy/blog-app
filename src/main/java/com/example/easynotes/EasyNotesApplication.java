package com.example.easynotes;

import com.example.easynotes.config.AppConstants;
import com.example.easynotes.model.Role;
import com.example.easynotes.repository.RoleRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@SpringBootApplication
@EnableJpaAuditing
public class EasyNotesApplication implements CommandLineRunner {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(EasyNotesApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return  new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
     try{
     	Role role = new Role();
		 Role role1 = new Role();
     	role.setId(AppConstants.ADMIN_USER);
     	role.setName("ROLE_ADMIN");

		 role1.setId(AppConstants.NORMAL_USER);
		 role1.setName("ROLE_NORMAL");

		 List<Role> roles = List.of(role,role1);

		 this.roleRepo.saveAll(roles);
	 }
     catch (Exception e){
     	e.printStackTrace();
	 }
	}

}
