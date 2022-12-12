package es.heladeria.helado;

public interface Ingredientable extends preciable, descriptible {
	public boolean sirveComoBase();

	public String stringPecio();
}
