package internal.models;

import patterns.behavioral.command.Command;
import patterns.creational.prototype.User;
import patterns.creational.singleton.SingletonDB;
import patterns.structural.adapter.Book;
import patterns.structural.facade.WorkTracker;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Library {
    public int id;
    public String name;
    public String location;
    public int nrUsers;
    public int nrBooks;
    public WorkTracker workTracker;
    public ArrayList<Book> books;

    public Command insert;
    public UpdateCommand update;
    public SelectCommand select;
    public Command delete;

    public Library(String name, String location){
        this.name = name;
        this.location = location;
    }

    public Library(int id, String name, String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public Library(WorkTracker workTracker, Command insert, UpdateCommand update, SelectCommand select, Command delete) {
        this.workTracker = workTracker;
        this.insert = insert;
        this.update = update;
        this.select = select;
        this.delete = delete;
    }


    public void toStringLib(){
        System.out.println("id : \t " + getId());
        System.out.println("name: \t "+ getName());
        System.out.println("location: \t" + getLocation());
        System.out.println("Nr of users: \t " + getNrUsers());
        System.out.println("Nr of books: \t " + getNrBooks());
    }
    //take all books that is available
    public ArrayList<Book> getAllBooks(){
        return new ArrayList<Book>();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNrUsers() {
        return nrUsers;
    }



    public void setNrUsers() {
        String sql = "select count(*) as total from users where library_id=" + getId();
        try {
            Statement statement = SingletonDB.getInstance().createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                this.nrUsers = result.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getNrBooks() {
        return nrBooks;
    }

    public void setNrBooks() {
        String sql = "select count(*) as total from books where library_id=" + getId();
        try {
            Statement statement = SingletonDB.getInstance().createStatement();
            ResultSet result = statement.executeQuery(sql);
            while (result.next()){
                this.nrBooks = result.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void giveBook(Book book, User user){
        book.setAvailable(false);
        //write what book is given to user in db
    }
}
