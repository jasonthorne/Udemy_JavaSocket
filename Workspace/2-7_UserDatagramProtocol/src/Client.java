import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client {

	public static void main(String[] args) {
		
		try {
			
			/* datagram socket for sending and receiving data.
			 * a port number DOESNT NEED declared (but one can be) 
			 * whatever the next available port is free will be assigned */
			DatagramSocket datagramSocket = new DatagramSocket();
			
			//message to send:
			String message = "Hullo, from client!";
			
			//convert message to bytes:
			byte [] byteMessage = message.getBytes();
			
			//create datagram packet holding message:
			DatagramPacket datagramPacket = new DatagramPacket(
					byteMessage, //message
					byteMessage.length, //length of bytes to read from message
					InetAddress.getLocalHost(), //IP address of server (get this from db maybe??)
					999 //socket used by server
					);
			
			//send the packet:
			System.out.println("sending datagram packet");
			datagramSocket.send(datagramPacket);
			
			/** close the socket :P */
			datagramSocket.close();
			
		} catch (SocketException | UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
