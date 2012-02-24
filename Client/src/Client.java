import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Client implements Constants {
	public String			serverIp;
	public int				serverPort;
	public byte[]			buffer;
	public DatagramSocket	socket;
	public InetAddress		server;

	public Client(String serverIp, int serverPort) throws SocketException, UnknownHostException {
		this.serverIp = serverIp;
		this.serverPort = serverPort;
		this.buffer = new byte[BUFFER_SIZE];
		this.socket = new DatagramSocket();
		this.server = InetAddress.getByName(this.serverIp);
	}

	public void send(byte[] bytes, int length) throws IOException {
		DatagramPacket request = new DatagramPacket(bytes, length, this.server, this.serverPort);
		this.socket.send(request);
	}

	public void send(String str) throws IOException {
		send(str.getBytes(), str.length());
	}

	public void recv(byte[] bytes, int maxLen) throws IOException {
		DatagramPacket request = new DatagramPacket(bytes, maxLen);
		this.socket.receive(request);
	}

	public static void main(String args[]) throws IOException {
		String serverIp = "127.0.0.1";
		int serverPort = 6789;
		Client client = new Client(serverIp, serverPort);
		Command cmdReadFile = new CommandReadFile("text", 2, 10);
		client.send(cmdReadFile);
		byte[] reply = new byte[1000];
		client.recv(reply, reply.length);
		System.out.println(new String(reply));
	}

	/**
	 * @param cmdReadFile
	 * @throws IOException
	 */
	private void send(Command cmdReadFile) throws IOException {
		send(cmdReadFile + "");
	}
}
