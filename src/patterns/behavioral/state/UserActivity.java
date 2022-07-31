package patterns.behavioral.state;

import internal.models.Library;
import internal.services.UserLoop;
import patterns.creational.prototype.Student;

public class UserActivity implements Activity {
    @Override
    public void doIt(Library lib, Student usr, Library library) {
        new UserLoop(lib, usr, library);
    }
}
