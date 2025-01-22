package tiposComunicación;

import java.io.Serializable;

public class MiMensaje implements Serializable {
    private String nombre;
    private int cantidad;
    private boolean leido;

   

    public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	public int getCantidad() {
		return cantidad;
	}



	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}



	public boolean isLeido() {
		return leido;
	}



	public void setLeido(boolean leido) {
		this.leido = leido;
	}



	public MiMensaje(String nombre, int cantidad, boolean leido) {
		super();
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.leido = leido;
	}



	@Override
    public String toString() {
        return "MiMensaje{" +
                "nombre='" + nombre + '\'' +
                ", cantidad=" + cantidad +
                ", leido=" + leido +
                '}';
    }
}