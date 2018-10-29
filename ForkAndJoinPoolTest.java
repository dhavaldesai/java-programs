import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class ForkAndJoinPoolTest {
	public static void main(String[] args) {
		ForkJoinPool fnjPool = new ForkJoinPool(4);
		RecursiveAction ra = new MyRecursiveAction(100);
		fnjPool.invoke(ra);

		RecursiveTask rt = new MyRecursiveTask(100);
		System.out.println(fnjPool.invoke(rt));
	}
}

class MyRecursiveAction extends RecursiveAction {

	private int workload;
	MyRecursiveAction(int workload) {
		this.workload = workload;
	}

	public void compute() {
		if(workload <= 5) {
			System.out.println("End State " + this.workload);
		} else {
			MyRecursiveAction t1 = new MyRecursiveAction(this.workload/2);
			MyRecursiveAction t2 = new MyRecursiveAction(this.workload/2);
			t1.fork();
			t2.fork();
		}

	}
}

class MyRecursiveTask extends RecursiveTask<Integer> {
	private int workload;
	MyRecursiveTask(int workload) {
		this.workload = workload;		
	}	

	public Integer compute() {
		if(workload <= 5) {
			return workload;
		} else {
			MyRecursiveTask t1 = new MyRecursiveTask(this.workload/2);
			MyRecursiveTask t2 = new MyRecursiveTask(this.workload/2);
			t1.fork();
			t2.fork();
			return t1.join() + t2.join();
		}		
	}
}