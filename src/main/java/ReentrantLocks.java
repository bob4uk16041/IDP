/*
 "Please write dummy application that would demonstrate usage of ReentrantLocks feature. Be ready to explain it.(for instance : write simple
applications, launch 2 threads consurrently, change state in share variable in thread-1 and process those change in thread-2; modify state(value) of shared
object in thread-2 and return the control to thread-1 )"

 */


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLocks {

    public static void main(String[] args) {
        System.out.println("Start-----------------------");
        crateListOfNames();
        System.out.println("Finish----------------------");
    }

    private static List<String> crateListOfNames() {
        Count count = new Count();
        Thread t2 = new Thread(new Plus(count));
        t2.setName("Thread "+ 2);
        t2.start();

        Thread t1 = new Thread(new Minus(count));
        t1.setName("Thread "+ 1);
        t1.start();
        return count.getListOfThreadNames();

    }

    private static class Resource{
        int share=0;
        boolean state = true;
    }

    private static class Minus implements Runnable {
    	Count count;
    	
    	Minus(Count count){
    		this.count = count;
    	}

		@Override
		public void run() {
			for (int i = 1; i < 6; i++) {
				count.minus();
			}
		}
    }


    private static class Plus implements Runnable {
    	Count count;
    	
    	Plus(Count count){
    		this.count = count;
    	}
    	
		@Override
		public void run() {
			for (int i = 1; i < 6; i++) {
				count.plus();
			}
		}
    }
    
    
    private static class Count{
        private List<String> listOfThreadNames;
        private Resource res;
        private ReentrantLock locker;
        private Condition condition;
        Count(){
            this.res = new Resource();
            locker =  new ReentrantLock();;
            this.condition = locker.newCondition();
            listOfThreadNames = new ArrayList<>();

        }
        public  List<String> getListOfThreadNames(){
            return listOfThreadNames;
        }

        private void plus(){
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
        private void minus(){
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
}
