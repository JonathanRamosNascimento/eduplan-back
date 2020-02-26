package com.unievangelica.eduplan;

import com.unievangelica.eduplan.api.repository.UserRepository;
import com.unievangelica.eduplan.api.entity.User;
import com.unievangelica.eduplan.api.enums.ProfileEnum;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EduPlanApplication {

  public static void main(String[] args) {
    SpringApplication.run(EduPlanApplication.class, args);
  }

  @Bean
  CommandLineRunner init(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    return args -> {
      initUsers(userRepository, passwordEncoder);
    };
  }

  private void initUsers(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    User diretor = new User();
    diretor.setNome("Viviane");
    diretor.setMatricula("143568");
    diretor.setEmail("diretor@eduplan.com");
    diretor.setPassword(passwordEncoder.encode("123456"));
    diretor.setProfile(ProfileEnum.ROLE_DIRETOR);

    User find = userRepository.findByEmail("diretor@eduplan.com");
    if (find == null) {
      userRepository.save(diretor);
    }
  }
}
