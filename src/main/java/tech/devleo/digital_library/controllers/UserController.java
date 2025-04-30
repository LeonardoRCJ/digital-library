package tech.devleo.digital_library.controllers;

import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.devleo.digital_library.entities.user.UserDTO;
import tech.devleo.digital_library.entities.user.UserResponseDTO;
import tech.devleo.digital_library.entities.user.UserUpdateDTO;
import tech.devleo.digital_library.services.UserService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> saveUser(@RequestBody UserDTO userDTO){
        var uuid = service.saveUser(userDTO);

        return ResponseEntity.created(URI.create("/api/v1/users" + uuid)).build();
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<UserResponseDTO> getUserByCpf(String cpf){
        var user = service.getUserByCpf(cpf);

        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers(){
        List<UserResponseDTO> users = service.getAllUsers();

        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable("userId") String userId){
        service.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> updateUserById(@PathVariable("userId") String userId, UserUpdateDTO update){
        service.updateUserById(userId, update);

        return ResponseEntity.noContent().build();
    }
}
