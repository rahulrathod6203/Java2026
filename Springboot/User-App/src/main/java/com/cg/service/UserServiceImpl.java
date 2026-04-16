package com.cg.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.dto.UserDTO;
import com.cg.entity.User;
import com.cg.exception.UserAlreadyExistsException;
import com.cg.exception.UserNotFoundException;
import com.cg.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public List<UserDTO> getAllUsers() {

		return userRepository.findAll().stream().map(user -> modelMapper.map(user, UserDTO.class)).toList();
	}

	@Override
	public UserDTO addUser(UserDTO userDTO) {
		// TODO Auto-generated method stub

		Boolean existsByEmail = userRepository.existsByEmail(userDTO.getEmail());
		if (existsByEmail) {
			throw new UserAlreadyExistsException(
					"User already found with the given email address, please use different one!");
		}

		User savedUser = userRepository.save(modelMapper.map(userDTO, User.class));
		return modelMapper.map(savedUser, UserDTO.class);
	}

	@Override
	public UserDTO getUserById(Long id) {
		// TODO Auto-generated method stub

		return userRepository.findById(id).map(user -> modelMapper.map(user, UserDTO.class))
				.orElseThrow(() -> new UserNotFoundException("User with the give id not found!"));
	}

	@Override
	public UserDTO updateUser(Long id, UserDTO userDTO) {

		return userRepository.findById(id).map(user -> {
			user.setUsername(userDTO.getUsername());
			user.setPassword(userDTO.getPassword());

			User savedUser = userRepository.save(user);

			return modelMapper.map(savedUser, UserDTO.class);
		}).orElseThrow(() -> new UserNotFoundException("User with the give id not found!"));
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		userRepository.findById(id).map(user -> modelMapper.map(user, UserDTO.class))
				.orElseThrow(() -> new UserNotFoundException("User with the give id not found!"));

		userRepository.deleteById(id);

	}

}
