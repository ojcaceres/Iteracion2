/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.negocio;

/**
 * Clase para modelar el concepto BAR del negocio de los Parranderos
 *
 * @author Germán Bravo
 */
public class Alojamiento implements VOAlojamiento
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El identificador ÚNICO de los bares
	 */
	private long id;
	
	/**
	 * El nombre del bar
	 */
	private String ubicacion;

	/**
	 * La ciudad donde se encuentra el bar
	 */
	private String nombre;

	private String tipoOferta;
	
	/**
	 * El presupuesto del bar (ALTO, MEDIO, BAJO)
	 */
	private String tipo;
	

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Alojamiento() 
    {
    	this.id = 0;
		this.nombre = "";
		this.tipo = "";
		this.ubicacion = "";

	}

	
    public Alojamiento(long id, String nombre, String tipo, String ubicacion) 
    {
    	this.id = id;
		this.nombre = nombre;
		this.tipo = tipo;
		this.ubicacion = ubicacion;
	}

    
	public long getId() 
	{
		return id;
	}
	
	
	public void setId(long id) 
	{
		this.id = id;
	}
	
	
	public String getNombre() 
	{
		return nombre;
	}
	

	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	
	public String getTipo() 
	{
		return tipo;
	}
	
	
	public void setTipoOferta(String tipoOferta) 
	{
		this.tipoOferta = tipoOferta;
	}
	
	public String getTipoOferta() 
	{
		return tipoOferta;
	}
	
	
	public void setTipo(String tipo) 
	{
		this.tipo = tipo;
	}

	public String getUbicacion() 
	{
		return ubicacion;
	}
	
	
	public void setUbicacion(String Ubicacion) 
	{
		this.ubicacion= ubicacion;
	}
	
	
	
	@Override

	public String toString() 
	{
		return "Alojamiento [id=" + id + ", nombre=" + nombre + ", tipoOferta=" + tipoOferta + ", tipo=" + tipo + ", ubicacion=" + ubicacion
				+ "]";
	}
	

}
