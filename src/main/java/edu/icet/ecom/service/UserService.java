package edu.icet.ecom.service;

import edu.icet.ecom.dto.UserDTO;
import java.util.List;

public interface UserService {
    UserDTO saveUser(UserDTO userDTO);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Integer id);
    UserDTO updateUser(Integer id, UserDTO userDTO);
    boolean deleteUser(Integer id);
}
