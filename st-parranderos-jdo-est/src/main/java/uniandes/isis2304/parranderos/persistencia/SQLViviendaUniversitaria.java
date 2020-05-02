package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;

import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.ViviendaUniversitaria;

public class SQLViviendaUniversitaria {
	
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

	
	public SQLViviendaUniversitaria (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	
	
	public long adicionarViviendaUniversitaria (PersistenceManager pm, long idViviendaUniversitaria, String nombre, String ubicacion, String tipooferta, String tipo) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaViviendaUniversitaria () + "(id, nombre, ubicacion, tipooferta	, tipo) values (?, ?, ?, ?, ?)");
        q.setParameters(idViviendaUniversitaria, nombre, ubicacion, tipooferta, tipo);
        return (long) q.executeUnique();
	}

	
	public long eliminarViviendaUniversitariasPorNombre (PersistenceManager pm, String nombreViviendaUniversitaria)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaViviendaUniversitaria () + " WHERE nombre = ?");
        q.setParameters(nombreViviendaUniversitaria);
        return (long) q.executeUnique();
	}

	
	public long eliminarViviendaUniversitariaPorId (PersistenceManager pm, long idViviendaUniversitaria)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaViviendaUniversitaria () + " WHERE id = ?");
        q.setParameters(idViviendaUniversitaria);
        return (long) q.executeUnique();
	}

	
	public ViviendaUniversitaria darViviendaUniversitariaPorId (PersistenceManager pm, long idViviendaUniversitaria) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaViviendaUniversitaria () + " WHERE id = ?");
		q.setResultClass(ViviendaUniversitaria.class);
		q.setParameters(idViviendaUniversitaria);
		return (ViviendaUniversitaria) q.executeUnique();
	}


	public List<ViviendaUniversitaria> darViviendaUniversitariasPorNombre (PersistenceManager pm, String nombreViviendaUniversitaria) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaViviendaUniversitaria () + " WHERE nombre = ?");
		q.setResultClass(ViviendaUniversitaria.class);
		q.setParameters(nombreViviendaUniversitaria);
		return (List<ViviendaUniversitaria>) q.executeList();
	}

	
	public List<ViviendaUniversitaria> darViviendaUniversitarias (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaViviendaUniversitaria ());
		q.setResultClass(ViviendaUniversitaria.class);
		return (List<ViviendaUniversitaria>) q.executeList();
	}




}
