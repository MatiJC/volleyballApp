package com.maticuad.volleyballApp;

import com.maticuad.volleyballApp.models.Role;
import com.maticuad.volleyballApp.models.User;
import com.maticuad.volleyballApp.repositories.RoleRepository;
import com.maticuad.volleyballApp.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class VolleyballAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(VolleyballAppApplication.class, args);
	}

	@Bean
	CommandLineRunner run(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder encoder) {
		return args -> {
			if (roleRepository.findByAuthority("ADMIN").isPresent()) {return;}

			Role adminRole = roleRepository.save(new Role("ADMIN"));
			Role userRole = roleRepository.save(new Role("USER"));

			Set<Role> roles = new HashSet<>();
			roles.add(adminRole);
			//roles.add(userRole);

			User admin = new User("admin", encoder.encode("admin"), "Voley",
					"Admin", roles);
			userRepository.save(admin);


		};
	}
}
