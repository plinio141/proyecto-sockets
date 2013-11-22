package logic;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.plaf.SliderUI;


	public class Conexion implements Runnable {
		private boolean pause;
		private boolean stop;
		private Thread thread;
		private long speed;
		private String direccion;
		private int puerto;
		private Socket socket;
		private DataInputStream entrada;
		private DataOutputStream salida;
		private int opcion;
		public Conexion(Socket socketEntrada) {
			
			socket = socketEntrada;
			try {
				entrada = new DataInputStream(socket.getInputStream());

			} catch (IOException e) {
				System.out.println("no creado canal de entrada");
				e.printStackTrace();
			}
			try {
				salida = new DataOutputStream(socket.getOutputStream());
			} catch (IOException e) {
				System.out.println("no creado canal de salida");
				e.printStackTrace();
			}
			
			iniciarHilo();
		}

		@Override
		public void run() {
			while (!stop) {
				Scanner scanner=new Scanner(System.in);
				String envio=scanner.nextLine();
				 enviarInfoString(envio);
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
		
		public void enviarInfoString(String info){
			try {
				salida.writeInt(1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				salida.writeUTF(info);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void iniciarHilo(){
			
			pause = false;
			stop = false;
			thread = new Thread(this);
			thread.start();
			speed = 4000;
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

		public DataInputStream getEntrada() {
			return entrada;
		}

		public void setEntrada(DataInputStream entrada) {
			this.entrada = entrada;
		}

		public DataOutputStream getSalida() {
			return salida;
		}

		public void setSalida(DataOutputStream salida) {
			this.salida = salida;
		}
		
	}
