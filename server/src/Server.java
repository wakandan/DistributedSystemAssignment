
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Scanner;

public class Server implements Constants {
	public static int port = 6789;
	public static boolean atMost = true;
	public int serverPort;
	DatagramSocket socket;
	public DatagramPacket request;
	HashMap<String, Client> clientList;

	public byte[] buffer;

	public Server(int port) throws SocketException {
		this.serverPort = port;
		this.socket = new DatagramSocket(serverPort);
		this.buffer = new byte[BUFFER_SIZE];
		this.request = new DatagramPacket(buffer, buffer.length);
		clientList = new HashMap<String, Client>();
	}

	public void receiveMessage() throws IOException {
		request = new DatagramPacket(buffer, buffer.length);
		socket.receive(request);

	}

	public void sendMessage(DatagramPacket reply) throws IOException {
		socket.send(reply);
	}

	public void sendMessage(String content, String address, int port)
			throws IOException {
		InetAddress clientAddress = InetAddress.getByName(address);
		DatagramPacket reply = new DatagramPacket(content.getBytes(),
				content.getBytes().length, clientAddress, port);
		socket.send(reply);
	}

	public void sendMessage(String content, InetAddress address, int port)
			throws IOException {

		DatagramPacket reply = new DatagramPacket(content.getBytes(),
				content.getBytes().length, address, port);
		socket.send(reply);
	}

	/*
	 * check whether the client is the new or old client
	 */
	public boolean checkNewClient(String address) {
		Client client = clientList.get(address);
		if (client != null)
			return false;
		else
			return true;

	}

	/*
	 * execute the command sent by the client
	 */
	public String executeCommand(Command command, String indexCommand) {
		String sendMessageString;
		String clientAddressPort = request.getAddress().toString() + ":"
				+ request.getPort();
		if (atMost) {
			if (!checkNewClient(clientAddressPort)) {
				// old client
				Client client = clientList.get(clientAddressPort);

				if (client.checkNewCommand(indexCommand)) {
					// new Command
					ReplyMessage replyMessage = command.execute();
					sendMessageString = command.replyMessage(replyMessage);
					client.addCommand(command, indexCommand);
					System.out.println("old client new Command");
				} else {
					// old command
					Command oldCommand = client.commandList.get(indexCommand);
					sendMessageString = oldCommand.replyMessage();
					System.out.println("old client old Command");
				}
			} else {
				// new Client
				Client client = new Client(request.getAddress().toString(),
						request.getPort());

				ReplyMessage replyMessage = command.execute();
				sendMessageString = command.replyMessage(replyMessage);
				client.addCommand(command, indexCommand);
				clientList.put(clientAddressPort, client);
				System.out.println("new Client");
			}
		} else {
			ReplyMessage replyMessage = command.execute();
			sendMessageString = command.replyMessage(replyMessage);

		}
		return sendMessageString;
	}

	public static void main(String args[]) throws IOException {
		int serverPort = 6789;
		Command command;
		System.out.println("Choose the algorith from server:  ");
		System.out.println("1: at least");
		System.out.println("other number: at most");
		Scanner sc = new Scanner(System.in);
		int result = sc.nextInt();
		if (result == 1) {
			System.out.println("Server runs at least algorithm");
			atMost = false;
		} else {
			atMost = true;
			System.out.println("Server runs at most algorithm");
		}

		Server server = new Server(serverPort);
		
		while (true) {
			server.receiveMessage();
			String receiveCommand = new String(server.request.getData());
			command = Command.setCommand(receiveCommand, server);
			String indexCommand = command.hashMap.get(INDEX_COMMAND);
			String sendMessageString = server.executeCommand(command,
					indexCommand);
			System.out.println("reply content " + sendMessageString);
			server.sendMessage(sendMessageString, server.request.getAddress(),
					server.request.getPort());
			System.out.println("---------------------------------");
		}
	}

}
