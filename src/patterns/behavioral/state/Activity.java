package patterns.behavioral.state;

import internal.models.Library;
import patterns.creational.prototype.Student;

public interface Activity {
    public void doIt(Library lib, Student usr, Library library);
}
