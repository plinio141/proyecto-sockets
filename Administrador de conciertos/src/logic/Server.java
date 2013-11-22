package logic;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server implements Runnable{
	private boolean pause;
	private boolean stop;
	private Thread thread;
	private long speed;
	private String direccion;
	private int puerto;
	private ServerSocket socketServer;
	private ArrayList<Conexion> listaConexion;
	private Socket auxSocket;

	public Server() {
		
		puerto=3000;
		listaConexion=new ArrayList<Conexion>();
		iniciarHilo();
	}
	public Server(int puerto){
		this.puerto=puerto;
		listaConexion=new ArrayList<Conexion>();
		iniciarHilo();
	}
	@Override
	public void run() {
		while (!stop) {
			System.out.println("esperando conexion");
			try {
				
				auxSocket=socketServer.accept();
				listaConexion.add(new Conexion(auxSocket));
				//enviarData();
				
				System.out.println("iniciada la conexion");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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
	public void enviarData(){
		listaConexion.get(listaConexion.size()).enviarInfoString("se conecto");
	}
	public void iniciarHilo(){
		pause = false;
		stop = false;
		thread = new Thread(this);
		speed = 0;
		iniciarServidor();
		thread.start();
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
	public void iniciarServidor() {
		if(socketServer==null){
			try {
				socketServer=new ServerSocket(puerto);
				
			} catch (IOException e) {
				System.out.println("no pudo crear server");
				e.printStackTrace();
			}
		}
	}
	public void cerrarConexion(){
		for (int i = 0; i < listaConexion.size(); i++) {
			listaConexion.get(i).cerrar();
		}
		try {
			socketServer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public ServerSocket getSocketServer() {
		return socketServer;
	}

	public void setSocketServer(ServerSocket socketServer) {
		this.socketServer = socketServer;
	}

	public ArrayList<Conexion> getListaConexion() {
		return listaConexion;
	}

	public void setListaConexion(ArrayList<Conexion> listaConexion) {
		this.listaConexion = listaConexion;
	}

	public Socket getAuxSocket() {
		return auxSocket;
	}

	public void setAuxSocket(Socket auxSocket) {
		this.auxSocket = auxSocket;
	}
	public static void main(String[] args) {
		Server server=new Server();
	}
}


