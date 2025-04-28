package tech.devleo.digital_library.entities.book;

public record BookResponseDTO(Long bookId, String title, String isbn, String author, String publisher, String description) {
    public BookResponseDTO(Book book){
        this(book.getBookId(), book.getTitle(), book.getIsbn(), book.getAuthor(), book.getPublisher(), book.getDescription());
    }
}
