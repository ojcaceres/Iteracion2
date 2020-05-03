package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;

import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Propietario;

public class SQLPropietario {
	
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

	
	public SQLPropietario (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	
	
	public long adicionarPropietario (PersistenceManager pm, String id, String nombre, String correo, int telefono) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPropietario () + "(id,  nombre, correo,  telefono) values (?, ?, ?, ?)");
        q.setParameters(id,  correo,  telefono);
        return (long) q.executeUnique();
	}

	
	public long eliminarPropietariosPorNombre (PersistenceManager pm, String nombrePropietario)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPropietario () + " WHERE nombre = ?");
        q.setParameters(nombrePropietario);
        return (long) q.executeUnique();
	}

	
	public long eliminarPropietarioPorId (PersistenceManager pm, long idPropietario)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPropietario () + " WHERE id = ?");
        q.setParameters(idPropietario);
        return (long) q.executeUnique();
	}

	
	public Propietario darPropietarioPorId (PersistenceManager pm, long idPropietario) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPropietario () + " WHERE id = ?");
		q.setResultClass(Propietario.class);
		q.setParameters(idPropietario);
		return (Propietario) q.executeUnique();
	}


	public List<Propietario> darPropietariosPorNombre (PersistenceManager pm, String nombrePropietario) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPropietario () + " WHERE nombre = ?");
		q.setResultClass(Propietario.class);
		q.setParameters(nombrePropietario);
		return (List<Propietario>) q.executeList();
	}

	
	public List<Propietario> darPropietarios (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPropietario ());
		q.setResultClass(Propietario.class);
		return (List<Propietario>) q.executeList();
	}




}
