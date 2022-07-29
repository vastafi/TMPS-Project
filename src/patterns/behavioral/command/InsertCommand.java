package patterns.behavioral.command;

import internal.models.Library;
import patterns.creational.prototype.Student;
import patterns.creational.singleton.SingletonDB;
import patterns.structural.adapter.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsertCommand implements Command{
    Connection conn = SingletonDB.getInstance();

    @Override
    public void execute(Student student) {
        String sql = "INSERT INTO users (name, surname, birthYear, university, accessGrade, library_id, " +
                " faculty, grp) values (?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, student.getName());
            statement.setString(2, student.getSurname());
            statement.setInt(3, student.getBirthYear());
            statement.setString(4, student.getUniversity());
            statement.setInt(5, student.getAccessGrade());
            statement.setInt(6, student.getLibrary().getId());
            statement.setString(7, student.getFaculty());
            statement.setString(8, student.getGroup());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0){
                System.out.println("A new user was inserted successfully!");
                System.out.println("\t Your login id: " + new SelectCommand().getLastInsertedUserID());
            }
        }catch (Exception exception){
            exception.printStackTrace();
        }
    }

    @Override
    public void execute(Book book) {
        String sql  = "insert into books (name, author, category, year, accessGrade, available, publisher, " +
                "publishDate, library_id) values (?,?,?,?,?,?,?,?,?)";
        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getCategory());
            statement.setInt(4, book.getYear());
            statement.setInt(5, book.getAccessGrade());
            statement.setBoolean(6, book.isAvailable());
            statement.setString(7, book.getPublisher());
            statement.setInt(8, book.getPublishYear());
            statement.setInt(9, book.getLibrary().getId());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0){
                System.out.println("A new book was inserted successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(Library library){
        String sql = "insert into libraries (name, location) values (?,?)";
        try{
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, library.getName());
            statement.setString(2, library.getLocation());
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0){
                System.out.println("A new library was inserted successfully!");
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }
}
