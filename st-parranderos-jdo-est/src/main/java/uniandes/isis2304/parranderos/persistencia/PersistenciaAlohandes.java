/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.persistencia;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.parranderos.negocio.Alojamiento;
import uniandes.isis2304.parranderos.negocio.AptoTemporada;
import uniandes.isis2304.parranderos.negocio.Bar;
import uniandes.isis2304.parranderos.negocio.Bebedor;
import uniandes.isis2304.parranderos.negocio.Bebida;
import uniandes.isis2304.parranderos.negocio.Cliente;
import uniandes.isis2304.parranderos.negocio.Empresa;
import uniandes.isis2304.parranderos.negocio.Gustan;
import uniandes.isis2304.parranderos.negocio.Hostal;
import uniandes.isis2304.parranderos.negocio.Sirven;
import uniandes.isis2304.parranderos.negocio.Servicio;
import uniandes.isis2304.parranderos.negocio.Visitan;

/**
 * Clase para el manejador de persistencia del proyecto Parranderos
 * Traduce la información entre objetos Java y tuplas de la base de datos, en ambos sentidos
 * Sigue un patrón SINGLETON (Sólo puede haber UN objeto de esta clase) para comunicarse de manera correcta
 * con la base de datos
 * Se apoya en las clases SQLBar, SQLBebedor, SQLBebida, SQLGustan, SQLSirven, SQLServicio y SQLVisitan, que son 
 * las que realizan el acceso a la base de datos
 * 
 * @author Germán Bravo
 */
public class PersistenciaAlohandes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaAlohandes.class.getName());
	
	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaAlohandes instance;
	
	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;
	
	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	 * Secuenciador, Servicio, bebida, bar, bebedor, gustan, sirven y visitan
	 */
	private List <String> tablas;
	
	/**
	 * Atributo para el acceso a las sentencias SQL propias a PersistenciaParranderos
	 */
	private SQLUtil sqlUtil;
	
	/**
	 * Atributo para el acceso a la tabla Servicio de la base de datos
	 */
	private SQLAptoSemestre sqlAptoSemestre;
	
	/**
	 * Atributo para el acceso a la tabla BEBIDA de la base de datos
	 */
	private SQLAptoTemporada sqlAptoTemporada;
	
	/**
	 * Atributo para el acceso a la tabla BAR de la base de datos
	 */
	private SQLAlojamiento sqlAlojamiento;
	private SQLEmpresa sqlEmpresa;
	private SQLCliente sqlCliente;
	private SQLHostal sqlHostal;
	private SQLHostalHabitacion sqlHostalHabitacion;
	private SQLHotel sqlHotel;
	private SQLHotelHabitacion sqlHotelHabitacion;
	private SQLPropietario sqlPropietario;
	private SQLProveedor sqlProveedor;
	private SQLReserva sqlReserva;
	private SQLServicio sqlServicio;
	private SQLAlojamiento sqlViviendaUniversitaria;
	/**
	 * Atributo para el acceso a la tabla BEBIDA de la base de datos
	 */

	
	/* ****************************************************************
	 * 			Métodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON
	 */
	private PersistenciaAlohandes ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("Parranderos");		
		crearClasesSQL ();
		
		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("Parranderos_sequence");
		tablas.add ("Cliente");
		tablas.add ("Reserva");
		tablas.add ("Alojamiento");
		tablas.add ("HostalHabitacion");
		tablas.add ("HotelHabitacion");
		tablas.add ("ViviendaUniversitaria");
		tablas.add ("AptoSemestre");

		tablas.add ("AptoTemporada");
		tablas.add ("Servicio");
		tablas.add ("Hostal");
		tablas.add ("Hotel");
		tablas.add ("Proveedor");
		tablas.add ("Empresa");
		tablas.add ("Propietario");
}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaAlohandes (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);
		
		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	/**
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaAlohandes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaAlohandes ();
		}
		return instance;
	}
	
	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaAlohandes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaAlohandes (tableConfig);
		}
		return instance;
	}

	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}
	
	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}
	
	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{
		sqlServicio = new SQLServicio(this);
		sqlAlojamiento = new SQLAlojamiento(this);
		sqlCliente = new SQLCliente(this);
		sqlReserva = new SQLReserva(this);
		sqlHostalHabitacion = new SQLHostalHabitacion(this);
		sqlHotelHabitacion = new SQLHotelHabitacion (this);
		sqlViviendaUniversitaria = new SQLViviendaUniversitaria(this);	
		sqlAptoSemestre = new SQLAptoSemestre(this);		

		sqlAptoTemporada = new SQLAptoTemporada(this);		

		sqlHostal = new SQLHostal(this);		

		sqlHotel = new SQLHotel(this);		

		sqlProveedor = new SQLProveedor(this);	
		sqlEmpresa = new SQLEmpresa(this);		

		sqlPropietario = new SQLPropietario(this);		


		sqlUtil = new SQLUtil(this);
	}

	/**
	 * @return La cadena de caracteres con el nombre del secuenciador de parranderos
	 */
	public String darSeqParranderos ()
	{
		return tablas.get (0);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Servicio de parranderos
	 */
	public String darTablaCliente ()
	{
		return tablas.get (1);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bebida de parranderos
	 */
	public String darTablaReserva ()
	{
		return tablas.get (2);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bar de parranderos
	 */
	public String darTablaAlojamiento ()
	{
		return tablas.get (3);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Bebedor de parranderos
	 */
	public String darTablaHostalHabitacion ()
	{
		return tablas.get (4);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Gustan de parranderos
	 */
	public String darTablaHotelHabitacion ()
	{
		return tablas.get (5);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Sirven de parranderos
	 */
	public String darTablaViviendaUniversitaria ()
	{
		return tablas.get (6);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaAptoSemestre ()
	{
		return tablas.get (7);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaAptoTemporada ()
	{
		return tablas.get (8);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaServicio ()
	{
		return tablas.get (9);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaHostal ()
	{
		return tablas.get (10);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaHotel ()
	{
		return tablas.get (11);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaProveedor ()
	{
		return tablas.get (12);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaEmpresa ()
	{
		return tablas.get (13);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Visitan de parranderos
	 */
	public String darTablaPropietario ()
	{
		return tablas.get (14);
	}
	
	/**
	 * Transacción para el generador de secuencia de Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de Parranderos
	 */
	private long nextval ()
	{
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
    }
	
	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/* ****************************************************************
	 * 			Métodos para manejar SERVICIOS
	 *****************************************************************/

	
	public AptoTemporada adicionarAptoTemporada(long idAptoTemporada, String nombre, String ubicacion, String tipooferta)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idServicio = nextval ();
            long tuplasInsertadas = sqlAptoTemporada.adicionarAptoTemporada(pm, idAptoTemporada, nombre, ubicacion, tipooferta);
            tx.commit();
            
            log.trace ("Inserción de tipo de bebida: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new AptoTemporada();
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	


	public long eliminarAptoTemporadaPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlAptoTemporada.eliminarAptoTemporadaPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<AptoTemporada> darAptoTemporadas ()
	{
		return sqlAptoTemporada.darAptoTemporadaes (pmf.getPersistenceManager());
	}
 
	
 
	
	public AptoTemporada darAptoTemporadaPorId (long id)
	{
		return sqlAptoTemporada.darAptoTemporadaPorId (pmf.getPersistenceManager(), id);
	}

	/* ****************************************************************
	 * 			Métodos para manejar SERVICIOS
	 *****************************************************************/

	
	public Cliente adicionarCliente(long idCliente, String nombre, String ubicacion, String tipooferta)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idServicio = nextval ();
            long tuplasInsertadas = sqlCliente.adicionarCliente(pm, idCliente, nombre, ubicacion, tipooferta);
            tx.commit();
            
            log.trace ("Inserción de tipo de bebida: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Cliente();
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	


	public long eliminarClientePorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlCliente.eliminarClientePorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<Cliente> darClientes ()
	{
		return sqlCliente.darClientes (pmf.getPersistenceManager());
	}
 
	
 
	
	public Cliente darClientePorId (long id)
	{
		return sqlCliente.darClientePorId (pmf.getPersistenceManager(), id);
	}

	/* ****************************************************************
	 * 			Métodos para manejar SERVICIOS
	 *****************************************************************/

	
	public Empresa adicionarEmpresa(long idEmpresa, String nombre, String ubicacion, String tipooferta)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idServicio = nextval ();
            long tuplasInsertadas = sqlEmpresa.adicionarEmpresa(pm, idEmpresa, nombre, ubicacion, tipooferta);
            tx.commit();
            
            log.trace ("Inserción de tipo de bebida: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Empresa();
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	


	public long eliminarEmpresaPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlEmpresa.eliminarEmpresaPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<Empresa> darEmpresas ()
	{
		return sqlEmpresa.darEmpresas (pmf.getPersistenceManager());
	}
 
	
 
	
	public Empresa darEmpresaPorId (long id)
	{
		return sqlEmpresa.darEmpresaPorId (pmf.getPersistenceManager(), id);
	}

	/* ****************************************************************
	 * 			Métodos para manejar SERVICIOS
	 *****************************************************************/

	
	public Hostal adicionarHostal(long idHostal, String nombre, String ubicacion, String tipooferta)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idServicio = nextval ();
            long tuplasInsertadas = sqlHostal.adicionarHostal(pm, idHostal, nombre, ubicacion, tipooferta);
            tx.commit();
            
            log.trace ("Inserción de tipo de bebida: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Hostal();
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	


	public long eliminarHostalPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHostal.eliminarHostalPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<Hostal> darHostals ()
	{
		return sqlHostal.darHostals (pmf.getPersistenceManager());
	}
 
	
 
	
	public Hostal darHostalPorId (long id)
	{
		return sqlHostal.darHostalPorId (pmf.getPersistenceManager(), id);
	}

	/* ****************************************************************
	 * 			Métodos para manejar SERVICIOS
	 *****************************************************************/

	
	public Hostal adicionarHostal(long idHostal, String nombre, String ubicacion, String tipooferta)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idServicio = nextval ();
            long tuplasInsertadas = sqlHostal.adicionarHostal(pm, idHostal, nombre, ubicacion, tipooferta);
            tx.commit();
            
            log.trace ("Inserción de tipo de bebida: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Hostal();
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	


	public long eliminarHostalPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHostal.eliminarHostalPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<Hostal> darHostals ()
	{
		return sqlHostal.darHostals (pmf.getPersistenceManager());
	}
 
	
 
	
	public Hostal darHostalPorId (long id)
	{
		return sqlHostal.darHostalPorId (pmf.getPersistenceManager(), id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar SERVICIOS
	 *****************************************************************/

	
	public Alojamiento adicionarAlojamiento(long idAlojamiento, String nombre, String ubicacion, String tipooferta)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idServicio = nextval ();
            long tuplasInsertadas = sqlAlojamiento.adicionarAlojamiento(pm, idAlojamiento, nombre, ubicacion, tipooferta);
            tx.commit();
            
            log.trace ("Inserción de tipo de bebida: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Alojamiento();
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	


	public long eliminarAlojamientoPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlAlojamiento.eliminarAlojamientoPorId(pm, id);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<Alojamiento> darAlojamientos ()
	{
		return sqlAlojamiento.darAlojamientos (pmf.getPersistenceManager());
	}
 
	
 
	
	public Alojamiento darAlojamientoPorId (long id)
	{
		return sqlAlojamiento.darAlojamientoPorId (pmf.getPersistenceManager(), id);
	}
 
	/* ****************************************************************
	 * 			Métodos para manejar SERVICIOS
	 *****************************************************************/

	
	public Servicio adicionarServicio(String nombre, long idAlojamiento)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idServicio = nextval ();
            long tuplasInsertadas = sqlServicio.adicionarServicio(pm, idServicio, nombre, idAlojamiento);
            tx.commit();
            
            log.trace ("Inserción de tipo de bebida: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            
            return new Servicio();
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	


	public long eliminarServicioPorId (long idServicio) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlServicio.eliminarServicioPorId(pm, idServicio);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<Servicio> darServicios ()
	{
		return sqlServicio.darServicios (pmf.getPersistenceManager());
	}
 
	
	public List<Servicio> darServicioPorNombre (String nombre)
	{
		return sqlServicio.darServiciosPorNombre (pmf.getPersistenceManager(), nombre);
	}
 
	
	public Servicio darServicioPorId (long idServicio)
	{
		return sqlServicio.darServicioPorId (pmf.getPersistenceManager(), idServicio);
	}
 

	
	public long [] limpiarParranderos ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long [] resp = sqlUtil.limpiarParranderos (pm);
            tx.commit ();
            log.info ("Borrada la base de datos");
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return new long[] {-1, -1, -1, -1, -1, -1, -1};
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
		
	}


 }
