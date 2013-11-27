package logic;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Escribir implements Runnable{
	private boolean pause;
	private boolean stop;
	private Thread thread;
	private long speed;
	private String direccion;
	private int puerto;
	private Socket socket;
	private DataOutputStream salida;
	private int opcion;
	private String mensaje;
	private String ruta;
	
	public Escribir(DataOutputStream salida) {
		this.salida = salida;
		ruta="codigo.txt";
	}

	public void run() {
		while (!stop) {
			File file = new File( ruta );
			BufferedReader entrada;
			FileReader fileReader;
			try {
			fileReader=new FileReader(file);
			entrada = new BufferedReader(fileReader);
			String linea;
			while(entrada.ready()){
			linea = entrada.readLine();
			try {
				salida.writeInt(1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				salida.writeUTF(linea);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			}catch (IOException e) {
			e.printStackTrace();
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

	public void leerArchivo(String ruta) {
		File file = new File( ruta );
		BufferedReader entrada;
		FileReader fileReader;
		try {
		fileReader=new FileReader(file);
		entrada = new BufferedReader(fileReader);
		String linea;
		while(entrada.ready()){
		linea = entrada.readLine();
		
		}
		}catch (IOException e) {
		e.printStackTrace();
		}
	}

	public void iniciarHilo() {

		pause = false;
		stop = false;
		thread = new Thread(this);
		speed = 1000;
		start();
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

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public boolean isStop() {
		return stop;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public Thread getThread() {
		return thread;
	}

	public void setThread(Thread thread) {
		this.thread = thread;
	}

	public long getSpeed() {
		return speed;
	}

	public void setSpeed(long speed) {
		this.speed = speed;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getPuerto() {
		return puerto;
	}

	public void setPuerto(int puerto) {
		this.puerto = puerto;
	}

	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}
}
