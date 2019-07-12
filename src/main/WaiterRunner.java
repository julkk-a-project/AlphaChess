package main;

public class WaiterRunner implements Runnable {

	private Waiter waiter;
	
	public WaiterRunner(Waiter waiter){
		this.waiter = waiter;
	}
	
	@Override
	public void run() {
		waiter.waiter();

	}
	
	public void runWaker(){
		try {
			//Thread.sleep(1); //WAS 5000
			waiter.waker();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
