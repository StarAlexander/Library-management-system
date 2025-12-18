import library.Book;
import library.Library;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();

        library.addBook(new Book(1, "Война и мир", "Л. Н. Толстой",1869,"978-5-17-090335-2"));
        library.addBook(new Book(2, "Преступление и наказание","Ф.М. Достоевский", 1866, "978-5-17-090336-9"));
        library.addBook(new Book(3, "Анна Каренина", "Л.Н. Толстой", 1877, "978-5-17-090337-6"));

        System.out.println(library.getAvailableBooks());
        library.borrowBook(1);
        System.out.println(library.getAvailableBooks());
        library.returnBook(1);
        System.out.println(library.getAvailableBooks());
        library.printOperationLog();
    }
    }
