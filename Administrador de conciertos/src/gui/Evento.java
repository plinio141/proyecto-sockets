package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Evento implements ActionListener{
	private VentanaAdministrador vAdministrador;
	private VentanaServer vServer;
	private VentanaConcierto vConcierto;
	public Evento(VentanaAdministrador ventana){
		this.vAdministrador=ventana;
	}
	public Evento(VentanaServer server) {
		this.vServer=server;
	}
	public Evento(VentanaConcierto vConcierto){
		this.vConcierto=vConcierto;
	}
	@Override
	public void actionPerformed(ActionEvent event) {
		// TODO Auto-generated method stub
		if(event.getActionCommand()=="agregar server"){
			vAdministrador.addServer(vAdministrador.crearServer());
		}
		if(event.getActionCommand()=="agregar concierto"){
			vServer.crearConcierto();
		}
		if(event.getActionCommand()=="ruta"){
			vConcierto.buscarRuta();
		}
		if(event.getActionCommand()=="addcancion"){
			vConcierto.addCancion();
		}
	}

}
