package tech.devleo.digital_library.entities.book;

import java.math.BigDecimal;

public record BookDTO(String isbn, String title, String author, String publisher, String description, BookType bookType, String imageUrl, BigDecimal price) {
    public Book toEntity(){
        return new Book(null, title, isbn, author, publisher, description, bookType, imageUrl, price);
    }
}
