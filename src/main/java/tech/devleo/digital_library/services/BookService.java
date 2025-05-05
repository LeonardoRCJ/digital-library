package tech.devleo.digital_library.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.devleo.digital_library.entities.book.BookDTO;
import tech.devleo.digital_library.entities.book.BookResponseDTO;
import tech.devleo.digital_library.entities.book.BookUpdateDTO;
import tech.devleo.digital_library.entities.book.exceptions.BookNotFoundException;
import tech.devleo.digital_library.entities.book.exceptions.DuplicateBookException;
import tech.devleo.digital_library.repositories.BookRepository;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }


    @Transactional(readOnly = true)
    public List<BookResponseDTO> getAllBooks(){
        return repository.findAll().stream().map(BookResponseDTO::new).toList();
    }

    @Transactional
    public Long saveBook(BookDTO bookDTO){
        if (repository.findByIsbn(bookDTO.isbn()).isPresent()){
            throw new DuplicateBookException("the ISBN: " + bookDTO.isbn() +" already exists");
        }

        var book = bookDTO.toEntity();


        return repository.save(book).getBookId();


    }

    @Transactional(readOnly = true)
    public BookResponseDTO getBookByIsbn(String isbn){
        var book = repository.findByIsbn(isbn).orElseThrow(() -> new BookNotFoundException("Book not found by ISBN: " + isbn));

        return new BookResponseDTO(book);
    }

    @Transactional
    public void deleteBookById(Long bookId) {
        var book = repository.findById(bookId).orElseThrow(()-> new BookNotFoundException("Book not found by ID: " + bookId));

        repository.deleteById(bookId);
    }

    @Transactional
    public void updateBookById(Long bookId, BookUpdateDTO bookUpdateDTO){
        var bookForUpdate = repository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book not found by ID: " + bookId));

        if (bookUpdateDTO.title().isEmpty() && bookUpdateDTO.imageUrl().isEmpty()) {
            throw new IllegalArgumentException("all fields must be filled in");
        }

        bookForUpdate.applyUpdates(bookUpdateDTO);
    }


}
