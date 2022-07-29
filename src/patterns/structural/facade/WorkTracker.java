package patterns.structural.facade;

public class WorkTracker {
    private boolean activeWork;

    public boolean isActiveWork(){
        return activeWork;
    }

    public void startWork(){
        System.out.println("Library is open");
        activeWork = true;
    }

    public void stopWork(){
        System.out.println("Library is closed");
        activeWork = true;
    }
}
