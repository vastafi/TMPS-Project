package patterns.structural.facade;

import patterns.creational.prototype.User;
import patterns.structural.adapter.Book;

import java.util.List;
import java.util.Random;

public class Workflow {
    User user   ;
    Library library;
    Book book;

    public Workflow(User user, Library library, Book book) {
        this.user = user;
        this.library = library;
        this.book = book;
    }


    public void solveProblem(){
//        user.goToLibrary();
        if (library.workTracker.isActiveWork()){

            List<Book> books = library.getAllBooks();
            Random random = new Random();
            int nr = random.nextInt(books.size());

            user.readBook(books.get(nr));

        }else {
            System.out.println("Library is closed, come back next time");
        }
        library.giveBook(book, user);
    }
}
