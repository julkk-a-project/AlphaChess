// allmänt tillgänglig på nätet, editerat från t.ex. http://zerioh.tripod.com/ressources/sockets.html

package network;

import java.io.*;
import java.net.*;

public class Provider3 {
    ServerSocket providerSocket;
    Socket connection = null;
    int port = 2004;
    String message;
    PrintWriter output;
    BufferedReader input;
	String [] ok = new String [50];    
	int i = 0;
	private boolean turnNotOver;
	//private String sistaHalsning;
    
    public Provider3(){}
    
    
    public boolean connect() {
        	try{
                //1. creating a server socket
                providerSocket = new ServerSocket(port, 10);
                //2. Wait for connection
                System.out.println("Waiting for connection");
                appendLog("Waiting for connection");
                connection = providerSocket.accept();
                System.out.println("Connection received from " + connection.getInetAddress().getHostName() + " and accepted on " + connection.getLocalSocketAddress() + " blahaa: " + connection.getOutputStream());
                appendLog("Connection received from " + connection.getInetAddress().getHostName() + " and accepted on " + connection.getLocalSocketAddress() + " blahaa: " + connection.getOutputStream());
                //3. get Input and Output streams
                input = new BufferedReader (new InputStreamReader (connection.getInputStream()));
                output = new PrintWriter (new BufferedWriter ( new OutputStreamWriter(connection.getOutputStream())), true);
                sendMessage("Connection successful");

                //4. The two parts communicate via the input and output streams
                return true;
            }
            catch(IOException ioException){
                ioException.printStackTrace();
                return false;
            }

    }
    
    public void disconnect() {

    	System.out.println("connection closing");
    	appendLog("connection closing");
    	//4: Closing connection
    	try{
    		input.close();
    		output.close();
    		providerSocket.close();
    	}
    	catch(IOException ioException){
    		ioException.printStackTrace();
    	}
    }
    
    
    
    

	//SendMessage
    public void run(int[] cords)
    {

    	message = "";
    	
    	do {
    		try{
    			//message = (String)JOptionPane.showInputDialog("Send info to client");
    			//message = (String)input.readLine(); // läser in vad servern skickat
    			//System.out.println("server>" + message); 

    			//System.out.println("Sending cords "+cords[0]+","+cords[1]); //<-- this is dumb
    			message = cords[0]+","+cords[1];
    			sendMessage(message); // skickar meddelandet add till metoden sendMessage 

    			message = (String)input.readLine(); // läser in vad servern skickat
    			System.out.println("client>" + message);
    			main.Main.itMatrix.hitReader(message, cords[0], cords[1]);
    			appendLog("client>" + message);

    			message = "copy";  
    			sendMessage(message); // skickar bye till metoden sendMessage

    			//sistaHalsning = input.readLine(); // läser svaret
    			//System.out.println(sistaHalsning);
    		}
    		catch(Exception e){
    			System.err.println("data received in unknown format: \""+message+"\"");
    			appendLog("data received in unknown format: \""+message+"\"");
    		}
    	} while(!message.equals("copy"));
    }
    
    

    //WaitForMessage
    public boolean run() {

            turnNotOver = true;
        	message = "";
        	
            do{
                try{
                	//System.out.println("kommit hit");
                    message = (String)input.readLine();
                    //add message handling here
                    System.out.println("client>" + message); //only shows message
                    appendLog("client>" + message);
                    
                    //Waits untill message is a cordinate
                    if (isCordinate(message)) {
                    		sendMessage(cordinateHandler(message));
                        }
                    
  
                    /*
                    if (message.equals("copy")) {
                        //turnNotOver = false;
                    }*/
                }
                catch(Exception classnot){
        			//System.err.println("data received in unknown format: \""+message+"\"");
        			//System.out.println(classnot);
                }
            }while(!message.equals("copy"));
            System.out.println("HostStopsListening");
            appendLog("HostStopsListening");
            
            turnNotOver = false;

        return turnNotOver;
    }
    
    
    
	void sendMessage(String msg){
        try{
            output.println(msg);
            output.flush();
            System.out.println("server>" + msg);
            appendLog("server>" + msg);
        }
        catch(Exception ioException){
            ioException.printStackTrace();
        }
    }
    public static void main(String args[]) {
        Provider3 server = new Provider3();
        while(true){
            server.run();
        }
    }
    
    ///////////////////
    //Personal edits://
    ///////////////////
    
    /*
     * sets port to what you want to connect to.
     */
    public void setPort(int port) {
    	this.port = port;
    }
    
    /*
     * use me to get port from memory
     */
    public int getPort() {
    	return port;
    }
    
    /*
     * use me to send stuff to log
     */
    public static void appendLog(String string) {
    	//add stuff that makes this add stuff to log
    	main.Main.addToBuffer(string);
    }
    
    /*
     * use me to determen if a cordinate was sent
     */
    public static boolean isCordinate(String message) {
    	try {
        	@SuppressWarnings("unused")
			String[] cords = message.split(",", 2);
        	return true;
    	}catch(Exception e){
    		System.out.println("isCordinateSaysNo");
    		appendLog(e+"");
    		return false;
    	}
    }

    /*
     * i am used to see if game is lost
     */
    public boolean isLost(String message) {
    	return false;
    }
    
    /*
     * use me to change what the matrixes look like, and return HitMiss, shipSunk and WinLoose message.
     */
    static String cordinateHandler(String message) {
    	String string = "";
    	
    	//handle message here
    	
    	String[] cords = message.split(",");

    	
    	string += main.Main.myMatrix.hitOrMiss(cords[0], cords[1]);
    	
    	
    	
    	
		return string;
	}
    
    
    
    
    
}