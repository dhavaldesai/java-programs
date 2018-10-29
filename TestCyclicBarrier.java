import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.BrokenBarrierException;

public class TestCyclicBarrier {
	public static void main(String[] args) {
		CyclicBarrier cb = new CyclicBarrier(2);
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try{
					System.out.println("Starting Thread 1");
					cb.await();
					System.out.println("Starting Thread 1 after cb");
				} catch(InterruptedException | BrokenBarrierException ie) {
					ie.printStackTrace();
				}
			}
		}, "Thread 1");

		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println("Starting Thread 2");
					cb.await();
					System.out.println("Starting Thread 2 after cb");
				} catch(InterruptedException | BrokenBarrierException ie) {
					ie.printStackTrace();
				}
			}
		}, "Thread 2");
		t1.start();
		t2.start();
	}	
}