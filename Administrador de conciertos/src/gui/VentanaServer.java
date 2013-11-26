package gui;

import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;

import logic.Concierto;
import logic.Server;

public class VentanaServer extends JFrame{
	private int puerto;
	private Server server;
	private JTextField jTNombreConcierto;
	private JTextField jtGeneroConcierto;
	private JButton jBNuevoConcierto;
	private ArrayList<Concierto> listaConciertos;
	private JList<String> jListaConciertos;
	private Evento evento;
	private VentanaConcierto vConcierto;
	private JButton jBSalir;
	private DefaultListModel<String> modelo;
	
	
	public VentanaServer(int puerto) {
		modelo=new DefaultListModel<>();
		
		setResizable(false);
		setDefaultCloseOperation(0);
		setLayout(null);
		setSize(300, 400);
		evento=new Evento(this);
		this.puerto=puerto;
		jTNombreConcierto=new JTextField();
		jTNombreConcierto.setBorder(BorderFactory
				.createTitledBorder("Digite nombre del concierto"));
		jTNombreConcierto.setBounds(20, 10, 250, 50);
		jtGeneroConcierto=new JTextField();
		jtGeneroConcierto.setBorder(BorderFactory
				.createTitledBorder("Digite el genero del concierto"));
		jtGeneroConcierto.setBounds(20, 70, 250, 50);
		jBNuevoConcierto=new JButton("nuevo Concierto");
		jBNuevoConcierto.setBounds(20, 130, 250, 50);
		jBNuevoConcierto.addActionListener(evento);
		jBNuevoConcierto.setActionCommand("agregar concierto");
		jListaConciertos=new JList<String>();
		jListaConciertos.setBounds(20, 190, 250, 150);
		jListaConciertos.setBorder(BorderFactory
				.createTitledBorder("lista de conciertos"));
		jListaConciertos.setModel(modelo);
		
		jBSalir=new JButton("cerrar server");
		jBSalir.setBounds(20, 350, 250, 40);

		add(jBNuevoConcierto);
		add(jListaConciertos);
		add(jTNombreConcierto);
		add(jtGeneroConcierto);
		add(jBSalir);
	}
	public void addConcierto(Concierto concierto){
		if(listaConciertos==null){
			listaConciertos=new ArrayList<Concierto>();
			listaConciertos.add(concierto);
			server=new Server(puerto);
		}
		else{
			listaConciertos.add(concierto);
		}
	}
	public Concierto crearConcierto(){
		Concierto concierto=new Concierto(jTNombreConcierto.getText(),jtGeneroConcierto.getText());
		vConcierto=new VentanaConcierto(concierto);
		vConcierto.setVisible(true);
		return concierto;
	}
	/**
	 * agregar visualmente el concierto
	 **/
	public void addVConcierto(Concierto concierto){
		listaConciertos.add(concierto);
		modelo.addElement(concierto.getNombre());
	}
}
