package main;

public class Waiter {

	private Object o1 = new Object();
	private Object o2 = new Object();
	private boolean wait = true;
	
	public synchronized void waiter() {
		wait = true;
		while (wait) {
			try {
				wait();
				wait = false;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		
	}
	public synchronized void waker() {		
			notifyAll();
	}

}
