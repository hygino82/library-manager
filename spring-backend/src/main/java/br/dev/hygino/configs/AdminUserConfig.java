package br.dev.hygino.configs;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import br.dev.hygino.models.Role;
import br.dev.hygino.models.SchoolAttribute;
import br.dev.hygino.models.User;
import br.dev.hygino.repositories.RoleRepository;
import br.dev.hygino.repositories.UserRepository;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    final Logger logger = LoggerFactory.getLogger(AdminUserConfig.class);

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(RoleRepository roleRepository,
            UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        Role roleAdmin = roleRepository
                .findByName(Role.Values.ADMIN.name())
                .orElseGet(() -> {
                    Role role = new Role();
                    role.setName(Role.Values.ADMIN.name());
                    return roleRepository.save(role);
                });

        var userAdmin = userRepository.findByUsername("admin");

        userAdmin.ifPresentOrElse(
                user -> {
                    // System.out.println("admin ja existe");
                    logger.error("admin ja existe!");
                },
                () -> {
                    var user = new User();
                    user.setName("Administrador");
                    user.setSchoolAttribute(SchoolAttribute.ADMIN);
                    user.setUsername("admin");
                    user.setPassword(passwordEncoder.encode("123"));
                    user.setRoles(Set.of(roleAdmin));
                    userRepository.save(user);
                    logger.info("admin criado!");
                });
    }
}