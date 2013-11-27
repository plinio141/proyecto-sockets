package logic;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;

public class Client {
	
	private Socket socket;
	private DataInputStream entrada;
	private DataOutputStream salida;
	
	
	public Client(String ip,int puerto) {
		
		try {
			socket=new Socket(ip, puerto);
		} catch (UnknownHostException e) {
			System.out.println("no existe host");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("puerto no  sirve");
			e.printStackTrace();
		}
		try {
			entrada=new DataInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			salida=new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	public void cerrar(){
		try {
			entrada.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			salida.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
