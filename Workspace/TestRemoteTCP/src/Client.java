import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
	
	public static void main(String[] args) {
		
		try {
			
			//socket for communicating with server (holding server's ip and port number)
			Socket socket = new Socket("127.0.0.1", 999);
			
			//product to send to server:
			String message = "client say's hi!";
			
			//input & output streams for socket:
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			//send product to outputStream:
			System.out.println("sending message to server");
			outputStream.write(message.getBytes());
			
			//response from server:
			byte[] response = new byte[100];
			inputStream.read(response); //read from inputStream and store in response (100 bytes as that the size of request)
			
			//create response from server from bytes held in response:
			String responseString = new String(response).trim();
			
			System.out.println("response from server: " + responseString);
			
			/** close socket */
			socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
