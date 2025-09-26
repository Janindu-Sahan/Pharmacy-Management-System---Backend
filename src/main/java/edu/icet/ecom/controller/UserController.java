package edu.icet.ecom.controller;

import edu.icet.ecom.dto.UserDTO;
import edu.icet.ecom.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@CrossOrigin(origins = "*") // Adjust origins as needed for production
public class UserController {

    private final UserService userService;

    // 🔹 Create User
    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createdUser = userService.saveUser(userDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    // 🔹 Get All Users
    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    // 🔹 Get User by ID
    @GetMapping("Users")
    public ResponseEntity<UserDTO> getUserById(Integer id) { // Changed to Long (verify with your service)
        UserDTO user = userService.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    // 🔹 Update User
    @PutMapping("Users")
    public ResponseEntity<UserDTO> updateUser(Integer id, @Valid @RequestBody UserDTO userDTO) {
        UserDTO updated = userService.updateUser(id, userDTO);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }

    // 🔹 Delete User
    @DeleteMapping("Users")
    public ResponseEntity<Void> deleteUser(Integer id) {
        boolean deleted = userService.deleteUser(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}