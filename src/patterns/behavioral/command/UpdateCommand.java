package patterns.behavioral.command;

import internal.models.Library;
import patterns.creational.prototype.Student;
import patterns.creational.singleton.SingletonDB;
import patterns.structural.adapter.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateCommand implements Command{
    public Connection conn = SingletonDB.getInstance();


    @Override
    public void execute(Student student) {

    }

    @Override
    public void execute(Book book) {

    }

    @Override
    public void execute(Library library) {
        String sql = "update libraries set name=?, location=? where lib_id=?";

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, library.getName());
            statement.setString(2, library.getLocation());
            statement.setInt(3, library.getId());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing library was updated successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void makeBookNotAvailable(int book_id, int user_id ){
        String sql = "update books set available=FALSE, user_id="+user_id+" where book_id="+book_id;

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("\tBook was taken successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void makeBookAvailable(int book_id, int user_id ){
        String sql = "update books set available=TRUE, user_id="+user_id+" where book_id="+book_id;

        try {
            PreparedStatement statement = conn.prepareStatement(sql);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("\tBook was taken successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
