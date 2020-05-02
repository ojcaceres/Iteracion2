package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;

import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Proveedor;

public class SQLProveedor {
	
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

	
	public SQLProveedor (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	
	
	public long adicionarProveedor (PersistenceManager pm, long idProveedor, String nombre, String ubicacion, String tipooferta, String tipo) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProveedor () + "(id, nombre, ubicacion, tipooferta	, tipo) values (?, ?, ?, ?, ?)");
        q.setParameters(idProveedor, nombre, ubicacion, tipooferta, tipo);
        return (long) q.executeUnique();
	}

	
	public long eliminarProveedorsPorNombre (PersistenceManager pm, String nombreProveedor)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveedor () + " WHERE nombre = ?");
        q.setParameters(nombreProveedor);
        return (long) q.executeUnique();
	}

	
	public long eliminarProveedorPorId (PersistenceManager pm, long idProveedor)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveedor () + " WHERE id = ?");
        q.setParameters(idProveedor);
        return (long) q.executeUnique();
	}

	
	public Proveedor darProveedorPorId (PersistenceManager pm, long idProveedor) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProveedor () + " WHERE id = ?");
		q.setResultClass(Proveedor.class);
		q.setParameters(idProveedor);
		return (Proveedor) q.executeUnique();
	}


	public List<Proveedor> darProveedorsPorNombre (PersistenceManager pm, String nombreProveedor) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProveedor () + " WHERE nombre = ?");
		q.setResultClass(Proveedor.class);
		q.setParameters(nombreProveedor);
		return (List<Proveedor>) q.executeList();
	}

	
	public List<Proveedor> darProveedors (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProveedor ());
		q.setResultClass(Proveedor.class);
		return (List<Proveedor>) q.executeList();
	}




}
