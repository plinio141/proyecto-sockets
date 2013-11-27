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


	public class Conexion{
		private Socket socket;
		private DataInputStream entrada;
		private DataOutputStream salida;
		
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
