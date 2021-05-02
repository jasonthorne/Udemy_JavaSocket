import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Client {
	
	public static void main(String[] args) {
		
		try {
			
			System.out.println("connecting to server");
			
			//socket for communicating with server (holding server's ip and port number)
			Socket socket = new Socket("127.0.0.1", 999);
			
			/** FIND A FREE PORT : 
			 * https://stackoverflow.com/questions/2675362/how-to-find-an-available-port
			
			ServerSocket serverSocket = new ServerSocket(0);
		    System.out.println("listening on port " + serverSocket.getLocalPort());
		     * */
			
			System.out.println("connected to server");
			
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
			String responseMsg = new String(response).trim();
			
			System.out.println("recieved response from server. message is: " + responseMsg);
			
			/** close socket */
			socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
