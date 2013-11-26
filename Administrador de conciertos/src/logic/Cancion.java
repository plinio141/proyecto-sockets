package logic;

public class Cancion {
	private String nombre;
	private String ruta;
	private String genero;
	private int tiempo;
	
	public Cancion(){
		
	}
	public Cancion(String nombre,String ruta,String genero,int tiempo){
		this.nombre=nombre;
		this.genero=genero;
		this.ruta=ruta;
		this.tiempo=tiempo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getRuta() {
		return ruta;
	}
	public void setRuta(String ruta) {
		this.ruta = ruta;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public int getTiempo() {
		return tiempo;
	}
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
}
