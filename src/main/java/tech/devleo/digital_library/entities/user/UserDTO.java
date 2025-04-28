package tech.devleo.digital_library.entities.user;

public record UserDTO(String fullName, String cpf, String email, String password, UserType userType) {
}
