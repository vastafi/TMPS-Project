package internal.services;

import internal.models.Library;
import internal.models.MainData;
import patterns.creational.prototype.Student;
import patterns.structural.adapter.Book;

import java.util.ArrayList;
import java.util.Scanner;

public class UserLoop extends MainData {
    public UserLoop(Library lib, Student usr, Library library) {
        super(lib, usr, library);
        start();
    }

    public void start(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("\n\t User menu");
            System.out.println("1. Show my books");
            System.out.println("2. Show available books");
            System.out.println("3. Take book by id");
            System.out.println("4. Return book by id");
            System.out.println("9. Open librarian menu ");
            System.out.println("0. Exit");
            int select = scanner.nextInt();
            scanner.nextLine();
            switch (select){
                case 1:
                    ArrayList<Book> myBooks = library.select.getBooksByUserID(usr.getId(),lib.getId());
                    if (myBooks.size() > 0){
                        System.out.println("\tAll my books:");
                        for (Book book : myBooks){
                            book.toStringBook();
                            System.out.println();
                        }
                    }else System.out.println("You dont take books");
                    break;
                case 2:
                    myBooks = library.select.getAvailableBooksByUserID(usr.getId(),lib.getId());
                    if (myBooks.size() > 0){
                        System.out.println("\tAvailable books:");
                        for (Book book : myBooks){
                            book.toStringBook();
                            System.out.println();
                        }
                    }else System.out.println("No books available");
                    break;
                case 3:
                    System.out.println("\n\tEnter book id:");
                    System.out.print("Id: ");

                    int id = scanner.nextInt();
                    scanner.nextLine();
                    if(library.select.isAvailableBook(id, lib.getId())){
                        library.update.makeBookNotAvailable(id, usr.getId());
                        library.select.getBookByID(id);
                    }else System.out.println("\tBook is not available or not exist");
                    break;
                case 4:
                    System.out.println("\n\tEnter book id:");
                    System.out.print("id: ");

                    id = scanner.nextInt();
                    scanner.nextLine();
                    if(library.select.isUsersBook(id, lib.getId(), usr.getId())){
                        library.update.makeBookAvailable(id, usr.getId());
                        library.select.getBookByID(id);
                    }else System.out.println("\tBook is not available or not exist");
                    break;

                case 9:
                    if (usr.getAccessGrade() > 4){
                        changeActivity();
                        doIt();
                        return;
                    }
                    System.out.println("\n\t You do not have access to this option");
                    break;
                case 0:
                    System.out.println("\n\tUser menu is closed");
                    return;
            }
        }

    }
}
