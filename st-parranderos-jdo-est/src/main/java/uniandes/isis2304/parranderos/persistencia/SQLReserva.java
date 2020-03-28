
	package uniandes.isis2304.parranderos.persistencia;

	import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
	import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Reserva;

	public class SQLReserva {
		
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
		public SQLReserva (PersistenciaAlohandes pp)
		{
			this.pp = pp;
		}
		


		/**
		 * Crea y ejecuta la sentencia SQL para eliminar BARES de la base de datos de Parranderos, por su fechaCheckIn
		 * @param pm - El manejador de persistencia
		 * @param fechaCheckInReserva - El fechaCheckIn del bar
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarReservaesPorFechaCheckIn (PersistenceManager pm, String fechaCheckInReserva)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaReserva () + " WHERE fechaCheckIn = ?");
	        q.setParameters(fechaCheckInReserva);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para eliminar UN BAR de la base de datos de Parranderos, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idReserva - El identificador del bar
		 * @return EL número de tuplas eliminadas
		 */
		public long eliminarReservaPorId (PersistenceManager pm, long idReserva)
		{
	        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaReserva () + " WHERE id = ?");
	        q.setParameters(idReserva);
	        return (long) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de UN BAR de la 
		 * base de datos de Parranderos, por su identificador
		 * @param pm - El manejador de persistencia
		 * @param idReserva - El identificador del bar
		 * @return El objeto BAR que tiene el identificador dado
		 */
		public Reserva darReservaPorId (PersistenceManager pm, long idReserva) 
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaReserva () + " WHERE id = ?");
			q.setResultClass(Reserva.class);
			q.setParameters(idReserva);
			return (Reserva) q.executeUnique();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
		 * base de datos de Parranderos, por su fechaCheckIn
		 * @param pm - El manejador de persistencia
		 * @param fechaCheckInReserva - El fechaCheckIn de bar buscado
		 * @return Una lista de objetos BAR que tienen el fechaCheckIn dado
		 */
		public List<Reserva> darReservasPorFechaCheckIn (PersistenceManager pm, String fechaCheckInReserva) 
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaReserva () + " WHERE fechaCheckIn = ?");
			q.setResultClass(Reserva.class);
			q.setParameters(fechaCheckInReserva);
			return (List<Reserva>) q.executeList();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS BARES de la 
		 * base de datos de Parranderos
		 * @param pm - El manejador de persistencia
		 * @return Una lista de objetos BAR
		 */
		public List<Reserva> darReservas (PersistenceManager pm)
		{
			Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaReserva ());
			q.setResultClass(Reserva.class);
			return (List<Reserva>) q.executeList();
		}

		/**
		 * Crea y ejecuta la sentencia SQL para adicionar un BAR a la base de datos de Parranderos
		 * @param pm - El manejador de persistencia
		 * @param idReserva - El identificador del bar
		 * @param fechaCheckIn - El fechaCheckIn del bar
		 * @param ciudad - La ciudad del bar
		 * @param presupuesto - El presupuesto del bar (ALTO, MEDIO, BAJO)
		 * @param sedes - El número de sedes del bar
		 * @return El número de tuplas insertadas
		 */

		
		public long adicionarReserva(PersistenceManager pm, long id, long idCliente, long idAlojamiento,
				int descuento, int cantpagos, Date fechaConfirmacion, Date fechaCheckIn, Date fechaCheckOut,
				double precioTotal) {
			Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaReserva () + "(id,  idCliente, idAlojamiento, descuento, cantpagos, fechaConfirmacion, fechaCheckIn, fechaCheckOut, precioTotal) values (?, ?, ?, ?, ?)");
	        q.setParameters(id,  idCliente, idAlojamiento, descuento, cantpagos, fechaConfirmacion, 
	    			fechaCheckIn, fechaCheckOut, precioTotal);
	        return (long) q.executeUnique();
			
		}




	}


}
