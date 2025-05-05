package tech.devleo.digital_library.entities.book;

import java.math.BigDecimal;

public record BookResponseDTO(Long bookId, String title, String isbn, String author, String publisher, String description, BookType bookType, String imageUrl, BigDecimal price) {
    public BookResponseDTO(Book book){
        this(book.getBookId(), book.getTitle(), book.getIsbn(), book.getAuthor(), book.getPublisher(), book.getDescription(), book.getBookType(), book.getImageUrl(), book.getPrice());
    }
}
