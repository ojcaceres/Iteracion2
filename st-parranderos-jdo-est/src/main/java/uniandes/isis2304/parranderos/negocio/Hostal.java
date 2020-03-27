package uniandes.isis2304.parranderos.negocio;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Hostal implements VOHostal
{
	
private long id;	
	
	/**
	 * El nombre del bebedor
	 */
	private String nombre;
	
	/**
	 * La ciudad del bebedor
	 */
	private String horario;
	
	/**
	 * El presupuesto del bebedor (ALTO, MEDIO, BAJO)
	 */
	private String email;
	
	private long telefono;
	
	private String ubicacion;
	
	private long idProveedor;
	
	private List<Object []> habs;
	
	public Hostal() 
	{
		this.id= 0;
		this.nombre = "";
		this.horario = "";
		this.email = "";
		this.telefono = 0;
		this.ubicacion= "";
		this.idProveedor = 0;


		habs = new LinkedList<Object []> ();

	}
	
    public Hostal(long id, String nombre, String horario, String email, long telefono, String ubicacion, long idProveedor, List<Object[]> habs) {
        this.id = id;
        this.nombre = nombre;
        this.horario = horario;
        this.email = email;
        this.telefono = telefono;
        this.ubicacion = ubicacion;
        this.idProveedor = idProveedor;
        this.habs = habs;
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

	    public String getHorario() {
	        return horario;
	    }

	    public void setHorario(String horario) {
	        this.horario = horario;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public long getTelefono() {
	        return telefono;
	    }

	    public void setTelefono(long telefono) {
	        this.telefono = telefono;
	    }

	    public String getUbicacion() {
	        return ubicacion;
	    }

	    public void setUbicacion(String ubicacion) {
	        this.ubicacion = ubicacion;
	    }

	    public long getIdProveedor() {
	        return idProveedor;
	    }

	    public void setIdProveedor(long idProveedor) {
	        this.idProveedor = idProveedor;
	    }

	    public List<Object []> getHabs() {
	        return habs;
	    }

	    public void setHabs (List<Object []> habs) {
	        this.habs = habs;
	    }
	  
	    @Override
	    public String toString() {
	        return "Hostal[" + "id=" + id + ", nombre=" + nombre + ", horario=" + horario + ", email=" + email + ", telefono=" + telefono + ", ubicacion=" + ubicacion + ", idProveedor=" + idProveedor + ", habs=" + habs + ']';
	    }
	

}
