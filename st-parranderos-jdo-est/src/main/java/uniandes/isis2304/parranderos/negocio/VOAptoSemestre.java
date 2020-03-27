package uniandes.isis2304.parranderos.negocio;

public interface VOAptoSemestre {
	 
	public long getId();
	
	
	public String getTipoOferta();
	
	
	public Integer getHabitaciones();
	public Integer getPrecioMes();
	
	public Boolean getAmoblado();

	@Override
	
	public String toString();

	}