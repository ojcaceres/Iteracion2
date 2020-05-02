package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;

import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.HostalHabitacion;

public class SQLHostalHabitacion {
	
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

	
	public SQLHostalHabitacion (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	
	
	public long adicionarHostalHabitacion (PersistenceManager pm, long idHostalHabitacion, String nombre, String ubicacion, String tipooferta, String tipo) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHostalHabitacion () + "(id, nombre, ubicacion, tipooferta	, tipo) values (?, ?, ?, ?, ?)");
        q.setParameters(idHostalHabitacion, nombre, ubicacion, tipooferta, tipo);
        return (long) q.executeUnique();
	}

	
	public long eliminarHostalHabitacionsPorNombre (PersistenceManager pm, String nombreHostalHabitacion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHostalHabitacion () + " WHERE nombre = ?");
        q.setParameters(nombreHostalHabitacion);
        return (long) q.executeUnique();
	}

	
	public long eliminarHostalHabitacionPorId (PersistenceManager pm, long idHostalHabitacion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHostalHabitacion () + " WHERE id = ?");
        q.setParameters(idHostalHabitacion);
        return (long) q.executeUnique();
	}

	
	public HostalHabitacion darHostalHabitacionPorId (PersistenceManager pm, long idHostalHabitacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHostalHabitacion () + " WHERE id = ?");
		q.setResultClass(HostalHabitacion.class);
		q.setParameters(idHostalHabitacion);
		return (HostalHabitacion) q.executeUnique();
	}


	public List<HostalHabitacion> darHostalHabitacionsPorNombre (PersistenceManager pm, String nombreHostalHabitacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHostalHabitacion () + " WHERE nombre = ?");
		q.setResultClass(HostalHabitacion.class);
		q.setParameters(nombreHostalHabitacion);
		return (List<HostalHabitacion>) q.executeList();
	}

	
	public List<HostalHabitacion> darHostalHabitacions (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHostalHabitacion ());
		q.setResultClass(HostalHabitacion.class);
		return (List<HostalHabitacion>) q.executeList();
	}




}
