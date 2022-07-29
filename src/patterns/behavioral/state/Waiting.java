package patterns.behavioral.state;

public class Waiting implements Activity {
    @Override
    public void doIt() {
        System.out.println("This book is ready to read");
    }
}
