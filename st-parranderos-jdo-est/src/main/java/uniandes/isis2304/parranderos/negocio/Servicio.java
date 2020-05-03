package uniandes.isis2304.parranderos.negocio;

public class Servicio implements VOServicio
{
	private long id;	


	private String nombre;


	public Servicio(long id, String nombre) {
		this.id = id;
		this.nombre = nombre;
	}

	public Servicio() {
		this.id = 0;
		this.nombre = "";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	
	@Override
	public String toString() {
		return "Servicio[" + "id=" + id + ", nombre=" + nombre + ", idAlojamiento=" +  + ']';
	}

}
