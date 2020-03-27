package uniandes.isis2304.parranderos.negocio;

public class Servicio implements VOServicio
{
	 private long id;	
		
		/**
		 * El nombre del bebedor
		 */
		private String nombre;
		 private long idAlojamiento;


	    public Servicio(long id, String nombre, long idAlojamiento) {
	        this.id = id;
	        this.nombre = nombre;
	        this.idAlojamiento = idAlojamiento;
	    }
	    
	    public Servicio() {
	        this.id = 0;
	        this.nombre = "";
	        this.idAlojamiento = 0;
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

	    public long getIdAlojamiento() {
	        return idAlojamiento;
	    }

	    public void setIdAlojamiento(long idAlojamiento) {
	        this.idAlojamiento = idAlojamiento;
	    }
	    
	    @Override
	    public String toString() {
	        return "Servicio[" + "id=" + id + ", nombre=" + nombre + ", idAlojamiento=" + idAlojamiento + ']';
	    }
	      
}
