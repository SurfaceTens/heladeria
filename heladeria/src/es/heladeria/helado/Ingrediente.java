package es.heladeria.helado;

import java.text.DecimalFormat;

public abstract class Ingrediente implements Ingredientable {
	private String descripcion;
	private double precio;

	public String getDescripcion() {
		return descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	protected void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	protected void setPrecio(double precio) {
		this.precio = precio;
	}

	public Ingrediente(String descripcion, double precio) {
		setDescripcion(descripcion);
		setPrecio(precio);
	}
	
	public String stringPecio() {
		String textito;
		DecimalFormat euros = new DecimalFormat("0.00");

		textito = euros.format(getPrecio()) + "€";
		return textito;
	}
	
	@Override
	public String toString() {
		return getDescripcion();
	}

}
