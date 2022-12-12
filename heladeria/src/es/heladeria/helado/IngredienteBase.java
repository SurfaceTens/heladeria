package es.heladeria.helado;

public class IngredienteBase extends Ingrediente {
	private String sabor;
	final private static double PRECIO_DEFECTO = 2.0;
	final private static String SABOR_DEFECTO = "Vainilla";
	private static String DESCRIPCION = "Bola de ";
	
	public String getSabor() {
		return sabor;
	}
	
	protected void setSabor(String sabor) {
		this.sabor = sabor;
	}
	
	public IngredienteBase(String sabor, double precio) {
		super(DESCRIPCION + sabor, precio);
		setSabor(sabor);
	}
	
	public IngredienteBase(String sabor) {
		this(sabor, PRECIO_DEFECTO);
	}
	
	public IngredienteBase() {
		this(SABOR_DEFECTO, PRECIO_DEFECTO);
	}

	@Override
	public boolean sirveComoBase() {
		return true;
	}

}
