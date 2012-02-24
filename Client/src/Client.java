import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;


public class Client {
	public static void main(String args[]){
		DatagramSocket aSocket =null;
		try{
			aSocket = new DatagramSocket();
			String text ="test";
			byte[] m = text.getBytes();
			InetAddress aHost = Inet4Address.getByName("192.168.1.14");
			int serverPort = 6789;
			DatagramPacket request = new DatagramPacket(m,m.length, aHost, serverPort);
			aSocket.send(request);
			byte [] buffer =new byte[1000];
			DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
			aSocket.receive(reply);
			System.out.println("rely : "+new String(reply.getData()));
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		finally{ 
			if(aSocket!=null)aSocket.close();
		}
	}
}
