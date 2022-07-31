package internal.services;

import internal.models.Library;
import internal.models.MainData;
import patterns.creational.prototype.Student;
import patterns.structural.adapter.Book;

import java.util.ArrayList;
import java.util.Scanner;

public class LibrarianLoop extends MainData {
    public LibrarianLoop(Library lib, Student usr, Library library) {
        super(lib, usr, library);
        start();
    }

    private void start(){
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("\n\t Librarian menu");
            System.out.println("1. Show all books");
            System.out.println("2. Add new book");
            System.out.println("3. Get info about book by id");
            System.out.println("4. Update book by id");
            System.out.println("5. Delete book by id");
            System.out.println("9. Open user menu ");
            System.out.println("0. Exit");
            int select = sc.nextInt();
            sc.nextLine();
            switch (select){
                case 1:
                    ArrayList<Book> myBooks = library.select.getAllBooks(usr.getId(),lib.getId());
                    if (myBooks.size() > 0){
                        System.out.println("\tAll books:");
                        for (Book book : myBooks){
                            book.toStringBook();
                            System.out.println();
                        }
                    }else System.out.println("You dont take books");
                    break;
                case 2:
                    System.out.println("\n\tEnter new book:");
                    System.out.print("name: ");
                    String name =  sc.nextLine();
                    System.out.print("author: ");
                    String author = sc.nextLine();
                    System.out.print("year: ");
                    int year = sc.nextInt();
                    sc.nextLine();
                    System.out.print("category: ");
                    String category = sc.nextLine();
                    System.out.print("accessGrade: ");
                    int accessGrade = sc.nextInt();
                    sc.nextLine();
                    System.out.print("publisher: ");
                    String publisher = sc.nextLine();
                    System.out.print("publishDate: ");
                    int publishDate = sc.nextInt();
                    sc.nextLine();

                    library.insert.execute(new Book(lib, year, accessGrade, name, author, category, publisher, publishDate));
                    break;
                case 3:
                    System.out.println("\n\tEnter book id:");
                    System.out.print("id: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    if(library.select.isBook(id, lib.getId())){
                        System.out.println("Book :");
                        library.select.getBookByID(id).toStringBook();
                    }else System.out.println("\tBook is not available or not exist");
                    break;
                case 4:
                    System.out.println("\n\tEnter book id:");
                    System.out.print("id: ");

                    id = sc.nextInt();
                    sc.nextLine();
                    if(library.select.isBook(id, lib.getId())){
                        library.select.getBookByID(id);
                        System.out.println("\n\tUpdate book:");
                        System.out.print("name: ");
                        name =  sc.nextLine();
                        System.out.print("author: ");
                        author = sc.nextLine();
                        System.out.print("category: ");
                        category = sc.nextLine();
                        System.out.print("year: ");
                        year = sc.nextInt();
                        sc.nextLine();
                        System.out.print("accessGrade: ");
                        accessGrade = sc.nextInt();
                        sc.nextLine();
                        System.out.print("available: ");
                        boolean available = sc.nextBoolean();
                        sc.nextLine();
                        System.out.print("publisher: ");
                        publisher = sc.nextLine();
                        System.out.print("publishDate: ");
                        publishDate = sc.nextInt();
                        System.out.print("library id: ");
                        int lib_id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("user id: ");
                        int user_id = sc.nextInt();
                        sc.nextLine();

                        library.update.updateBookByID(new Book(id,name,author,category, year,accessGrade, available,
                                publisher, publishDate, lib, user_id));

                    }else System.out.println("\tBook is not available or not exist");
                    break;
                case 5:
                    System.out.println("\n\tEnter book id:");
                    System.out.print("id: ");

                    id = sc.nextInt();
                    sc.nextLine();
                    if(library.select.isBook(id, lib.getId())){
                        library.delete.execute(library.select.getBookByID(id));

                    }else System.out.println("\tBook is not available or not exist");
                    break;

                case 9:
                    changeActivity();
                    doIt();
                    return;
                case 0:
                    System.out.println("\n\tUser menu is closed");
                    return;
            }
        }

    }
}