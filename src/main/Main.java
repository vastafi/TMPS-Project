package main;

import internal.models.Library;
import internal.models.MainData;
import patterns.behavioral.command.DeleteCommand;
import patterns.behavioral.command.InsertCommand;
import patterns.behavioral.command.SelectCommand;
import patterns.behavioral.command.UpdateCommand;
import patterns.behavioral.state.LibrarianActivity;
import patterns.behavioral.state.UserActivity;
import patterns.creational.prototype.Student;

import patterns.structural.facade.WorkTracker;


import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Library lib;
    static Student usr;
    static WorkTracker workTracker = new WorkTracker();
    static Library library = new Library(workTracker,
            new InsertCommand(),
            new UpdateCommand(),
            new SelectCommand(),
            new DeleteCommand());

    public static void main(String[] args) {
        mainLoop();
    }


    public static void loginLoop(){
        Scanner sc = new Scanner(System.in);

        while (true){
            System.out.println("\n\tLogin menu");
            System.out.println("1. Login");
            System.out.println("2. Registration");
            System.out.println("0. Exit");
            int select = sc.nextInt();
            sc.nextLine();
            switch (select){
                case 1:
                    System.out.println("\n\tEnter the user id:");
                    System.out.print("Id: ");

                    int id = sc.nextInt();
                    if (library.select.isUser(id)){
                        System.out.println("\tUser ");
                        usr = library.select.selectUserByID(id).clone();
                        usr.toStringUser();

                        MainData usrLoop  = new MainData(lib, usr, library);
                        if (usr.getAccessGrade() > 4){
                            MainData.setActivity(new LibrarianActivity());
                        }else {
                            MainData.setActivity(new UserActivity());
                        }

                        usrLoop.doIt();
                        return;
                    } else {
                        System.out.println("\tUser does not exist in the database");
                    }
                    break;
                case 2:
                    System.out.println("\n\tEnter your data:");
                    System.out.print("Name: ");
                    String name =  sc.nextLine();
                    System.out.print("Surname: ");
                    String surname = sc.nextLine();
                    System.out.print("BirthYear: ");
                    int birthYear =  sc.nextInt();
                    sc.nextLine();
                    System.out.print("University: ");
                    String university =  sc.nextLine();
                    System.out.print("AccessGrade: ");
                    int accessGrade =  sc.nextInt();
                    sc.nextLine();
                    System.out.print("Faculty: ");
                    String faculty =  sc.nextLine();
                    System.out.print("Group: ");
                    String group =  sc.nextLine();

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
        Scanner sc = new Scanner(System.in);
        while (true){
            System.out.println("\n\tMain menu");
            System.out.println("1. Show all libraries");
            System.out.println("2. Select library by id");
            System.out.println("3. Add new library ");
            System.out.println("4. Update library by id");
            System.out.println("5. Delete library by id");
            System.out.println("0. Exit");
            int select = sc.nextInt();
            sc.nextLine();
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
                    int id = sc.nextInt();
                    sc.nextLine();
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
                    String name = sc.nextLine();
                    System.out.print("Location: ");
                    String location = sc.nextLine();
                    library.insert.execute(new Library(name,location));
                    break;
                case 4:
                    System.out.print("Lib id: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    if(library.select.isLibrary(id)){
                        System.out.println("\tEnter all data");
                        System.out.print("Name: ");
                        name = sc.nextLine();
                        System.out.print("Location: ");
                        location = sc.nextLine();
                        library.update.execute(new Library(id, name, location));
                    }else {
                        System.out.println("\tThis library id does not exist");
                    }
                    break;
                case 5:
                    System.out.print("Lib id: ");
                    id = sc.nextInt();
                    sc.nextLine();
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
