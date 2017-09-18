/*
 "Please write dummy application that would demonstrate usage of ReentrantLocks feature. Be ready to explain it.(for instance : write simple
applications, launch 2 threads consurrently, change state in share variable in thread-1 and process those change in thread-2; modify state(value) of shared
object in thread-2 and return the control to thread-1 )"

 */


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLocksExample {

    public static void main(String[] args) {
        System.out.println("Start-----------------------");
        Count count = new Count();
        
        Thread t1 = new Thread(new Minuss(count));
        t1.setName("Thread "+ 1);
        t1.start();

        Thread t2 = new Thread(new Pluss(count));
        t2.setName("Thread "+ 2);
        t2.start();

    }
    static class Resource{
        int share=0;
        boolean state = true;
    }
    
    static class Minuss  implements Runnable {
    	Count count;
    	
    	Minuss(Count count){
    		this.count = count;
    	}

		@Override
		public void run() {
			for (int i = 1; i < 6; i++) {
				count.minus();
			}
		}
    }

    
    static class Pluss implements Runnable {
    	Count count;
    	
    	Pluss(Count count){
    		this.count = count;
    	}
    	
		@Override
		public void run() {
			for (int i = 1; i < 6; i++) {
				count.plus();
			}
		}
    }
    
    
    static class Count{

        Resource res;
        ReentrantLock locker;
        Condition condition;
        Count(){
            this.res = new Resource();
            locker =  new ReentrantLock();;
            this.condition = locker.newCondition();

        }
        public void plus(){
            locker.lock();
            try{
            	while (res.state) {
            		condition.await();
            	}
            	res.share += Math.round(Math.random()*20);
                System.out.println(Thread.currentThread().getName()+ " " + res.share + " state="+res.state);
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
