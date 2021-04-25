import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
	
	public static void main(String[] args) {
		
		try {
			
			System.out.println("connecting to server");
			
			//socket for communicating with server (holding server's ip and port number)
			Socket socket = new Socket("127.0.0.1", 999);
			
			System.out.println("connected to server");
			
			//product to send to server:
			String product = "a";
			
			//input & output streams for socket:
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			
			//send product to outputStream:
			System.out.println("sending product information");
			outputStream.write(product.getBytes());
			
			//response from server:
			byte[] response = new byte[100];
			inputStream.read(response); //read from inputStream and store in response (100 bytes as that the size of request)
			
			//create price of product from bytes held in response:
			String price = new String(response).trim();
			
			System.out.println("recieved response from server. Price is: " + price);
			
			/** close socket */
			socket.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
