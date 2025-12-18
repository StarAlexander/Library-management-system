package library;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class Library {
    private final List<Book> books;

    private final OperationLog operationLog;




    public Library() {
        books = new ArrayList<>();
        operationLog = new OperationLog();
    }

    public void addBook(Book book) {
        books.add(book);
        operationLog.addEntry(book, OperationLog.OperationType.ADD_BOOK,"Added a new book");

    }

    public Book findBookById(int id) {
        books.forEach((Book b) -> {
            if (b.getId() == id) {
                return b;
            }
        });
        return null;
    }

    public List<Book> findBooksByAuthor(String author) {
        return books.stream().filter(b -> b.getAuthor() == author).toList();
    }

    public void borrowBook(int id) {
        var book = findBookById(id);
        if (book != null) {
            book.setAvailable(false);
            operationLog.addEntry(book,OperationLog.OperationType.BORROW,"Borrowed a book");
        }
        else {
            System.out.println("No such book found.");
        }
    }

    public void returnBook(int id) {
        var book = findBookById(id);
        if (book != null) {
            book.setAvailable(true);
            operationLog.addEntry(book,OperationLog.OperationType.RETURN,"Returned a book");
        }
        else {
            System.out.println("No such book found");
        }
    }

    public List<Book> getAvailableBooks() {
        return books.stream().filter(b -> b.getAvailable()).toList();
    }

    public void printOperationLog() {
        operationLog.entries.forEach(System.out::println);
    }
}
