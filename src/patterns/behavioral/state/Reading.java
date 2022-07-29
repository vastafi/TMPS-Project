package patterns.behavioral.state;

public class Reading  implements Activity {
    @Override
    public void doIt() {
        System.out.println("This book is currently being read");
    }
}
