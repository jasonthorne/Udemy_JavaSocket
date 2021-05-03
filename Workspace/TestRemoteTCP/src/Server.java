import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/** MULTI threaded application. */


//class for running a socket on a new thread:
class ServiceThread extends Thread {
	 
	Socket socket;
	
	//constructor:
	ServiceThread(Socket socket){
		this.socket = socket;
	}
	
	//run method of Thread class deals with socket:
	public void run() {
		
		try {
			
			//input & output streams for socket:
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			byte request[] = new byte[100];
			inputStream.read(request); //read from inputStream and store in request (100 bytes as that the size of request)
			
			//create request string from bytes held in request:
			String requestString = new String(request).trim();
			
			System.out.println("recieved message from client: " + requestString);
	
			if(requestString == null) { System.out.println("invalid message"); }
				
			//sent the price back to the client:
			outputStream.write(new String("Hi from Server!").getBytes());
			
		} catch (IOException e) { e.printStackTrace(); }
			
	}
}

public class Server {

	public static void main(String[] args) {
		
		try {
			
			//server socket for recieving connection, using port 999:
			ServerSocket serverSocket = new ServerSocket(999);
			//System.out.println("started listening to port:" + serverSocket.getLocalPort());
			
			
			/** this is in loop so that when socket is closed after use, serverSocket waits for a new client connection */
			while(true) {
				//serverSocket waiting for client, and creating a new socket with this connection when accepted:
				System.out.println("waiting for client");
				Socket socket = serverSocket.accept(); 
				
				//create new thread to service client. passing it the freshly accepted socket:
				System.out.println("starting a new thread which will service the client");
				new ServiceThread(socket).run();
				
				/** close socket: */
				socket.close();
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
