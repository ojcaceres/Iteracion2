package uniandes.isis2304.parranderos.negocio;


public class ViviendaUniversitaria  implements VOViviendaUniversitaria 
{
	/* **********************
	 * 			Atributos
	 ***********************/

	private long id;


	private Integer telefono;


	private Boolean amoblado;

	private String tipoOferta;
	private Integer precioNoche;
	/* **********************
	 * 			Métodos
	 ***********************/

	public ViviendaUniversitaria() 
	{
		this.id = 0;

		this.amoblado = false;
		this.telefono = 0;
		this.tipoOferta = "";
		this.setPrecioNoche(0);
	}

	/**
	 * Constructor con valores
	 */
	public ViviendaUniversitaria(long id, Integer telefono, Boolean amoblado,  String tipoOferta) 
	{
		this.id = id;

		this.telefono = telefono;
		this.amoblado = amoblado;
		this.tipoOferta = tipoOferta;
	}

	public String getTipoOferta() 
	{
		return tipoOferta;
	}


	public void setTipoOferta(String tipoOferta) 
	{
		this.tipoOferta = tipoOferta;
	}
	public Integer getTelefono() 
	{
		return telefono;
	}

	public void setTelefono(Integer telefono) 
	{
		this.telefono = telefono;
	}

	public long getId() 
	{
		return id;
	}

	public void setId(long id) 
	{
		this.id = id;
	}

	public Boolean getAmoblado() 
	{
		return amoblado;
	}


	public void setamoblado(Boolean amoblado) 
	{
		this.amoblado = amoblado;
	}


	@Override
	public String toString() 
	{
		return "Visitan [id=" + id + ", amoblado=" + amoblado + ", tipoOferta=" + tipoOferta + ", telefono="
				+ telefono + "]";
	}

	public Integer getPrecioNoche() {
		return precioNoche;
	}

	public void setPrecioNoche(Integer i) {
		this.precioNoche = i;
	}

}