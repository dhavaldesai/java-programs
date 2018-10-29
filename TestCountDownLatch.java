import java.util.concurrent.CountDownLatch;

class TestCountDownLatch {

	public static void main(String[] args) {
		TestCountDownLatch test = new TestCountDownLatch();

		CountDownLatch cld = new CountDownLatch(3);

		Thread t1 = new Thread(new Runnable(){
			public void run() {
				try {
					cld.countDown();
					Thread.sleep(1000);
					cld.countDown();
					Thread.sleep(1000);
					cld.countDown();					
					System.out.println("Count Down Finish");
				} catch(InterruptedException ie) {
					ie.printStackTrace();
				}
			}
		});

		Thread t2 = new Thread(new Runnable(){
			public void run() {
				try {
					System.out.println("Waiting for Count Down");
					cld.await();
					System.out.println("Started after count Down");
				} catch(InterruptedException ie) {
					ie.printStackTrace();
				}
			}
		});

		t2.start();
		t1.start();
		
	}
}
