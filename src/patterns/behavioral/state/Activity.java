package patterns.behavioral.state;

import patterns.creational.prototype.Student;

public interface Activity {
    public void doIt(Library lib, Student usr, Library library);
}
