package uniandes.isis2304.parranderos.negocio;

import java.util.List;

public interface VOProveedor 
{
public long getId();
	
	/**
	 * @return el nombre del bar
	 */
	public String getTipo();
	
	public String toString();
	
	public List<Object[]> getAlojamientos();
}
