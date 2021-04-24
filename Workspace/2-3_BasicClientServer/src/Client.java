import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
	
	public static void main(String[] args) {
		
		try {
			//client socket with IP address and port number of machine where server is running (same IP address in this local example):
			/** get this from db when attempting to connect. Have server send this info when it fires up */
			Socket socket = new Socket("127.0.0.1", 999);
			
			InputStream inputStream = socket.getInputStream(); //for retrieving info FROM server
			OutputStream outputStream = socket.getOutputStream(); //for sending TO server
			
			//send a message to the server (as bytes):
			outputStream.write("Hullo from client!".getBytes());
			
			//response from server:
			byte[] buffer = new byte[1024]; //create array buffer for stream
			inputStream.read(buffer); //read buffer numb of bytes (1024) of input stream and store in buffer
			
			//print message from server:
			System.out.println("message from seerver: "+ new String(buffer).trim());
			
			/** finally, CLOSE SOCKET: */
			socket.close();
			
		} catch (IOException e) {
			
			System.out.println("uh oh!");
			e.printStackTrace();
		}
	}

}
