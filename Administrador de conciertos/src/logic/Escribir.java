package logic;

import java.io.IOException;
import java.net.ServerSocket;

public class Escribir implements Runnable{
	private boolean pause;
	private boolean stop;
	private Thread thread;
	private long speed;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (!stop) {
			
		

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
		speed = 0;
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

}
