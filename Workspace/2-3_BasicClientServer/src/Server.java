import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Server {
	
	/** RUN SERVER FIRST - as this opens a socket on port 999 & waits for client to connect to it */

	public static void main(String[] args) {
		
		
		/////https://stackoverflow.com/questions/9481865/getting-the-ip-address-of-the-current-machine-using-java
		
		/** 2 examples of getting ip address from system (for sending to db along with a free port number) as per stack overflow link above: */
		//===============================
		InetAddress IP;
		try {
			IP = InetAddress.getLocalHost();
			System.out.println("IP of my system is: " + IP.getHostAddress());
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//====================================
		
		//-----------------------------
		
		try(final DatagramSocket socket = new DatagramSocket()){
			  socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
			  String ip = socket.getLocalAddress().getHostAddress();
			  System.out.println("IP of my system is: " + ip);
			  
		} catch (SocketException e1) {
			e1.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		//this is prob best - as described in stackoverflow!! 
		
		//----------------------------
		
		
		
		
		
		
		
		
		
		//server socket with a given port number (if port numb is available then obj is created)
		try {
			ServerSocket serverSocket = new ServerSocket(999); 
			
			System.out.println("server waiting for client");
			
			//this waits for a connection to be made to serverSocket (from client) then creates a new socket with that connection
			Socket socket = serverSocket.accept();
			
			System.out.println("client connected. IP of server is: " + socket.getInetAddress());
			
			//socket has an input & output stream:
			InputStream inputStream = socket.getInputStream(); /** what's read here is what's sent BY the client */
			OutputStream outputStream = socket.getOutputStream(); /** what's here is what's sent TO the client */
			
			//create array buffer for stream:
			byte[] buffer = new byte[1024];
			
			 //read buffer numb of bytes (1024) into input stream and store in buffer:
			inputStream.read(buffer);
			
			System.out.println("recieved from client: " + new String(buffer).trim()); //trim unnecessary characters
			
			//write message to output stream (as bytes)
			outputStream.write("hello from server!".getBytes());
			
			/** finally, CLOSE SOCKETS: */
			
			socket.close();
			serverSocket.close();
			
		} catch (IOException e) {
			System.out.println("port number not available");
			e.printStackTrace();
		} 
		
		

	}

}
