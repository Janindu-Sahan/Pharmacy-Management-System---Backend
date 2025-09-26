package edu.icet.ecom.service;

import edu.icet.ecom.dto.UserDTO;
import edu.icet.ecom.entity.UserEntity;
import edu.icet.ecom.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    @Override
    public UserDTO saveUser(UserDTO userDTO) {
        try {
            UserEntity entity = new UserEntity(
                    null, // ID is auto-generated
                    userDTO.getName(),
                    userDTO.getEmail(),
                    userDTO.getPNumber(),
                    UserEntity.Role.valueOf(userDTO.getRole()) // Convert String to Role enum
            );
            UserEntity saved = userRepo.save(entity);
            return new UserDTO(
                    saved.getId(),
                    saved.getName(),
                    saved.getEmail(),
                    saved.getPnumber(),
                    saved.getRole().name() // Convert Role enum to String
            );
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + userDTO.getRole() + ". Valid roles are: ADMIN, PHARMACIST, ASSISTANT");
        }
    }

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepo.findAll()
                .stream()
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getPnumber(),
                        user.getRole().name() // Convert Role enum to String
                ))
                .toList(); // Use Stream.toList() for unmodifiable list
    }

    @Override
    public UserDTO getUserById(Integer id) {
        return null;
    }

    @Override
    public UserDTO updateUser(Integer id, UserDTO userDTO) {
        return null;
    }

    @Override
    public boolean deleteUser(Integer id) {
        return false;
    }


    public UserDTO getUserById(Long id) {
        return userRepo.findById(id)
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getName(),
                        user.getEmail(),
                        user.getPnumber(),
                        user.getRole().name() // Convert Role enum to String
                ))
                .orElse(null);
    }


    public UserDTO updateUser(Long id, UserDTO userDTO) {
        try {
            return userRepo.findById(id)
                    .map(existingUser -> {
                        existingUser.setName(userDTO.getName());
                        existingUser.setEmail(userDTO.getEmail());
                        existingUser.setPnumber(userDTO.getPNumber());
                        existingUser.setRole(UserEntity.Role.valueOf(userDTO.getRole())); // Convert String to Role enum
                        UserEntity updated = userRepo.save(existingUser);
                        return new UserDTO(
                                updated.getId(),
                                updated.getName(),
                                updated.getEmail(),
                                updated.getPnumber(),
                                updated.getRole().name() // Convert Role enum to String
                        );
                    })
                    .orElse(null);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + userDTO.getRole() + ". Valid roles are: ADMIN, PHARMACIST, ASSISTANT");
        }
    }


    public boolean deleteUser(Long id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }
}