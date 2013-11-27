package gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import logic.Client;

public class VentanaConexionCliente extends JFrame{
	private Client cliente;
	private JTextField jTFIp;
	private JTextField jTFPuerto;
	private JComboBox<String> jCListaServer;
	private JButton jBConectar;
	private JTextArea area;
	private String ip;
	private int puerto;
	
	public VentanaConexionCliente(){
	
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setSize(300, 400);
		ip=JOptionPane.showInputDialog("Ingrese la ip del server");
		puerto=Integer.parseInt(JOptionPane
		.showInputDialog("Ingrese el puerto a utilizar del server "));
		
		jTFIp=new JTextField();
		jTFPuerto=new JTextField();
		jCListaServer=new JComboBox<String>();
		jBConectar=new JButton();
	}
	public static void main(String[] args) {
		VentanaConexionCliente vConexionCliente=new VentanaConexionCliente();
		vConexionCliente.setVisible(true);
	}
}
