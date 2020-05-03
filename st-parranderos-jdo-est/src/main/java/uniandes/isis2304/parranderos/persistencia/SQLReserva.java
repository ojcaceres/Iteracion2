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

	
	public SQLReserva (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	
	
	public long adicionarReserva (PersistenceManager pm, String idReserva, Date fechaConfirmacion, String fechaCheckin, String fechaCheckout, int precioTotal) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaReserva () + "(id, fechaConfirmacion, fechaCheckin, fechaCheckout, precioTotal) values (?, ?, ?, ?, ?)");
        q.setParameters(idReserva, fechaConfirmacion, fechaCheckin, fechaCheckout, precioTotal);
        return (long) q.executeUnique();
	}

	
	public long eliminarReservasPorNombre (PersistenceManager pm, String nombreReserva)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaReserva () + " WHERE nombre = ?");
        q.setParameters(nombreReserva);
        return (long) q.executeUnique();
	}

	
	public long eliminarReservaPorId (PersistenceManager pm, long idReserva)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaReserva () + " WHERE id = ?");
        q.setParameters(idReserva);
        return (long) q.executeUnique();
	}

	
	public Reserva darReservaPorId (PersistenceManager pm, long idReserva) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaReserva () + " WHERE id = ?");
		q.setResultClass(Reserva.class);
		q.setParameters(idReserva);
		return (Reserva) q.executeUnique();
	}


	public List<Reserva> darReservasPorNombre (PersistenceManager pm, String nombreReserva) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaReserva () + " WHERE nombre = ?");
		q.setResultClass(Reserva.class);
		q.setParameters(nombreReserva);
		return (List<Reserva>) q.executeList();
	}

	
	public List<Reserva> darReservas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaReserva ());
		q.setResultClass(Reserva.class);
		return (List<Reserva>) q.executeList();
	}




}
