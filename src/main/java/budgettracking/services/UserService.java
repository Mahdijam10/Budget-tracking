package budgettracking.services;


import budgettracking.dtos.UserDTO;
import budgettracking.entities.UserAccount;
import budgettracking.exceptions.UserException;
import jakarta.transaction.Transactional;
import budgettracking.mapper.UserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import budgettracking.repositories.UserRepository;

@Service
@Transactional
public class UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;


    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional
    public UserDTO createUser(UserDTO userDTO){
        UserAccount user = userMapper.toEntity(userDTO);
        UserAccount savedUser = this.userRepository.save(user);

        return this.userMapper.toDTO(savedUser);
    }

    @Transactional
    public UserDTO updateUser(Long id, UserDTO updatedUserDTO){
        UserAccount existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserException("User not found"));

        existingUser.setUsername(updatedUserDTO.getUsername());

        return userMapper.toDTO(userRepository.save(existingUser));
    }

    @Transactional
    public void deleteUser(Long id){
        this.userRepository.deleteById(id);
    }

    public UserDTO getUserById(Long id) {
        UserAccount user = userRepository.findById(id)
                .orElseThrow(() -> new UserException("User not found"));
        return userMapper.toDTO(user);
    }

    public boolean authenticate(String username, String password) {
        return userRepository.findByUsername(username)
                .map(user -> user.getPassword().equals(password))
                .orElse(false);
    }

}
