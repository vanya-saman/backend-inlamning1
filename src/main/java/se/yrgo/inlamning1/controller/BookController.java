package se.yrgo.inlamning1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.yrgo.inlamning1.domain.Book;


import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
public class BookController {
    private final List<Book> books = List.of(
            new Book("Time of Contempt", "Andrzej Sapkowski", "Fantasy"),
            new Book("The Blade Itself", "Joe Abercrombie", "Fantasy"),
            new Book("A Confederacy of Dunces", "John Kennedy Toole", "Comedy"),
            new Book("The Pillars of the Earth", "Ken Follett", "History"),
            new Book("I'm My Own Grandpa", "Gaeldol", "Comedy"),
            new Book("Morning Star", "Carlovac Townway", "History"),
            new Book("Poison Song", "Bristin Xel", "History"),
            new Book("A Dance in Fire", "Waughin Jarth", "Adventure"),
            new Book("Battle of Sancre Tor", "Anonymous", "History")
    );

    @GetMapping("/home")
    public String getHomePage(Model model) {
        model.addAttribute("current_date", LocalDate.now().toString());
        return "home";
    }

    @GetMapping("/books")
    public String showBooks(Model model) {
        var recommendedBooks = List.of(
                new Book("The Blade Itself", "Joe Abercrombie", "Fantasy"),
                new Book("A Confederacy of Dunces", "John Kennedy Toole", "Comedy"),
                new Book("The Pillars of the Earth", "Ken Follett", "History"),
                new Book("A Dance in Fire", "Waughin Jarth", "Adventure"),
                new Book("Battle of Sancre Tor", "Anonymous", "History")
        );
        model.addAttribute("books", recommendedBooks);
        return "booklist";
    }

    @GetMapping("/genre")
    public String showByGenre(Model model, @RequestParam(name = "type", required = false) String genre) {
        String message;
        List<Book> booksToDisplay;

        if (genre == null || genre.isBlank()) {
            message = "All Books";
            booksToDisplay = books;
        } else {
            booksToDisplay = books.stream()
                    .filter(book -> book.getGenre().equalsIgnoreCase(genre))
                    .toList();

            message = booksToDisplay.isEmpty() ?
                    "No books of genre \"" + genre + "\" found."
                    : "All books of genre: " + genre;
        }

        model.addAttribute("message", message);
        model.addAttribute("books", booksToDisplay);
        return "genre";
    }
}
