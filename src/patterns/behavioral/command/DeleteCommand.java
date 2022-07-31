package patterns.behavioral.command;

import internal.models.Library;
import patterns.creational.prototype.Student;
import patterns.creational.singleton.SingletonDB;
import patterns.structural.adapter.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteCommand implements Command{
    Connection conn = SingletonDB.getInstance();

    @Override
    public void execute(Student student) {
        String sql = "delete from users where user_id=" + student.getId();
        try{
            PreparedStatement statement = conn.prepareStatement(sql);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A user was deleted successfully!");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void execute(Book book) {
        String sql = "delete from books where book_id=" + book.getId();
        try{
            PreparedStatement statement = conn.prepareStatement(sql);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A book was deleted successfully!");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    @Override
    public void execute(Library library) {
        String sql = "delete from libraries where lib_id=" + library.getId();
        try{
            PreparedStatement statement = conn.prepareStatement(sql);

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A library was deleted successfully!");
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
