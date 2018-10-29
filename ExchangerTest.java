import java.util.concurrent.Exchanger;

public class ExchangerTest{
	public static void main(String[] args) {
		Exchanger ex = new Exchanger();
		String str1 = "Thread 1";
		String str2 = "Thread 2";

		Runnable t1 = new TestExchanger(ex, str1);
		Runnable t2 = new TestExchanger(ex, str2);
		
		new Thread(t1, str1).start();
		new Thread(t2, str2).start();
	}
}

class TestExchanger implements Runnable {
	Exchanger ex;
	Object str;
	TestExchanger(Exchanger ex , Object str) {
		this.ex = ex;
		this.str = str;
	}


	public void run() {
		try {
			this.str = ex.exchange(this.str);
			System.out.println(str + "  from thread " + Thread.currentThread().getName());
		} catch(InterruptedException ie) {
			ie.printStackTrace();
		}
	}
}