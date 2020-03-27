package uniandes.isis2304.parranderos.negocio;

import java.util.List;

public class Proveedor implements VOProveedor
{

private long id;	
	
	/**
	 * El nombre del bebedor
	 */
	private String tipo;
	
	private List<Object[]> alojamientos;

    public Proveedor(long id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }
    
    public Proveedor() {
        this.id = 0;
        this.tipo = "";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public List<Object[]> getAlojamientos() {
        return alojamientos;
    }

    public void setAlojamientos(List<Object[]> alojamientos) {
        this.alojamientos = alojamientos;
    }

    @Override
    public String toString() {
        return "Proveedor[" + "id=" + id + ", tipo=" + tipo + ", alojamientos=" + alojamientos + ']';
    }


}
