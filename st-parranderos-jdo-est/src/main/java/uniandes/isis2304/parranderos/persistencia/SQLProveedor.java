
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

		/**
		 * Constructor
		 * @param pp - El Manejador de persistencia de la aplicación
		 */
		public SQLProveedor (PersistenciaAlohandes pp)
		{
			this.pp = pp;
		}
		
		/**
		 * Crea y ejecuta la sentencia SQL para adicionar un BAR a la base de datos de Parranderos
		 * @param pm - El manejador de persistencia
		 * @param idProveedor - El identificador del bar
		 * @param tipo - El tipo del bar
		 * @param ciudad - La ciudad del bar
		 * @param presupuesto - El presupuesto del bar (ALTO, MEDIO, BAJO)
		 * @param sedes - El número de sedes del bar
		 * @return El número de tuplas insertadas
		 */
		public long adicionarProveedor (PersistenceManager pm, long idProveedor, String tipo) 
		{
	        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaProveedor () + "(id, tipo) values (?, ?)");
	        q.setParameters(idProveedor, tipo);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para eliminar BARES de la base de datos de Parranderos, por su tipo
		 * @param pm - El manejador de persistencia
		 * @param tipoProveedor - El tipo del bar
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarProveedoresPorTipo (PersistenceManager pm, String tipoProveedor)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveedor () + " WHERE tipo = ?");
	        q.setParameters(tipoProveedor);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para eliminar UN BAR de la base de datos de Parranderos, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idProveedor - El identificador del bar
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarProveedorPorId (PersistenceManager pm, long idProveedor)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaProveedor () + " WHERE id = ?");
	        q.setParameters(idProveedor);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de UN BAR de la 
		 * base de datos de Parranderos, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idProveedor - El identificador del bar
		 * @return El objeto BAR que tiene el identificador dado
		 */
		public Proveedor darProveedorPorId (PersistenceManager pm, long idProveedor) 
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProveedor () + " WHERE id = ?");
			q.setResultClass(Proveedor.class);
			q.setParameters(idProveedor);
			return (Proveedor) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
		 * base de datos de Parranderos, por su tipo
		 * @param pm - El manejador de persistencia
		 * @param tipoProveedor - El tipo de bar buscado
		 * @return Una lista de objetos BAR que tienen el tipo dado
		 */
		public List<Proveedor> darProveedoresPorTipo (PersistenceManager pm, String tipoProveedor) 
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProveedor () + " WHERE tipo = ?");
			q.setResultClass(Proveedor.class);
			q.setParameters(tipoProveedor);
			return (List<Proveedor>) q.executeList();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
		 * base de datos de Parranderos
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos BAR
		 */
		public List<Proveedor> darProveedores (PersistenceManager pm)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaProveedor ());
			q.setResultClass(Proveedor.class);
			return (List<Proveedor>) q.executeList();
		}




	}


}
