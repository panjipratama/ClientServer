package ClientServerdgnSocket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class InfoServer {
	
	private final int INFO_PORT=50000;
	private String datafromClient;
	

	/** InfoServer Constructor **/
	public InfoServer()
	{
		BufferedReader inFromClient;
		DataOutputStream outToClient;
		Socket serverSocket;
		
		try
		{
			//bind port 50000 ke alamat lokal
			ServerSocket infoServer = new ServerSocket(INFO_PORT);
			
			System.out.println("Server Telah Siap ... ");
			
			/**
			 * lakukan perulangan tanpa henti ,
			 * sampai clien memberi perintah QUIT
			 **/
			while (true)
			{
				/**
				 * masuk ke mode listening,
				 * server siap menerima permintaan dari client
				 **/
				serverSocket = infoServer.accept();
				System.out.println("Ada Client yang Terkoneksi !");
				
				/**
				 * buat input stream dari socket
				 * dan juga sekaligus konversi dari 
				 * byte stream ke character stream
				 * (InputStremReader)
				 * BufferedReader akan memudahkan dalam pengolahan data karakter
				 **/
				inFromClient = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
				
				/**buat output stream kesocket**/
				outToClient = new DataOutputStream(serverSocket.getOutputStream());
				
				/**tulis selamat datang untuk client**/
				outToClient.writeBytes("InfoServer versi 0.1\n hanya untuk testing ... \n"+
										"silahkan berikan perintah TIME | NET | QUIT \n");
				
				/**lakukan perulangan sampai client
				 * mengirimkan perintah QUIT
				 **/
				
				boolean isQUIT = false;
				while (!isQUIT)
				{
					//baca data dari client
					datafromClient = inFromClient.readLine();
					
					if (datafromClient.startsWith("TIME"))
					{
						outToClient.writeBytes(new Date().toString()+"\n");
					}
					else if (datafromClient.startsWith("NET"))
					{
						outToClient.writeBytes(InetAddress.getByName("localhost").toString()+"\n");
					}
					else if (datafromClient.startsWith("QUIT"))
					{
						isQUIT = true;
						outToClient.writeBytes("BYE !!");
					}
				}
				
				outToClient.close();
				inFromClient.close();
				serverSocket.close();
				System.out.println("koneksi client tertutup ... ");
			}
		}catch (IOException ioe)
		{
			System.out.println("error : "+ ioe);
		}
		catch (Exception e)
		{
			System.out.println("error : "+ e);
		}
	}
	
	//program utama
	public static void main(String[] args) {
		new InfoServer();

	}

}
