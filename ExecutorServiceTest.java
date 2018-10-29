import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class ExecutorServiceTest {
	public static void main(String[] args) {
		//ExecutorService  pool =  Executors.newFixedThreadPool(1);
		//ExecutorService pool = Executors.newSingleThreadExecutor();
		ExecutorService pool = Executors.newScheduledThreadPool(10);
		List<Callable<String>> list = new ArrayList<Callable<String>>();
		/*list.add(new ServiceRunnable());
		list.add(new ServiceRunnable());
		try{
			pool.invokeAll(list);
		}catch(InterruptedException ie) {
			ie.printStackTrace();
		}*/
		Future future = pool.submit(new ServiceRunnable());
		try{
		System.out.println("Here" + future.get(1, TimeUnit.SECONDS));
		}catch(InterruptedException | ExecutionException | TimeoutException ie) {
			ie.printStackTrace();
		}
		pool.shutdownNow();
	}

}

class ServiceRunnable implements Callable {
	public String call() throws Exception{
		for(int i = 0; i < 10; i++) {
			System.out.println(i + "  : " + Thread.currentThread().getName());
			Thread.sleep(1000);
		}
		return Thread.currentThread().getName();
	}
}