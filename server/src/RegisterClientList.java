import java.net.DatagramPacket;
import java.util.ArrayList;

public class RegisterClientList {
	ArrayList<RegisterClient> clientList;

	public RegisterClientList() {
		clientList = new ArrayList<RegisterClient>();
	}

	public void addRegisterClient(RegisterClient registerClient) {
		clientList.add(registerClient);
	}

	public void addRegisterClient(String ip, int port, int interval) {
		RegisterClient client = new RegisterClient(ip, port, interval);
		clientList.add(client);
	}

	public void addRegisterClient(DatagramPacket packet, int interval) {
		RegisterClient client = new RegisterClient(packet.getAddress()
				.toString().substring(1), packet.getPort(), interval);
		clientList.add(client);
	}

	/*
	 * remove the client that the monitor time is over
	 */
	public boolean refresh() {
		Long currentTime = System.currentTimeMillis() / 1000;
		int i = 0;
		RegisterClient client;
		while (i < clientList.size()) {
			client = clientList.get(i);
			if (client.isAlive(currentTime))
				i++;
			else {
				System.out.println("client timeout " + client.ip);
				clientList.remove(i);
			}
		}
		if (clientList.size() > 0)
			return true;
		else
			return false;
	}

	public int size() {
		// TODO Auto-generated method stub
		return clientList.size();
	}

	public RegisterClient get(int index) {
		return clientList.get(index);
	}
}
