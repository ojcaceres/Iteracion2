
	package uniandes.isis2304.parranderos.persistencia;

	import java.util.List;

import javax.jdo.PersistenceManager;
	import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Hostal;

	public class SQLHostal {
		
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
		public SQLHostal (PersistenciaAlohandes pp)
		{
			this.pp = pp;
		}
		
		/**
		 * Crea y ejecuta la sentencia SQL para adicionar un BAR a la base de datos de Parranderos
		 * @param pm - El manejador de persistencia
		 * @param idHostal - El identificador del bar
		 * @param nombre - El nombre del bar
		 * @param ciudad - La ciudad del bar
		 * @param presupuesto - El presupuesto del bar (ALTO, MEDIO, BAJO)
		 * @param sedes - El número de sedes del bar
		 * @return El número de tuplas insertadas
		 */
		public long adicionarHostal (PersistenceManager pm, long idHostal, String nombre, String ciudad, String presupuesto, int sedes) 
		{
	        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHostal () + "(id, nombre, ciudad, presupuesto, cantsedes) values (?, ?, ?, ?, ?)");
	        q.setParameters(idHostal, nombre, ciudad, presupuesto, sedes);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para eliminar BARES de la base de datos de Parranderos, por su nombre
		 * @param pm - El manejador de persistencia
		 * @param nombreHostal - El nombre del bar
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarHostalesPorNombre (PersistenceManager pm, String nombreHostal)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHostal () + " WHERE nombre = ?");
	        q.setParameters(nombreHostal);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para eliminar UN BAR de la base de datos de Parranderos, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idHostal - El identificador del bar
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarHostalPorId (PersistenceManager pm, long idHostal)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHostal () + " WHERE id = ?");
	        q.setParameters(idHostal);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de UN BAR de la 
		 * base de datos de Parranderos, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idHostal - El identificador del bar
		 * @return El objeto BAR que tiene el identificador dado
		 */
		public Hostal darHostalPorId (PersistenceManager pm, long idHostal) 
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHostal () + " WHERE id = ?");
			q.setResultClass(Hostal.class);
			q.setParameters(idHostal);
			return (Hostal) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
		 * base de datos de Parranderos, por su nombre
		 * @param pm - El manejador de persistencia
		 * @param nombreHostal - El nombre de bar buscado
		 * @return Una lista de objetos BAR que tienen el nombre dado
		 */
		public List<Hostal> darHostalesPorNombre (PersistenceManager pm, String nombreHostal) 
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHostal () + " WHERE nombre = ?");
			q.setResultClass(Hostal.class);
			q.setParameters(nombreHostal);
			return (List<Hostal>) q.executeList();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
		 * base de datos de Parranderos
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos BAR
		 */
		public List<Hostal> darHostales (PersistenceManager pm)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHostal ());
			q.setResultClass(Hostal.class);
			return (List<Hostal>) q.executeList();
		}




	}


}
