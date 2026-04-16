package com.cg.service;

import java.util.List;

import com.cg.dto.UserDTO;

public interface UserService {

	List<UserDTO> getAllUsers();

	UserDTO addUser(UserDTO userDTO);

	UserDTO getUserById(Long id);

	UserDTO updateUser(Long id, UserDTO userDTO);

	void deleteUser(Long id);

}
