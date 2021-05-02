import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/** UDP is CONNECTIONLESS. 
 * you dont have to first establish the connection like you do with TCP (previous examples are all TCP)
 * You still need a socket though (and end point to send the data to. But it is not connection oriented)
 * Data is sent with a socket OBJECT called a DATAGRAM PACKET.
 * think of TCP as like using a telephone, where a connection has to be established before data can be sent.
 * Think of UDP as like sending an envelope through the mail 
 * 
 * */

public class Server {

	public static void main(String[] args) {
		
		try {
			
			//datagram socket object with port number
			DatagramSocket datagramSocket = new DatagramSocket();
			
			System.out.println("datagram is using port number: " + datagramSocket.getLocalPort());
			
			//datagram packet for receiving client datagram packet data:
			DatagramPacket datagramPacket = new DatagramPacket(new byte[1000], 1000); //buffer for holding data, number of bytes to read
			
			System.out.println("waiting for client...");
			/*set socket to wait to receive data from an external packet, 
			 * which is then inserted into the buffer of packet passed as an arg.
			 * this blocks the socket until an external packet is received*/
			datagramSocket.receive(datagramPacket);
			
			//print what was received (converted from binary to string using .getData()):
			System.out.println(new String(datagramPacket.getData()));
			
			//print IP of client that sent data:
			System.out.println("obtained from IP: " + datagramPacket.getAddress());
			//print port number of client:
			System.out.println("obtained from port: " + datagramPacket.getPort());
			
			/** +++ Here we can now send back info to client using its port number and IP. like in previous examples +++++*/
			
			/** CLOSE SOCKET :P */
			datagramSocket.close();
			
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
