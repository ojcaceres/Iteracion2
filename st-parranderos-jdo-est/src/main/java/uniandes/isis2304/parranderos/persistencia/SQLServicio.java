
	package uniandes.isis2304.parranderos.persistencia;

	import java.util.List;

import javax.jdo.PersistenceManager;
	import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Servicio;

	public class SQLServicio {
		
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
		public SQLServicio (PersistenciaAlohandes pp)
		{
			this.pp = pp;
		}
		
		/**
		 * Crea y ejecuta la sentencia SQL para adicionar un BAR a la base de datos de Parranderos
		 * @param pm - El manejador de persistencia
		 * @param idServicio - El identificador del bar
		 * @param Nombre - El Nombre del bar
		 * @param ciudad - La ciudad del bar
		 * @param presupuesto - El presupuesto del bar (ALTO, MEDIO, BAJO)
		 * @param sedes - El número de sedes del bar
		 * @return El número de tuplas insertadas
		 */
		public long adicionarServicio (PersistenceManager pm, long idServicio, String Nombre, long idAlojamiento) 
		{
	        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaServicio () + "(id, Nombre, idAlojamiento) values (?, ?, ?)");
	        q.setParameters(idServicio, Nombre, idAlojamiento);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para eliminar BARES de la base de datos de Parranderos, por su Nombre
		 * @param pm - El manejador de persistencia
		 * @param NombreServicio - El Nombre del bar
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarServicioesPorNombre (PersistenceManager pm, String NombreServicio)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServicio () + " WHERE Nombre = ?");
	        q.setParameters(NombreServicio);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para eliminar UN BAR de la base de datos de Parranderos, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idServicio - El identificador del bar
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarServicioPorId (PersistenceManager pm, long idServicio)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServicio () + " WHERE id = ?");
	        q.setParameters(idServicio);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de UN BAR de la 
		 * base de datos de Parranderos, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idServicio - El identificador del bar
		 * @return El objeto BAR que tiene el identificador dado
		 */
		public Servicio darServicioPorId (PersistenceManager pm, long idServicio) 
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicio () + " WHERE id = ?");
			q.setResultClass(Servicio.class);
			q.setParameters(idServicio);
			return (Servicio) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
		 * base de datos de Parranderos, por su Nombre
		 * @param pm - El manejador de persistencia
		 * @param NombreServicio - El Nombre de bar buscado
		 * @return Una lista de objetos BAR que tienen el Nombre dado
		 */
		public List<Servicio> darServiciosPorNombre (PersistenceManager pm, String NombreServicio) 
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicio () + " WHERE Nombre = ?");
			q.setResultClass(Servicio.class);
			q.setParameters(NombreServicio);
			return (List<Servicio>) q.executeList();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
		 * base de datos de Parranderos
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos BAR
		 */
		public List<Servicio> darServicios (PersistenceManager pm)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicio ());
			q.setResultClass(Servicio.class);
			return (List<Servicio>) q.executeList();
		}




	}



