package tech.devleo.digital_library.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import tech.devleo.digital_library.entities.book.Book;
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
    private final SimpMessagingTemplate messagingTemplate;

    public BookController(BookService service, SimpMessagingTemplate messagingTemplate) {
        this.service = service;
        this.messagingTemplate = messagingTemplate;
    }

    @CrossOrigin(allowedHeaders = "*", origins = "*")
    @PostMapping
    public ResponseEntity<Void> saveBook(@RequestBody BookDTO bookDTO){
        var book = service.saveBook(bookDTO);
        messagingTemplate.convertAndSend("/topic/books", new BookResponseDTO(book));

       return ResponseEntity.created(URI.create("/api/v1/books/" + book.getBookId())).build();
    }

    @CrossOrigin(allowedHeaders = "*", origins = "*")
    @GetMapping("/{isbn}")
    public ResponseEntity<BookResponseDTO> getBookByIsbn(@PathVariable("isbn") String isbn){
        var book = service.getBookByIsbn(isbn);
        return ResponseEntity.ok(book);
    }

    @CrossOrigin(allowedHeaders = "*", origins = "*")
    @GetMapping()
    public ResponseEntity<List<BookResponseDTO>> getAllBooks(){
        return ResponseEntity.ok(service.getAllBooks());
    }

    @CrossOrigin(allowedHeaders = "*", origins = "*")
    @PutMapping("/{bookId}")
    public ResponseEntity<Void> updateBookById(@PathVariable("bookId") Long bookId, @RequestBody BookUpdateDTO bookUpdateDTO){
        service.updateBookById(bookId, bookUpdateDTO);
        return ResponseEntity.noContent().build();
    }

    @CrossOrigin(allowedHeaders = "*", origins = "*")
    @DeleteMapping("/{bookId}")
    public ResponseEntity<Void> deleteBookById(@PathVariable("bookId") Long bookId){
        service.deleteBookById(bookId);
        messagingTemplate.convertAndSend("/topic/books/delete", bookId.toString());
        return ResponseEntity.noContent().build();
    }
}
