package main;

import internal.models.Library;
import patterns.behavioral.command.DeleteCommand;
import patterns.behavioral.command.InsertCommand;
import patterns.behavioral.command.SelectCommand;
import patterns.behavioral.command.UpdateCommand;
import patterns.creational.prototype.Student;
import patterns.structural.adapter.Book;
import patterns.structural.facade.WorkTracker;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static WorkTracker workTracker = new WorkTracker();
    static Library library = new Library(workTracker,
            new InsertCommand(),
            new UpdateCommand(),
            new SelectCommand(),
            new DeleteCommand());
    static Library lib;
    static Student std;

    public static void main(String[] args) {
        mainLoop();
    }

    public static void userLoop(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("\n\t User menu");
            System.out.println("1. Show my books");
            System.out.println("2. Show available books");
            System.out.println("3. Take book by id");
            System.out.println("4. Return book by id");
            System.out.println("5. Add to library new book");
            System.out.println("0. Exit");
            int select = scanner.nextInt();
            scanner.nextLine();
            switch (select){
                case 1:
                    ArrayList<Book> myBooks = library.select.getBooksByUserID(std.getId(),lib.getId());
                    if (myBooks.size() > 0){
                        System.out.println("\tAll my books:");
                        for (Book book : myBooks){
                            book.toStringBook();
                            System.out.println();
                        }
                    }else System.out.println("You dont take books");
                    break;
                case 2:
                    myBooks = library.select.getAvailableBooksByUserID(std.getId(),lib.getId());
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
                    System.out.print("id: ");

                    int id = scanner.nextInt();
                    scanner.nextLine();
                    if(library.select.isAvailableBook(id, lib.getId())){
                        library.update.makeBookNotAvailable(id, std.getId());
                        library.select.getBookByID(id);
                    }else System.out.println("\tBook is not available or not exist");
                    break;
                case 4:
                    System.out.println("\n\tEnter book id:");
                    System.out.print("id: ");

                    id = scanner.nextInt();
                    scanner.nextLine();
                    if(library.select.isUsersBook(id, lib.getId(), std.getId())){
                        library.update.makeBookAvailable(id, std.getId());
                        library.select.getBookByID(id);
                    }else System.out.println("\tBook is not available or not exist");
                    break;
                case 5:
                    System.out.println("\n\tEnter new book:");
                    System.out.print("name: ");
                    String name =  scanner.nextLine();
                    System.out.print("author: ");
                    String author = scanner.nextLine();
                    System.out.print("year: ");
                    int year = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("category: ");
                    String category = scanner.nextLine();
                    System.out.print("accessGrade: ");
                    int accessGrade = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("publisher: ");
                    String publisher = scanner.nextLine();
                    System.out.print("publishDate: ");
                    int publishDate = scanner.nextInt();
                    scanner.nextLine();

                    library.insert.execute(new Book(lib, year, accessGrade, name, author, category, publisher, publishDate));

                case 0:
                    System.out.println("\n\tUser menu is closed");
                    return;
            }
        }

    }


    public static void loginLoop(){
        Scanner scanner = new Scanner(System.in);

        while (true){
            System.out.println("\n\tLogin menu");
            System.out.println("1. Login");
            System.out.println("2. Registration");
            System.out.println("0. Exit");
            int select = scanner.nextInt();
            scanner.nextLine();
            switch (select){
                case 1:
                    System.out.println("\n\tEnter the user id:");
                    System.out.print("id: ");

                    int id = scanner.nextInt();
                    if (library.select.isUser(id)){
                        System.out.println("\tUser ");
                        std = library.select.selectUserByID(id).clone();
                        std.toStringUser();
                        userLoop();
                    } else {
                        System.out.println("\tUser does not exist in the database!");
                    }
                    break;

                case 2:
                    System.out.println("\n\tEnter your data:");
                    System.out.print("Name: ");
                    String name =  scanner.nextLine();
                    System.out.print("Surname: ");
                    String surname = scanner.nextLine();
                    System.out.print("BirthYear: ");
                    int birthYear =  scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("University: ");
                    String university =  scanner.nextLine();
                    System.out.print("AccessGrade: ");
                    int accessGrade =  scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Faculty: ");
                    String faculty =  scanner.nextLine();
                    System.out.print("Group: ");
                    String group =  scanner.nextLine();

                    Student st = new Student(name, surname, birthYear, university,accessGrade, lib, faculty, group);
                    library.insert.execute(st);
                    break;
                case 0:
                    System.out.println("You have exited the main menu!");
                    return;

            }
        }
    }

    public static void mainLoop(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("\n\tMain menu");
            System.out.println("1. Show all libraries");
            System.out.println("2. Select library by id");
            System.out.println("3. Add new library ");
            System.out.println("4. Update library by id");
            System.out.println("5. Delete library by id");
            System.out.println("0. Exit");
            int select = scanner.nextInt();
            scanner.nextLine();
            switch (select){

                case 1:
                    ArrayList<Library> libs = library.select.getAllLibraries();
                    System.out.println("\n\tAll libraries");
                    for (Library lib : libs){
                        lib.toStringLib();
                        System.out.println();
                    }
                    break;

                case 2:
                    System.out.print("Lib id: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    lib = library.select.selectLibByID(id);
                    if (lib != null){
                        System.out.println("You select this library:");
                        lib.toStringLib();
                        loginLoop();
                    }else {
                        System.out.println("\tThis library id does not exist");
                    }
                    break;

                case 3:
                    System.out.println("\tEnter all data");
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Location: ");
                    String location = scanner.nextLine();
                    library.insert.execute(new Library(name,location));
                    break;

                case 4:
                    System.out.print("Lib id: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    if(library.select.isLibrary(id)){
                        System.out.println("\tEnter all data");
                        System.out.print("Name: ");
                        name = scanner.nextLine();
                        System.out.print("Location: ");
                        location = scanner.nextLine();
                        library.update.execute(new Library(id, name, location));
                    }else {
                        System.out.println("\tThis library id does not exist");
                    }
                    break;

                case 5:
                    System.out.print("Lib id: ");
                    id = scanner.nextInt();
                    scanner.nextLine();
                    if(library.select.isLibrary(id)){
                        library.delete.execute(library.select.selectLibByID(id));
                    }else {
                        System.out.println("\tThis library id does not exist");
                    }
                    break;

                case 0:
                    System.out.println("\n\t Close Main menu");
                    return;
            }
        }
    }
}
