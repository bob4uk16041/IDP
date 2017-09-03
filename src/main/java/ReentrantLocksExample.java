import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLocksExample {

    public static void main(String[] args) {

        Resource commonResource= new Resource();
        ReentrantLock locker = new ReentrantLock();

        Thread t1 = new Thread(new CountThread(commonResource, locker));
        t1.setName("Thread "+ 1);
        t1.start();

        Thread t2 = new Thread(new Count(commonResource, locker));
        t2.setName("Thread "+ 2);
        t2.start();

    }
    static class Resource{
        int share=0;
    }

    static class CountThread implements Runnable{

        Resource res;
        ReentrantLock locker;
        CountThread(Resource res, ReentrantLock lock){
            this.res=res;
            locker = lock;
        }
        public void run(){
            locker.lock();
            try{
                for (int i = 1; i <= 5; i++){
                    res.share += 2;
                    System.out.println(Thread.currentThread().getName()+ " " + res.share);
                    Thread.sleep(1000);
                }
            }
            catch(InterruptedException e){
                System.out.println(e.getMessage());
            }
            finally{
                locker.unlock();
            }
        }
    }

    static class Count implements Runnable{
        Resource res;
        ReentrantLock locker;
        Count(Resource res, ReentrantLock lock){
            this.res=res;
            locker = lock;
        }
        public void run(){
            locker.lock();
            try{
                    for (int i = 1; i <= 5; i++){
                        res.share += 3;
                        System.out.println(Thread.currentThread().getName()+ " " + res.share);

                        Thread.sleep(100);
                    }
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
