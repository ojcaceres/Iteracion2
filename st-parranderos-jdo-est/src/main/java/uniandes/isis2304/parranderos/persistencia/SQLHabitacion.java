package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;

import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Habitacion;

public class SQLHabitacion {
	
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

	
	public SQLHabitacion (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	
	
	public long adicionarHabitacion (PersistenceManager pm, long idHabitacion, String horario, int precioNoche, int capacidad, String tipoOferta) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHabitacion () + "(id, horario, precioNoche, capacidad, tipoOferta) values (?, ?, ?, ?, ?)");
        q.setParameters(idHabitacion,  horario, precioNoche, capacidad, tipoOferta);
        return (long) q.executeUnique();
	}

	
	public long eliminarHabitacionsPorNombre (PersistenceManager pm, String nombreHabitacion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacion () + " WHERE nombre = ?");
        q.setParameters(nombreHabitacion);
        return (long) q.executeUnique();
	}

	
	public long eliminarHabitacionPorId (PersistenceManager pm, long idHabitacion)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacion () + " WHERE id = ?");
        q.setParameters(idHabitacion);
        return (long) q.executeUnique();
	}

	
	public Habitacion darHabitacionPorId (PersistenceManager pm, long idHabitacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacion () + " WHERE id = ?");
		q.setResultClass(Habitacion.class);
		q.setParameters(idHabitacion);
		return (Habitacion) q.executeUnique();
	}


	public List<Habitacion> darHabitacionsPorNombre (PersistenceManager pm, String nombreHabitacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacion () + " WHERE nombre = ?");
		q.setResultClass(Habitacion.class);
		q.setParameters(nombreHabitacion);
		return (List<Habitacion>) q.executeList();
	}

	
	public List<Habitacion> darHabitacions (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacion ());
		q.setResultClass(Habitacion.class);
		return (List<Habitacion>) q.executeList();
	}




}
