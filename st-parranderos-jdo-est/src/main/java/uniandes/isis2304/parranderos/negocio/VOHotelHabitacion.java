package uniandes.isis2304.parranderos.negocio;

public interface VOHotelHabitacion {
	/* **********************
	 * 			Métodos 
	 ***********************/
     /**
	 * @return El id del HotelHabitacion
	 */
	public long getId();
	
	/**
	 * @return el nombre del HotelHabitacion
	 */
	public String getTipoOferta();
	
	/**
	 * @return El presupuesto del HotelHabitacion
	 */
	public Integer getPrecioNoche();
	
	/**
	 * @return la cantSedes del HotelHabitacion
	 */
	public Integer getCapacidad();

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos del HotelHabitacion
	 */
	public String toString();

}