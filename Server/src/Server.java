import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Server {
  
	private static int port = 9912;
	
	public static void main(String[] args) {

		try {
			
			BufferedReader clientInput;
			
			ServerSocket serverSocket = new ServerSocket(port);
			
			hostIP();
			
			System.out.println("[Server]: Cekam na konekciju...");
			Socket socketZaKomunikaciju = serverSocket.accept();
			System.out.println("[Server]: Doslo je do konekcije!");
			
			while(true) {
				
				clientInput = new BufferedReader(new InputStreamReader(socketZaKomunikaciju.getInputStream()));
				
				String input = clientInput.readLine();
				
				try {
					
					Robot keyInputGenerator = new Robot();
					
					if(!input.equals(null)) {
							
						switch (input) {
						case "1":
							System.out.println("[Server]: Presli smo na sledeci slajd");
							keyInputGenerator.keyPress(KeyEvent.VK_RIGHT);
							keyInputGenerator.keyRelease(KeyEvent.VK_RIGHT);
							break;
						case "-1":
							System.out.println("[Server]: Presli smo na prethodni slajd");
							keyInputGenerator.keyPress(KeyEvent.VK_LEFT);
							keyInputGenerator.keyRelease(KeyEvent.VK_LEFT);
							break;
						case "0":
							break;
						default:
							break;
						}
						
					}
					
				}catch (Exception e) {
						
						System.out.println("[Server]: Korisnik se diskonektovao!");
						break;
						
					}
				
			}
			
		} catch (IOException e) {
			
			System.err.println("[Server]: Greska pri pokretanju servera!");
			e.printStackTrace();
			
		}

	}
	
	static public void hostIP() {
		
		InetAddress inetIP;
		
		try {
			
			inetIP = InetAddress.getLocalHost();
			
			System.out.println("[Server]: Vasa IP adresa je: " + inetIP.getHostAddress());
			
		} catch (UnknownHostException e) {
			
			System.out.println("[Server]: Greska pri pronalazenju adrese host-a");
			e.printStackTrace();
			
		}
		
	}

}
