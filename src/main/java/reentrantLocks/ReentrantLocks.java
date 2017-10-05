package reentrantLocks;
/*
 "Please write dummy application that would demonstrate usage of reentrantLocks.ReentrantLocks feature. Be ready to explain it.(for instance : write simple
applications, launch 2 threads consurrently, change state in share variable in thread-1 and process those change in thread-2; modify state(value) of shared
object in thread-2 and return the control to thread-1 )"
*/




public class ReentrantLocks {

    public static void crateListOfNames(Count count) {
        Thread t2 = new Thread(new Plus(count));
        t2.setName("Thread "+ 2);
        t2.start();

        Thread t1 = new Thread(new Minus(count));
        t1.setName("Thread "+ 1);
        t1.start();
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

}
