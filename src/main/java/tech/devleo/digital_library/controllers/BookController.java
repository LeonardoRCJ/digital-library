package tech.devleo.digital_library.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.devleo.digital_library.entities.book.BookDTO;
import tech.devleo.digital_library.entities.book.BookResponseDTO;
import tech.devleo.digital_library.entities.book.BookUpdateDTO;
import tech.devleo.digital_library.services.BookService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> saveBook(@RequestBody BookDTO bookDTO){
        Long bookId = service.saveBook(bookDTO);

       return ResponseEntity.created(URI.create("/api/v1/books/" + bookId)).build();
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<BookResponseDTO> getBookByIsbn(@PathVariable("isbn") String isbn){
        var book = service.getBookByIsbn(isbn);
        return ResponseEntity.ok(book);
    }

    @GetMapping()
    public ResponseEntity<List<BookResponseDTO>> getAllBooks(){
        return ResponseEntity.ok(service.getAllBooks());
    }

    @PutMapping("/{bookId}")
    public ResponseEntity<Void> updateBookById(@PathVariable("bookId") Long bookId, @RequestBody BookUpdateDTO bookUpdateDTO){
        service.updateBookById(bookId, bookUpdateDTO);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBookById(@PathVariable("bookId") Long bookId){
        service.deleteBookById(bookId);
        return ResponseEntity.noContent().build();
    }
}
