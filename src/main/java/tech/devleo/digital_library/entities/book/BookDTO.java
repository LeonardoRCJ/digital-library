package tech.devleo.digital_library.entities.book;

public record BookDTO(String isbn, String title, String author, String publisher, String description, BookType bookType, String imageUrl) {
    public Book toEntity(){
        return new Book(null, title, isbn, author, publisher, description, bookType, imageUrl);
    }
}
