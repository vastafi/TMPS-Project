package internal.models;

import patterns.behavioral.state.Activity;
import patterns.behavioral.state.LibrarianActivity;
import patterns.behavioral.state.UserActivity;
import patterns.creational.prototype.Student;

public class MainData {
    public static Activity activity;
    public Library lib;
    public Student usr;

    public Library library;

    public MainData(Library lib, Student usr, Library library) {
        this.lib = lib;
        this.usr = usr;
        this.library = library;
    }

    public static void setActivity(Activity activity) {
        MainData.activity = activity;
    }

    public static void changeActivity(){
        if (activity instanceof LibrarianActivity){
            setActivity(new UserActivity());
        }else setActivity(new LibrarianActivity());
    }

    public void doIt(){
        activity.doIt(lib,usr,library);
    }
}
