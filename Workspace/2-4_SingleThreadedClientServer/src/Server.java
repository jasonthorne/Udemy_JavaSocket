import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		
		try {
			
			//server socket for recieving connection, using port 999:
			ServerSocket serverSocket = new ServerSocket(999);
			System.out.println("started listening to port 999");
			
			
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
				
				
				
			}
			
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

}
