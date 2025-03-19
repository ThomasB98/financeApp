package com.example.demo.ServiceImpl;

import com.example.demo.Dto.LoginDto;
import com.example.demo.Dto.UserRegisterDto;
import com.example.demo.Entity.Admin;
import com.example.demo.Entity.Card;
import com.example.demo.Entity.User;
import com.example.demo.Exceptions.AdminNotFoundException;
import com.example.demo.Exceptions.UserAlreadyExistsException;
import com.example.demo.Exceptions.UserNotFoundException;
import com.example.demo.Mapper.UserMapper;
import com.example.demo.Service.IAuthService;
import com.example.demo.Utils.Card.CardUtils;
import com.example.demo.repository.AdminRepository;
import com.example.demo.repository.CardRepository;
import com.example.demo.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements IAuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserMapper userMapper;

    @Transactional
    @Override
    public String registerUser(UserRegisterDto dto) {
        boolean userExists = userRepository.existsByEmailOrAccountNumberOrUsername(
                dto.getEmail(), dto.getAccountNumber(), dto.getUsername()
        );

        if (userExists) {
            throw new UserAlreadyExistsException("User already exists with the provided email, account number, or username.");
        }

        User user=userMapper.convertingDtoToUser(dto);

        Card newCard = new Card();
        newCard.setName(user.getName() + "'s EMI Card");
        newCard.setValidTill(CardUtils.generateValidTillDate());
        newCard.setCardType(dto.getCardType()); // Assume the user selects card type
        newCard.setIsActivated(false);

        // Assign card to user
        user.setCard(newCard);

        // Save user and card together
        userRepository.save(user);

        return "User registered successfully!";
    }

    @Override
    public User loginUser(LoginDto user) {
        User existingUser = userRepository.findByUsername(user.getUsernameOrEmail());
        if (existingUser != null && user.getPassword().equals(existingUser.getPassword())) {
            return existingUser;  // Return user details on successful login
        }
        throw new UserNotFoundException("User not found Invalid cred");  // Return null for invalid credentials
    }
    @Override
    public Admin loginAdmin(LoginDto user) {
        Admin existingAdmin = adminRepository.findByUsername(user.getUsernameOrEmail());
        if (existingAdmin != null && user.getPassword().equals(existingAdmin.getPassword())) {
            return existingAdmin;  // Return admin details on successful login
        }
        throw new AdminNotFoundException("Admin Not found Invalid cred"); // Return null for invalid credentials
    }

}
