package uniandes.isis2304.parranderos.negocio;


public class HotelHabitacion implements VOHotelHabitacion
{
	/* **********************
	 * 			Atributos
	 ***********************/
	/**
	 * El identificador ÚNICO de los HotelHabitacions
	 */
	private long id;

	/**
	 * El nombre del HotelHabitacion
	 */
	private String tipoOferta;

	/**
	 * La ciudad donde se encuentra el HotelHabitacion
	 */
	private Integer precioNoche;



	/**
	 * El número de sedes del HotelHabitacion en la ciudad
	 */
	private int capacidad;

	/* **********************
	 * 			Métodos 
	 ***********************/
	/**
	 * Constructor por defecto
	 */
	public HotelHabitacion() 
	{
		this.id = 0;
		this.setPrecioNoche(0);
		this.settipoOferta("");
		this.setCapacidad(0);
	}

	/**
	 * Constructor con valores
	 */
	public HotelHabitacion(long id, Integer precioNoche, String tipoOferta,  Integer capacidad) 
	{
		this.id = id;
		this.precioNoche = precioNoche;
		this.tipoOferta = tipoOferta;

		this.setCapacidad(capacidad);
	}

	/**
	 * @return El id del HotelHabitacion
	 */
	public long getId() 
	{
		return id;
	}

	/**
	 * @param id - El nuevo id del HotelHabitacion
	 */
	public void setId(long id) 
	{
		this.id = id;
	}



	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del HotelHabitacion
	 */
	public String toString() 
	{
		return "HotelHabitacion [id=" + id + ", tipoOferta=" + tipoOferta + ", precioNoche=" + precioNoche +  ", capacidad=" + capacidad + "]";
	}



	public Integer getPrecioNoche() {
		return precioNoche;
	}

	public void setPrecioNoche(Integer precioNoche) {
		this.precioNoche = precioNoche;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public String getTipoOferta() {
		return tipoOferta;
	}

	public void settipoOferta(String tipoOferta) {
		this.tipoOferta = tipoOferta;
	}

	


}