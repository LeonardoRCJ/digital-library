package tech.devleo.digital_library.entities.user;

public record UserDTO(String fullName, String cpf, String email, String password, UserType userType) {
    public User  toEntity(){
        return new User(null, fullName, cpf, email, password, userType);
    }
}
