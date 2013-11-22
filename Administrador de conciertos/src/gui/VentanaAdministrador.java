package gui;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;

import logic.Server;
/**
 * 
 * @author pramirez
 * 
 */
public class VentanaAdministrador extends JFrame{
	private ArrayList<VentanaServer> arrayServers;
	private JTextField jTPuerto;
	private JList<String> listaServer;
	private JButton jBAgregar;
	private Evento evento;
	/**
	 * constructor de inicializacion de algunas variables y agregacion de 
	 * componentes graficos 
	 */
	public VentanaAdministrador(){
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(300, 400);
		evento=new Evento(this);
		jTPuerto=new JTextField();
		jTPuerto.setBorder(BorderFactory
				.createTitledBorder("Digite el puerto a utilizar"));
		jTPuerto.setBounds(20, 10, 250, 50);
		listaServer=new JList<String>();
		listaServer.setBorder(BorderFactory
				.createTitledBorder("servers activos"));
		listaServer.setBounds(20, 140, 250, 200);
		jBAgregar=new JButton("iniciar nuevo Server");
		jBAgregar.setBounds(40, 80, 200, 50);
		jBAgregar.addActionListener(evento);
		jBAgregar.setActionCommand("agregar server");
		add(jBAgregar);
		add(jTPuerto);
		add(listaServer);
	}
	
	/**
	 * agrega servers creados a lista
	 * @param server es nuevo server que se va añadir
	 */
	public void addServer(VentanaServer server){
		if(arrayServers==null){
			arrayServers=new ArrayList<VentanaServer>();
			arrayServers.add(server);
		}
		else{
			arrayServers.add(server);
		}
	}
	/**
	 * se crea un nuevo server
	 * @return se retorna el server creado
	 */
	public VentanaServer crearServer(){
		VentanaServer server=new VentanaServer(Integer.valueOf(jTPuerto.getText()));
		server.setVisible(true);
		return server;
	}
	public static void main(String[] args) {
		VentanaAdministrador ventana=new VentanaAdministrador();
		ventana.setVisible(true);
	}
}
