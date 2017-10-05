package reentrantLocks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Count{
    private List<String> listOfThreadNames;
    private Resource res;
    private ReentrantLock locker;
    private Condition condition;
    public Count(){
        this.res = new Resource();
        locker =  new ReentrantLock();;
        this.condition = locker.newCondition();
        listOfThreadNames = new ArrayList<>();

    }
    private static class Resource{
        int share=0;
        boolean state = true;
    }

    public  List<String> getListOfThreadNames(){
        return listOfThreadNames;
    }

    public void plus(){
        locker.lock();
        try{
            while (res.state) {
                condition.await();
            }
            res.share += Math.round(Math.random()*20);
            System.out.println(Thread.currentThread().getName()+ " " + res.share + " state="+res.state);
            listOfThreadNames.add(Thread.currentThread().getName());
            res.state = !res.state;
            condition.signalAll();

        }
        catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
        finally{
            locker.unlock();

        }
    }
    public void minus(){
        locker.lock();

        try{
            while (!res.state) {
                condition.await();
            }
            res.share -= Math.round(Math.random()*10);;
            System.out.println(Thread.currentThread().getName()+ " " + res.share + " state="+res.state);
            res.state = !res.state;
            listOfThreadNames.add(Thread.currentThread().getName());
            condition.signalAll();
        }
        catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
        finally{
            locker.unlock();

        }
    }
}
