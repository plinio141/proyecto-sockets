package logic;

import java.io.DataInputStream;
import java.io.IOException;

public class Leer implements Runnable{

	private boolean pause;
	private boolean stop;
	private Thread thread;
	private long speed;
	private DataInputStream entrada;

	private int opcion;
	
	public Leer(DataInputStream entrada) {
		this.entrada=entrada;
				
	}
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
		start();
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


}
