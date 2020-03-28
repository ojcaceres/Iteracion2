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

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.AptoSemestre;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto AptoSemestre de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLAptoSemestre 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
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
	public SQLAptoSemestre (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	
	
	public long adicionarAptoSemestre (PersistenceManager pm, long id, Integer precioMes, Boolean amoblado, Integer habitaciones, String tipoOferta) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaAptoSemestre () + "(id, nombre, ciudad, presupuesto, cantsedes) values (?, ?, ?, ?, ?)");
        q.setParameters(id, precioMes, amoblado, habitaciones, tipoOferta);
        return (long) q.executeUnique();
	}

	
	/**
	 * Crea y ejecuta la sentencia SQL para eliminar UN AptoSemestre de la base de datos de Parranderos, por su identificador
	
	 */
	public long eliminarAptoSemestrePorId (PersistenceManager pm, long id)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaAptoSemestre () + " WHERE id = ?");
        q.setParameters(id);
        return (long) q.executeUnique();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de UN AptoSemestre de la 
	
	 */
	public AptoSemestre darAptoSemestrePorId (PersistenceManager pm, long id) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAptoSemestre () + " WHERE id = ?");
		q.setResultClass(AptoSemestre.class);
		q.setParameters(id);
		return (AptoSemestre) q.executeUnique();
	}

	

	/**
	 * Crea y ejecuta la sentencia SQL para encontrar la información de LOS AptoSemestreES de la 
	
	 */
	public List<AptoSemestre> darAptoSemestres (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaAptoSemestre ());
		q.setResultClass(AptoSemestre.class);
		return (List<AptoSemestre>) q.executeList();
	}

	/**
	 * Crea y ejecuta la sentencia SQL para aumentar en uno el número de sedes de los AptoSemestrees de la 
	
	 */
	public long aumentarSedesAptoSemestresCiudad (PersistenceManager pm, String ciudad)
	{
        Query q = pm.newQuery(SQL, "UPDATE " + pp.darTablaAptoSemestre () + " SET cantsedes = cantsedes + 1 WHERE ciudad = ?");
        q.setParameters(ciudad);
        return (long) q.executeUnique();
	}
	
}
