package patterns.behavioral.command;

import internal.models.Library;
import patterns.creational.prototype.Student;
import patterns.structural.adapter.Book;

public interface Command {
    public void  execute(Student student);
    public void  execute(Book book);
    public void  execute(Library library);
}
