package logic;

import java.util.ArrayList;

public class Concierto {
	private ArrayList<Cancion> listaCanciones;
	private String nombre;
	private String genero;
	public Concierto(String nombre,String genero){
		this.nombre=nombre;
		this.genero=genero;
	}
	public void addCancion(Cancion cancion){
		if(listaCanciones==null){
			listaCanciones=new ArrayList<Cancion>();
			listaCanciones.add(cancion);
		}
		else{
			listaCanciones.add(cancion);
		}
	}
	public Cancion crearCancion(){
		Cancion cancion=new Cancion();
		return cancion;
	}
}
