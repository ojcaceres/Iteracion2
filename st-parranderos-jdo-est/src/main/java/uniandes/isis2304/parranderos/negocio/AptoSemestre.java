

package uniandes.isis2304.parranderos.negocio;


public class AptoSemestre  implements VOAptoSemestre
{
	/* **********************
	 * 			Atributos
	 ***********************/

	private long id;


	private Integer habitaciones;


	private Boolean amoblado;

	private String tipoOferta;
	private Integer precioMes;
	/* **********************
	 * 			Métodos
	 ***********************/

	public AptoSemestre() 
	{
		this.id = 0;
		this.amoblado = false;
		this.habitaciones = 0;
		this.tipoOferta = "";
		this.setPrecioMes(0);
	}

	/**
	 * Constructor con valores
	 * @param precioMes 
	 */
	public AptoSemestre(long id, Integer habitaciones, Boolean amoblado,  String tipoOferta, Integer precioMes) 
	{
		this.id = id;
		this.precioMes=precioMes;
		this.habitaciones = habitaciones;
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
	public Integer getHabitaciones() 
	{
		return habitaciones;
	}

	public void setHabitaciones(Integer habitaciones) 
	{
		this.habitaciones = habitaciones;
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
		return "Visitan [id=" + id + ", amoblado=" + amoblado + ", tipoOferta=" + tipoOferta + ", habitaciones="
				+ habitaciones + "]";
	}

	public Integer getPrecioMes() {
		return precioMes;
	}

	public void setPrecioMes(Integer i) {
		this.precioMes = i;
	}

}