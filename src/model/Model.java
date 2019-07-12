package model;

public class Model {

	public static String lastEvent;
	
	public Model() {
		
	}

	public String getLastEvent() {
		return lastEvent;
	}

	public void setLastEvent(String hit, String sunk) {
		lastEvent = hit+","+sunk;
		System.out.println(lastEvent);
		
	}


}
