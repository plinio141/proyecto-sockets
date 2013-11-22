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

public class Client implements Runnable{
	private boolean pause;
	private boolean stop;
	private Thread thread;
	private long speed;
	private Socket socket;
	private DataInputStream entrada;
	private DataOutputStream salida;
	private int opcion;
	
	public Client() {
		
		try {
			socket=new Socket("localhost", 3000);
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
		iniciarHilo();
	}

	@Override
	public void run() {
		while (!stop) {
			try {
				opcion=entrada.readInt();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			switch (opcion) {
			case 1:
				try {
					System.out.println(entrada.readUTF());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 2:
					            
				break;
			default:
				break;
			}
				
			
			try {
				Thread.sleep(speed);
			} catch (Exception e) {
				e.printStackTrace();
			}

			synchronized (this) {
				while (pause)

					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				if (stop)
					break;
			}
		}
}

	public void iniciarHilo(){
		pause = false;
		stop = false;
		thread = new Thread(this);
		thread.start();
		speed = 0;
	}
	public void start() {
		thread.start();
	}

	synchronized void stop() {
		stop = true;
		pause = false;
		notify();
	}
	synchronized void suspend() {
		pause = true;

	}

	synchronized void resume() {
		pause = false;
		notify();
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
	public static void main(String[] args) {
		Client c=new Client();
	}
}
