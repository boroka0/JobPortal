package com.boroka.jobsearchingapp.user;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserService implements InitializingBean {
    private final List<UserDto> initUsers = List.of(
            UserDto.builder().name("James Caeser").email("james.caeser@hotmail.com").build(),
            UserDto.builder().name("John Doe").email("john.doe@gmail.com").build(),
            UserDto.builder().name("Billie Jean").email("billie.jean@gmail.com").build(),
            UserDto.builder().name("Mary Jane").email("mary.j@gmail.com").build(),
            UserDto.builder().name("Pam Coleman").email("pam.clmn@gmail.com").build()
    );

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> findAllUsers() {
        return userRepository.findAll().stream()
                .map(this::buildDto)
                .collect(Collectors.toList());
    }

    private UserDto buildDto(UserEntity entity) {
        return UserDto.builder()
                .name(entity.getName())
                .email(entity.getEmail())
                .build();
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (userRepository.count() == 0) {
            userRepository.saveAll(
                    initUsers.stream()
                    .map(dto -> UserEntity.builder()
                            .name(dto.getName())
                            .email(dto.getEmail())
                            .build())
                            .collect(Collectors.toList())
            );
        }
    }

    public void createUser(UserEntity user) {
        userRepository.save(
                UserEntity.builder()
                        .name(user.getName())
                        .email(user.getEmail())
                        .build());
    }

    public UserDto getUserDtoById(Integer id) {
        return buildDto(userRepository.getById(id));
    }

    public UserEntity getById(Integer id) {
        return userRepository.getById(id);
    }

    public void save(UserEntity user) {
        userRepository.save(user);
    }
}
