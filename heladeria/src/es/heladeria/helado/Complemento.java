package es.heladeria.helado;

public class Complemento extends Ingrediente {

	public Complemento(String descripcion, double precio) {
		super(descripcion, precio);
	}

	public Complemento() {
		// Por defecto
		this("", 0.0);
	}

	@Override
	public boolean sirveComoBase() {
		return false;
	}
}
