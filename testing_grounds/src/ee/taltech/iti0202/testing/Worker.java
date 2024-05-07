package ee.taltech.iti0202.testing;

public class Worker {
    private String worker;
    
    public Worker(String worker) {
        this.worker = worker;
    }
    
    public boolean doWork() {
        return false;
    }

    public static void main(String[] args) {
        int a = Integer.MAX_VALUE;
        a++;
        System.out.println(a);
        Worker worker = null;
        worker.doWork();
    }
}