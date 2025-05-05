package tech.devleo.digital_library.entities.book.exceptions;

public class DuplicateBookException extends RuntimeException {
    public DuplicateBookException(String message) {
        super(message);
    }
}
