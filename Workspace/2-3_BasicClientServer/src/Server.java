import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		
		//server socket with a given port number (if port numb is available then obj is created)
		try {
			ServerSocket serverSocket = new ServerSocket(999);
			
			//this waits for a connection to be made to this (from client) then accepts it:
			Socket socket = serverSocket.accept();
			
			//socket has an input & output stream:
			InputStream inputStream = socket.getInputStream(); /** what's read here is what's sent BY the client */
			OutputStream outputStream = socket.getOutputStream(); /** what's here is what's sent TO the client */
			
			//create array buffer for stream:
			byte[] buffer = new byte[1024];
			
			 //read buffer numb of bytes (1024) into input stream:
			inputStream.read(buffer);
			
			System.out.println("recieved from client: " + new String(buffer).trim()); //trim unnecessary characters
			
			//write message to output stream (as bytes)
			outputStream.write("hello from server!".getBytes());
			
			
		} catch (IOException e) {
			System.out.println("port number not available");
			e.printStackTrace();
		}
		
		

	}

}
