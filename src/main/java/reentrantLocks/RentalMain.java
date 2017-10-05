package reentrantLocks;

public class RentalMain {
    public static void main(String[] args) {
        System.out.println("Start-----------------------");
        Count count = new Count();
        ReentrantLocks.crateListOfNames(count);
    }
}
