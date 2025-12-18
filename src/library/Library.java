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


    public static class OperationLog {
        public static class LogEntry {
            private final OperationType operationType;
            private final LocalDateTime timestamp;
            private final String description;

            public LogEntry(OperationType operationType, LocalDateTime timestamp, String description) {
                this.operationType = operationType;
                this.timestamp = timestamp;
                this.description = description;
            }

            public OperationType getOperationType() {
                return operationType;
            }

            public LocalDateTime getTimestamp() {
                return timestamp;
            }

            public String getDescription() {
                return description;
            }
        }

        public enum OperationType {
            ADD_BOOK,BORROW,RETURN
        }

        private List<LogEntry> entries;

        public void addEntry(Book book,OperationType op,String description) {
            var entry = new LogEntry(op,LocalDateTime.now(),description);
            entries.add(entry);
        }

        public List<LogEntry> getEntries(){
            return entries;
        }


    }

    public Library() {
        books = new ArrayList<>();
        operationLog = new OperationLog();
    }

    public void addBook(Book book) {
        books.add(book);
        operationLog.addEntry(book, OperationLog.OperationType.ADD_BOOK,"Added a new book");

    }

    public Book findBookById(int id) {
        Book book = null;
        for (var b:books) {
            if (b.getId() == id) {
                book = b;
                break;
            }
        }
        return book;
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
        return books.stream().filter(Book::isAvailable).toList();
    }

    public void printOperationLog() {
        operationLog.entries.forEach(System.out::println);
    }

    public String getStatistics() {
        return String.format("All books:%d, Available: %d",books.size(),getAvailableBooks().size());
    }
}
