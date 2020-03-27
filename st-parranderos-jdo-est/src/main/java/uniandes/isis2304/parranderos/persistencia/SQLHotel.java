
	package uniandes.isis2304.parranderos.persistencia;

	import java.util.List;

import javax.jdo.PersistenceManager;
	import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Hotel;

	public class SQLHotel {
		
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
		public SQLHotel (PersistenciaAlohandes pp)
		{
			this.pp = pp;
		}
		
		/**
		 * Crea y ejecuta la sentencia SQL para adicionar un BAR a la base de datos de Parranderos
		 * @param pm - El manejador de persistencia
		 * @param idHotel - El identificador del bar
		 * @param nombre - El nombre del bar
		 * @param ciudad - La ciudad del bar
		 * @param presupuesto - El presupuesto del bar (ALTO, MEDIO, BAJO)
		 * @param sedes - El número de sedes del bar
		 * @return El número de tuplas insertadas
		 */
		public long adicionarHotel (PersistenceManager pm, long idHotel, String nombre, String ciudad, String presupuesto, int sedes) 
		{
	        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHotel () + "(id, nombre, ciudad, presupuesto, cantsedes) values (?, ?, ?, ?, ?)");
	        q.setParameters(idHotel, nombre, ciudad, presupuesto, sedes);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para eliminar BARES de la base de datos de Parranderos, por su nombre
		 * @param pm - El manejador de persistencia
		 * @param nombreHotel - El nombre del bar
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarHotelesPorNombre (PersistenceManager pm, String nombreHotel)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHotel () + " WHERE nombre = ?");
	        q.setParameters(nombreHotel);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para eliminar UN BAR de la base de datos de Parranderos, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idHotel - El identificador del bar
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarHotelPorId (PersistenceManager pm, long idHotel)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHotel () + " WHERE id = ?");
	        q.setParameters(idHotel);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de UN BAR de la 
		 * base de datos de Parranderos, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idHotel - El identificador del bar
		 * @return El objeto BAR que tiene el identificador dado
		 */
		public Hotel darHotelPorId (PersistenceManager pm, long idHotel) 
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHotel () + " WHERE id = ?");
			q.setResultClass(Hotel.class);
			q.setParameters(idHotel);
			return (Hotel) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
		 * base de datos de Parranderos, por su nombre
		 * @param pm - El manejador de persistencia
		 * @param nombreHotel - El nombre de bar buscado
		 * @return Una lista de objetos BAR que tienen el nombre dado
		 */
		public List<Hotel> darHotelesPorNombre (PersistenceManager pm, String nombreHotel) 
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHotel () + " WHERE nombre = ?");
			q.setResultClass(Hotel.class);
			q.setParameters(nombreHotel);
			return (List<Hotel>) q.executeList();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
		 * base de datos de Parranderos
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos BAR
		 */
		public List<Hotel> darHoteles (PersistenceManager pm)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHotel ());
			q.setResultClass(Hotel.class);
			return (List<Hotel>) q.executeList();
		}




	}


}
