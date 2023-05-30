package io.project.todoapp.service;

import io.project.todoapp.security.user.Role;
import io.project.todoapp.security.user.User;
import io.project.todoapp.security.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {

    private static final String NOT_FOUND_USER_EXCEPTION_MESSAGE = "Student with id: %s not founded";

    private final UserRepository userRepository;

    public User getStudentById(Long id) {
        Optional<User> foundedUser = userRepository.findById(id);

        if(foundedUser.isPresent()) {
            Role userRole = foundedUser.get().getRole();
            if(userRole.equals(Role.STUDENT) || userRole.equals(Role.CLASS_PRESIDENT)) {
                return foundedUser.get();
            }
        }
        throw new EntityNotFoundException(String.format(NOT_FOUND_USER_EXCEPTION_MESSAGE, id));
    }

    public void addNewStudent(User user) {
        if(user.isStudent()) {
            userRepository.save(user);
        }
    }

    public void deleteStudent(String email) {
        Optional<User> foundedUser = userRepository.findByEmail(email);
        if(foundedUser.isPresent() && foundedUser.get().isStudent()) {
            userRepository.delete(foundedUser.get());
        }
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }
}