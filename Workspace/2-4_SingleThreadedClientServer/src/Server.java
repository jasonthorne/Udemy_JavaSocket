import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


class QuoteService {
	
	//map of product info:
	Map<String, String>productInfo = new HashMap<String, String>();
	
	//constructor:
	public QuoteService() {
		//add product info to map:
		productInfo.put("a", "100");
		productInfo.put("b", "200");
	}
	
	//getter for map holding product info;
	public String getQuote(String product) {
		return productInfo.get(product);
	}
}

public class Server {

	public static void main(String[] args) {
		
		try {
			
			//create QuoteSerevice obj (which holds product info):
			QuoteService quoteService = new QuoteService();
			
			//server socket for recieving connection, using port 999:
			ServerSocket serverSocket = new ServerSocket(999);
			System.out.println("started listening to port 999");
			
			
			/** this is in loop so that when socket is closed after use, serverSocket waits for a new client connection */
			while(true) {
				//serverSocket waiting for client, and creating a new socket with this connection when accepted:
				System.out.println("waiting for client");
				Socket socket = serverSocket.accept(); 
				
				//input & output streams for socket:
				InputStream inputStream = socket.getInputStream();
				OutputStream outputStream = socket.getOutputStream();
				
				System.out.println("waiting for product info from client");
				
				byte request[] = new byte[100];
				inputStream.read(request); //read from inputStream and store in request (100 bytes as that the size of request)
				
				//create product from bytes held in request:
				String product = new String(request).trim();
				
				System.out.println("recieved product: " + product);
				
				//get price of product from quoteSerice:
				String price = quoteService.getQuote(product);
				
				if(price == null) { System.out.println("invalid product"); }
					
				//sent the price back to the client:
				outputStream.write(price.getBytes());
				
				System.out.println("response sent.");
				
				/** close socket: */
				socket.close();
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

}
