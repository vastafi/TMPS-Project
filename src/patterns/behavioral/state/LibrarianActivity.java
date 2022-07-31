package patterns.behavioral.state;

import internal.models.Library;
import internal.services.LibrarianLoop;
import patterns.creational.prototype.Student;

public class LibrarianActivity implements Activity {
    @Override
    public void doIt(Library lib, Student usr, Library library) {
        new LibrarianLoop(lib, usr, library);
    }
}
