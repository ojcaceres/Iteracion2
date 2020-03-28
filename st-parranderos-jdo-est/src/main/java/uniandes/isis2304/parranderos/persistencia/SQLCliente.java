
	package uniandes.isis2304.parranderos.persistencia;

	import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
	import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Cliente;

	public class SQLCliente {
		
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
		public SQLCliente (PersistenciaAlohandes pp)
		{
			this.pp = pp;
		}
		
		/**
		 * Crea y ejecuta la sentencia SQL para adicionar un BAR a la base de datos de Parranderos
		 * @param pm - El manejador de persistencia
		 * @param idCliente - El identificador del bar
		 * @param Nombre - El Nombre del bar
		 * @param ciudad - La ciudad del bar
		 * @param presupuesto - El presupuesto del bar (ALTO, MEDIO, BAJO)
		 * @param sedes - El número de sedes del bar
		 * @return El número de tuplas insertadas
		 */
		public long adicionarCliente (PersistenceManager pm, long documento, String Nombre, String ciudad, String contrasena, String email,  long telefono, String genero, String vinculacion, Date fechaNacimiento) 
		{
	        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCliente () + "(documento, Nombre, ciudad, contrasena, email, telefono, genero, vinculacion, fechaNacimiento) values (?, ?, ?, ?, ?)");
	        q.setParameters(documento, Nombre, ciudad, contrasena, email, telefono, genero, vinculacion, fechaNacimiento);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para eliminar BARES de la base de datos de Parranderos, por su Nombre
		 * @param pm - El manejador de persistencia
		 * @param NombreCliente - El Nombre del bar
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarClienteesPorNombre (PersistenceManager pm, String NombreCliente)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCliente () + " WHERE Nombre = ?");
	        q.setParameters(NombreCliente);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para eliminar UN BAR de la base de datos de Parranderos, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idCliente - El identificador del bar
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarClientePorId (PersistenceManager pm, long idCliente)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCliente () + " WHERE id = ?");
	        q.setParameters(idCliente);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de UN BAR de la 
		 * base de datos de Parranderos, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idCliente - El identificador del bar
		 * @return El objeto BAR que tiene el identificador dado
		 */
		public Cliente darClientePorId (PersistenceManager pm, long idCliente) 
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente () + " WHERE id = ?");
			q.setResultClass(Cliente.class);
			q.setParameters(idCliente);
			return (Cliente) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
		 * base de datos de Parranderos, por su Nombre
		 * @param pm - El manejador de persistencia
		 * @param NombreCliente - El Nombre de bar buscado
		 * @return Una lista de objetos BAR que tienen el Nombre dado
		 */
		public List<Cliente> darClienteesPorNombre (PersistenceManager pm, String NombreCliente) 
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente () + " WHERE Nombre = ?");
			q.setResultClass(Cliente.class);
			q.setParameters(NombreCliente);
			return (List<Cliente>) q.executeList();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
		 * base de datos de Parranderos
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos BAR
		 */
		public List<Cliente> darClientees (PersistenceManager pm)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente ());
			q.setResultClass(Cliente.class);
			return (List<Cliente>) q.executeList();
		}




	}


}
