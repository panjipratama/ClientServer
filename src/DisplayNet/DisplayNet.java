package DisplayNet;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

public class DisplayNet {

	public static void main(String[] args) throws Exception 
	{
		//panggil method getNetworkInterface()
		//untuk mendapatkan semua antarmuka jaringan yang digunkan dikomputer lokal
		Enumeration e = NetworkInterface.getNetworkInterfaces();
		
		//perulangan untuk mengambil tiap antarmuka jaringan
		//hasil dari method getNetworkInterface()
		while (e.hasMoreElements())
		{
			NetworkInterface netface = (NetworkInterface) e.nextElement();
			System.out.println("Net interface : "+netface.getName());
			
			//ambil semua informasi alamat ip dari tiap antarmuka jaringan
			Enumeration e2 = netface.getInetAddresses();
			
			//lakukan perulangan untuk tiap alamat ip yang didapat pada tiap antarmuka jaringannya
			while(e2.hasMoreElements())
			{
				InetAddress ip = (InetAddress) e2.nextElement();
				System.out.println("IP Adress : "+ip.toString());
			}
			
		}
	}

}
