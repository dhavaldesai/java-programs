public class WaitNotifyTest {
	public static void main(String[] args) {
		
	}
}

class MonitorObject {}

class MyWaitNotify {
	MonitorObject mo = new MonitorObject();

	public void doWait() {
		synchronized(mo) {
			try {
				mo.wait();
			} catch(InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}

	public void doNotify() {
		synchronized(mo) {
			mo.notifyALl();
		}
	}
}