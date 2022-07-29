package patterns.behavioral.command;

import internal.models.Library;
import patterns.creational.prototype.Student;
import patterns.creational.singleton.SingletonDB;
import patterns.structural.adapter.Book;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class SelectCommand {
    public Connection conn = SingletonDB.getInstance();

    public boolean isUser(int id){
        String sql = "select exists(select 1 from users where user_id="+ id+")" ;

        try{
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            return result.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isLibrary(int id){
        String sql = "select exists(select 1 from libraries where lib_id="+ id+")" ;

        try{
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            return result.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isBook(int id){
        String sql = "select exists(select 1 from books where book_id="+ id+")" ;

        try{
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            return result.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isAvailableBook(int id, int lib_id){
        String sql = "select exists(select 1 from books where book_id="+ id+" and available=TRUE and library_id="+lib_id+")" ;

        try{
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            return result.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isUsersBook(int id, int lib_id, int user_id){
        String sql = "select exists(select 1 from books where book_id="+ id+" and available=FALSE and library_id="+lib_id+" and user_id="+user_id+")" ;

        try{
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            return result.next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



    public Student selectUserByID(int id){
        String sql = "select * from users where user_id="+ id;
        Student student;
        try{
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result.next()){
                int user_id = result.getInt(1);
                String name = result.getString(2);
                String surname = result.getString(3);
                int birthDate = result.getInt(4);
                String university = result.getString(5);
                int accessGrade = result.getInt(6);
                int library_id =result.getInt(7);
                String faculty = result.getString(8);
                String grp = result.getString(9);
                student = new Student(user_id, name,surname, birthDate,university,accessGrade,
                        selectLibByID(library_id),faculty,grp);
                return student;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public Library selectLibByID(int id){
        String sql = "select * from libraries where lib_id="+ id;
        try{
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            if(result.next()){
                int lib_id = result.getInt(1);
                String name = result.getString("name");
                String location = result.getString("location");

                Library tempLib = new Library(lib_id,name,location);
                tempLib.setNrUsers();
                tempLib.setNrBooks();

                return tempLib;


            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Library> getAllLibraries(){
        ArrayList<Library> libraries = new ArrayList<>();

        String sql = "select * from libraries";
        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);

            while (result.next()){
                int id = result.getInt("lib_id");
                String name = result.getString("name");
                String location = result.getString("location");

                Library tempLib = new Library(id,name,location);
                tempLib.setNrUsers();
                tempLib.setNrBooks();

                libraries.add(tempLib);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return libraries;
    }

    public ArrayList<Book> getBooksByUserID(int userId, int lib_id) {
        String sql = "select * from books where user_id=" + userId + " and library_id=" + lib_id;
        ArrayList<Book> tempBooks = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()){
                int book_id = rs.getInt("book_id");
                String name = rs.getString("name");
                String author = rs.getString("author");
                String category =rs.getString("category");
                int year = rs.getInt("year");
                int accessGrade = rs.getInt("accessGrade");
                boolean available = rs.getBoolean("available");
                String publisher = rs.getString("publisher");
                int publishDate = rs.getInt("publishDate");
                int library_id = rs.getInt("library_id");
                int user_id = rs.getInt("user_id");

                Book tmpBook =new Book(book_id, name,author, category, year, accessGrade, available,publisher,publishDate,
                        selectLibByID(library_id), user_id) ;
                tempBooks.add(tmpBook);
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return tempBooks;
    }

    public ArrayList<Book> getAvailableBooksByUserID(int userId, int lib_id) {
        String sql = "select * from books where user_id=" + userId + " and library_id=" + lib_id + " and available=TRUE" ;
        ArrayList<Book> tempBooks = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()){
                int book_id = rs.getInt("book_id");
                String name = rs.getString("name");
                String author = rs.getString("author");
                String category =rs.getString("category");
                int year = rs.getInt("year");
                int accessGrade = rs.getInt("accessGrade");
                boolean available = rs.getBoolean("available");
                String publisher = rs.getString("publisher");
                int publishDate = rs.getInt("publishDate");
                int library_id = rs.getInt("library_id");
                int user_id = rs.getInt("user_id");

                Book tmpBook =new Book(book_id, name,author, category, year, accessGrade, available,publisher,publishDate,
                        selectLibByID(library_id), user_id) ;
                tempBooks.add(tmpBook);
            }


        }catch (Exception e){
            e.printStackTrace();
        }
        return tempBooks;
    }
    public Book getBookByID(int id){
        String sql = "select * from books where book_id=" +  id;
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            if (rs.next()){
                int book_id = rs.getInt("book_id");
                String name = rs.getString("name");
                String author = rs.getString("author");
                String category =rs.getString("category");
                int year = rs.getInt("year");
                int accessGrade = rs.getInt("accessGrade");
                boolean available = rs.getBoolean("available");
                String publisher = rs.getString("publisher");
                int publishDate = rs.getInt("publishDate");
                int library_id = rs.getInt("library_id");
                int user_id = rs.getInt("user_id");

                return new Book(book_id, name,author, category, year, accessGrade, available,publisher,publishDate,
                        selectLibByID(library_id), user_id) ;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public int getLastInsertedUserID(){
        String sql = "SELECT currval(pg_get_serial_sequence('users','user_id'))";

        try {
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(sql);
            if (result.next()){
                return result.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return 0;
    }

}
