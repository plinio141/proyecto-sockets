package gui;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JTextField;

import logic.Cancion;
import logic.Concierto;

public class VentanaConcierto extends JFrame {
	private JTextField jTNombreCancion;
	private JTextField jTDuracionCancion;
	private JTextField jTRutaCancion;
	private JButton jBRutaCancion;
	private JButton jBAddCancion;
	private Concierto concierto;
	private JList<String> jLCancion;
	private JFileChooser jFCRuta;
	private DefaultListModel<String> modelo;
	private Evento evento;
	private VentanaServer vServer;
	
	public VentanaConcierto(Concierto concierto, VentanaServer vServer) {
		modelo=new DefaultListModel<>();
		this.vServer=vServer;
		
		this.concierto=concierto;
		this.setLayout(null);
		this.setSize(300, 500);
		this.setResizable(false);
		
		evento=new Evento(this);
		
		jTNombreCancion = new JTextField();
		jTNombreCancion.setBorder(BorderFactory
				.createTitledBorder("Digite nombre de la cancion"));
		jTNombreCancion.setBounds(10, 10, 270, 40);
		
		jTDuracionCancion = new JTextField();
		jTDuracionCancion.setBorder(BorderFactory
				.createTitledBorder("Digite la duracion de la cancion"));
		jTDuracionCancion.setBounds(10, 60, 270, 40);
		
		jTRutaCancion = new JTextField();
		jTRutaCancion.setBorder(BorderFactory
				.createTitledBorder("Ruta de la cancion"));
		jTRutaCancion.setBounds(10, 110, 250, 40);
		
		jBAddCancion = new JButton("ADD CANCION");
		jBAddCancion.setBounds(10, 160, 270, 40);
		jBAddCancion.addActionListener(evento);
		jBAddCancion.setActionCommand("addcancion");
		
		jBRutaCancion = new JButton("...");
		jBRutaCancion.setBounds(270, 110, 20, 40);
		jBRutaCancion.addActionListener(evento);
		jBRutaCancion.setActionCommand("ruta");
		
		jLCancion = new JList<String>();
		jLCancion.setBorder(BorderFactory
				.createTitledBorder("Lista de canciones"));
		jLCancion.setBounds(10, 230, 270, 230);
		
		jFCRuta=new JFileChooser();
		jLCancion.setModel(modelo);
		
		add(jTNombreCancion);
		add(jBAddCancion);
		add(jBRutaCancion);
		add(jLCancion);
		add(jTDuracionCancion);
		add(jTRutaCancion);
	}
	public void buscarRuta(){
		
		int returnVal = jFCRuta.showOpenDialog(this);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
		     jTRutaCancion.setText(jFCRuta.getSelectedFile().getAbsolutePath());  
		 repaint();
		    }
	}
	public void addCancion(){
		Cancion c=new Cancion(jTNombreCancion.getText(), jTRutaCancion.getText(), concierto.getGenero(), Integer.parseInt(jTDuracionCancion.getText()));
		concierto.addCancion(c);
		modelo.addElement(c.getNombre());
		repaint();
	}
}
