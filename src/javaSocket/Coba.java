package javaSocket;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Coba {

	public static void main(String[] args) 
	{
		try{
			InetAddress ip = InetAddress.getByName("localhost");
			System.out.println(ip.getHostAddress());
			System.out.println(ip.getHostName());
		}catch(UnknownHostException e)
		{
			System.out.println(e);
		}
	}

}
