package tech.devleo.digital_library.entities.book;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false, unique = true)
    private String isbn;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String publisher;
    @Column(nullable = false, length = 150)
    private String description;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BookType bookType;
    @Column(nullable = false, length = 500)
    private String imageUrl;
    @Column(nullable = false)
    private BigDecimal price;
    public Book() {
    }

    public Book(Long bookId, String title, String isbn, String author, String publisher, String description, BookType bookType, String imageUrl, BigDecimal price) {
        this.bookId = bookId;
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.publisher = publisher;
        this.description = description;
        this.bookType = bookType;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BookType getBookType() {
        return bookType;
    }

    public void setBookType(BookType bookType) {
        this.bookType = bookType;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void applyUpdates(BookUpdateDTO updateDTO) {
        if (!updateDTO.title().isEmpty()) {
            this.setTitle(updateDTO.title());
        }
        if (!updateDTO.imageUrl().isEmpty()) {
            this.setImageUrl(updateDTO.imageUrl());
        }
        if (!(updateDTO.price() == null)) {
            this.setPrice(updateDTO.price());
        }
    }
}
