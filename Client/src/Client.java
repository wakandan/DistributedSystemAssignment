import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Client implements Constants {
	public static int		indexCommand;
	public String			serverIp;
	public int				serverPort;
	public byte[]			buffer;
	public DatagramSocket	socket;
	public InetAddress		server;
	public Command			command;
	public Thread			registerThread;

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

	public void recv() throws IOException {
		DatagramPacket request = new DatagramPacket(this.command.buffer, this.command.buffer.length);
		this.socket.receive(request);
	}

	public static void main(String args[]) throws IOException {
		String serverIp = "127.0.0.1";
		// String serverIp = "192.168.1.14";
		int serverPort = 6789;
		indexCommand = 0;
		Client client = new Client(serverIp, serverPort);
		client.displayMenu();
	}

	/**
	 * `
	 * 
	 * @param cmdReadFile
	 * @throws IOException
	 */
	private void send(Command command) throws IOException {
		send(command + "");
	}

	public int displayCommands() {
		System.out.println("**********");
		System.out.println("1. Read from a file");
		System.out.println("2. Write to a file");
		System.out.println("3. Register file to server");
		System.out.println("4. List file in a directory");
		System.out.println("5. Delete file in server");
		System.out.println("6. Exit");
		System.out.println("**********");
		System.out.print("Your command?  ");
		Scanner sc = new Scanner(System.in);
		int result = sc.nextInt();
		return result;
	}

	public void displayMenu() throws IOException {
		int choiceCode = displayCommands();
		boolean choiceValid = true;
		while (choiceCode != OPT_EXIT) {
			switch (choiceCode) {
			case OPT_READFILE:
				command = new CommandReadFile();
				break;
			case OPT_WRITEFILE:
				command = new CommandWriteFile();
				break;
			case OPT_REGISTER:
				command = new CommandRegister();
				break;
			case OPT_DIRECTORY:
				command = new CommandListDir();
				break;
			case OPT_DELETE:
				command = new CommandDelete();
				break;
			default:
				System.out.println("Invalid command...");
				choiceValid = false;
			}

			if (choiceValid) {
				command.requestData();
				indexCommand++;
				this.send(command);
				this.recv();
				command.processReply();

				/* In case of registering for monitoring a new file... */
				if (choiceCode == OPT_REGISTER) {
					socket.setSoTimeout(((CommandRegister) command).interval * 100);
					Timer timer = new Timer();
					timer.schedule(new TimerTask(){
						public void run() {
							((CommandRegister) command).timeElapsed = true;
						}
					}, ((CommandRegister) command).interval * 1000);

					while (!((CommandRegister) command).timeElapsed)
						try {
							/* set time out so that it won't wait forever */
							recv();
							command.processReply();
						} catch (SocketTimeoutException e) {} catch (IOException e) {
							e.printStackTrace();
						}
					/* reset this value for waiting infinitely */
					socket.setSoTimeout(0);
					System.out.println("[info] Register period elapsed. Monitor removed");
				}
			}

			choiceCode = displayCommands();
		}
	}
}
