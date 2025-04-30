package tech.devleo.digital_library.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.devleo.digital_library.entities.user.UserDTO;
import tech.devleo.digital_library.entities.user.UserResponseDTO;
import tech.devleo.digital_library.entities.user.UserUpdateDTO;
import tech.devleo.digital_library.exception.DuplicateUserException;
import tech.devleo.digital_library.exception.UserNotFoundException;
import tech.devleo.digital_library.repositories.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public UserResponseDTO getUserByCpf(String cpf){
        var user = repository.getUserByCpf(cpf).orElseThrow(() -> new UserNotFoundException("User Not Found by cpf: " + cpf));

        return new UserResponseDTO(user);
    }

    @Transactional
    public UUID saveUser(UserDTO dto){
        if (repository.getUserByCpf(dto.cpf()).isPresent()) {
            throw new DuplicateUserException("The CPF: " + dto.cpf() + " already exists in database");
        }

        var user = dto.toEntity();

        return user.getUserId();
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUsers(){
        return repository.findAll().stream().map(UserResponseDTO::new).toList();
    }

    @Transactional
    public void deleteUserById(String userId){
        var id = UUID.fromString(userId);

        var user = repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found by ID: " + id));

        repository.deleteById(id);
    }

    @Transactional
    public void updateUserById(String id, UserUpdateDTO update){
        var userByUpdate = repository.findById(UUID.fromString(id)).orElseThrow(() -> new UserNotFoundException("User not found by ID: " + id));

        userByUpdate.tradePassword(update);
    }
}
