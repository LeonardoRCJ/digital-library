package tech.devleo.digital_library.entities.user;

public record UserResponseDTO(String fullName, String email, String cpf) {
    public UserResponseDTO(User user) {
        this(user.getFullName(), user.getEmail(), user.getCpf());
    }
}
