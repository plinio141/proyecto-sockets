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
	public ArrayList<Cancion> getListaCanciones() {
		return listaCanciones;
	}
	public void setListaCanciones(ArrayList<Cancion> listaCanciones) {
		this.listaCanciones = listaCanciones;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
}
