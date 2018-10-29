import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(1);
		SemaphoreObj obj = new SemaphoreObj("SharedObj", semaphore);
		Runnable r1 = new SemaphoreRunnable(obj);
		Runnable r2 = new SemaphoreRunnable(obj);

		new Thread(r1, "Thread 1").start();
		new Thread(r2, "Thread 2").start();
	}
}

class SemaphoreRunnable implements Runnable {
	SemaphoreObj obj;
	SemaphoreRunnable(SemaphoreObj obj) {
		this.obj = obj;
	}

	public void run() {

		for(int i = 0; i < 10; i++)
			System.out.println(obj.incrCount() + "  : " + Thread.currentThread().getName());
	}
}

class SemaphoreObj {
	private String name;
	private int count;
	private Semaphore semaphore;
	SemaphoreObj(String name, Semaphore semaphore) {
		this.name = name;
		count = 0;
		this.semaphore = semaphore;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;	
	}

	public int incrCount() {
		try {
		//semaphore.acquire();
		Thread.sleep(500);
		} catch(InterruptedException ie)  {
			ie.printStackTrace();
		}
		int tempCount = count;
		tempCount++;
		count = tempCount;
		//semaphore.release();
		return count;
	}

}