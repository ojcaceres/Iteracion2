/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universnitad	de	los	Andes	(Bogotá	- Colombia)
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
public class Empresa implements VOEmpresa
{
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El nitentificador ÚNICO de los bares
	 */
	private long nit;
	
	/**
	 * El nombre del bar
	 */
	private String nombre;

	/**
	 * La email donde se encuentra el bar
	 */
	private String email;
	
	/**
	 * El registrado del bar (ALTO, MEDIO, BAJO)
	 */
	private Boolean registrado;
	
	/**
	 * El número de sedes del bar en la email
	 */
	private int cantSedes;

	/* ****************************************************************
	 * 			Métodos 
	 *****************************************************************/
    /**
     * Constructor por defecto
     */
	public Empresa() 
    {
    	this.nit = 0;
		this.nombre = "";
		this.email = "";
		this.registrado = false;
		this.cantSedes = 0;
	}

	/**
	 * Constructor con valores
	 * @param nit - El nit del bart
	 * @param nombre - El nombre del bar
	 * @param email - La email del bar
	 * @param registrado - El registrado del bar (ALTO, MEDIO, BAJO)
	 * @param cantSedes - Las sedes del bar (Mayor que 0)
	 */
    public Empresa(long nit, String nombre, String email, Boolean registrado, int cantSedes) 
    {
    	this.nit = nit;
		this.nombre = nombre;
		this.email = email;
		this.registrado = registrado;
		this.cantSedes = cantSedes;
	}

    /**
	 * @return El nit del bar
	 */
	public long getNit() 
	{
		return nit;
	}
	
	/**
	 * @param nit - El nuevo nit del bar
	 */
	public void setNit(long nit) 
	{
		this.nit = nit;
	}
	
	/**
	 * @return el nombre del bar
	 */
	public String getNombre() 
	{
		return nombre;
	}
	
	/**
	 * @param nombre El nuevo nombre del bar
	 */
	public void setNombre(String nombre) 
	{
		this.nombre = nombre;
	}
	
	/**
	 * @return la email del bar
	 */
	public String getEmail() 
	{
		return email;
	}
	
	/**
	 * @param email - La nueva email del bar
	 */
	public void setEmail(String email) 
	{
		this.email = email;
	}
	
	/**
	 * @return El registrado del bar
	 */
	public Boolean getRegistrado() 
	{
		return registrado;
	}
	
	/**
	 * @param registrado - El nuevo registrado del bar (ALTO, MEDIO, BAJOO)
	 */
	public void setRegistrado(Boolean registrado) 
	{
		this.registrado = registrado;
	}
	
	/**
	 * @return la cantSedes del bar
	 */
	public int getCantSedes() 
	{
		return cantSedes;
	}
	
	/**
	 * @param cantSedes - la nueva cantnitad de sedes del bar
	 */
	public void setCantSedes(int cantSedes) 
	{
		this.cantSedes = cantSedes;
	}
	

	/**
	 * @return Una cadena de caracteres con todos los atributos del bar
	 */
	public String toString() 
	{
		return "Bar [nit=" + nit + ", nombre=" + nombre + ", email=" + email + ", registrado=" + registrado
				+ ", cantSedes=" + cantSedes + "]";
	}

	

}
