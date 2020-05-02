package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;

import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.HotelHabitacion;

public class SQLHotelHabitacion {
	
	private final static String SQL = PersistenciaAlohandes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaAlohandes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	
	public SQLHotelHabitacion (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	
	
	public long adicionarHotelHabitacion (PersistenceManager pm, long idHotelHabitacion, String nombre, String ubicacion, String tipooferta, String tipo) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHotelHabitacion () + "(id, nombre, ubicacion, tipooferta	, tipo) values (?, ?, ?, ?, ?)");
        q.setParameters(idHotelHabitacion, nombre, ubicacion, tipooferta, tipo);
        return (long) q.executeUnique();
	}

	
	public long eliminarHotelHabitacionsPorNombre (PersistenceManager pm, String nombreHotelHabitacion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHotelHabitacion () + " WHERE nombre = ?");
        q.setParameters(nombreHotelHabitacion);
        return (long) q.executeUnique();
	}

	
	public long eliminarHotelHabitacionPorId (PersistenceManager pm, long idHotelHabitacion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHotelHabitacion () + " WHERE id = ?");
        q.setParameters(idHotelHabitacion);
        return (long) q.executeUnique();
	}

	
	public HotelHabitacion darHotelHabitacionPorId (PersistenceManager pm, long idHotelHabitacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHotelHabitacion () + " WHERE id = ?");
		q.setResultClass(HotelHabitacion.class);
		q.setParameters(idHotelHabitacion);
		return (HotelHabitacion) q.executeUnique();
	}


	public List<HotelHabitacion> darHotelHabitacionsPorNombre (PersistenceManager pm, String nombreHotelHabitacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHotelHabitacion () + " WHERE nombre = ?");
		q.setResultClass(HotelHabitacion.class);
		q.setParameters(nombreHotelHabitacion);
		return (List<HotelHabitacion>) q.executeList();
	}

	
	public List<HotelHabitacion> darHotelHabitacions (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHotelHabitacion ());
		q.setResultClass(HotelHabitacion.class);
		return (List<HotelHabitacion>) q.executeList();
	}




}
