package es.heladeria.helado;

import java.text.DecimalFormat;

import com.github.extras.Extra;

public class CopaHelado implements preciable, Comparable<CopaHelado> {
	private final static int MAX_COMPLEMENTOS = 3;
	private IngredienteBase ingredienteBase;
	private Ingredientable[] complementosCopa;
	private int numeroComplementos;

	// Array de complementos ejercicio 6.
	public static final Complemento VIRUTAS_DE_CHOCOLATE = new Complemento("Virutas de Chocolate", 0.5);
	public static final Complemento GALLETITAS = new Complemento("Galletitas", 0.8);
	public static final Complemento[] COMPLEMENTOS_6 = { VIRUTAS_DE_CHOCOLATE, GALLETITAS };

	// Extras de libreria externa.
	public final static Complemento[] COMPLEMENTOS_EXTRA = ImportarExtras();

	public int getMAX_COMPLEMENTOS() {
		return MAX_COMPLEMENTOS;
	}

	public Ingredientable[] getComplementosCopa() {
		return complementosCopa;
	}

	public IngredienteBase getIngredienteBase() {
		return ingredienteBase;
	}

	public int getNumeroComplementos() {
		return numeroComplementos;
	}

	protected void setComplementosCopa(Ingredientable[] complementosCopa) {
		this.complementosCopa = complementosCopa;
	}

	protected void setIngredienteBase(Ingredientable ingredienteBase) {
		if (ingredienteBase.sirveComoBase()) {
			this.ingredienteBase = (IngredienteBase) ingredienteBase;
		} else {
			this.ingredienteBase = new IngredienteBase();
		}
	}

	protected void setNumeroComplementos(int numeroComplementos) {
		this.numeroComplementos = numeroComplementos;
	}

	public static int getMaxComplementos() {
		return MAX_COMPLEMENTOS;
	}

	public CopaHelado(Ingredientable ingredienteBase, Ingredientable[] complementosCopa, int numeroComplementos) {
		setComplementosCopa(complementosCopa);
		setIngredienteBase(ingredienteBase);
		setNumeroComplementos(numeroComplementos);

	}

	public CopaHelado(Ingredientable ingredienteBase, Ingredientable[] complementosCopa) {
		this(ingredienteBase, complementosCopa, complementosCopa.length);

	}

	public CopaHelado(Ingredientable[] complementosCopa, int numeroComplementos) {
		this(new IngredienteBase(), complementosCopa, numeroComplementos);
	}

	public CopaHelado(Ingredientable ingredienteBase) {
		this(ingredienteBase, new Ingredientable[MAX_COMPLEMENTOS], 0);
	}

	public CopaHelado() {
		this(new IngredienteBase(), new Ingredientable[MAX_COMPLEMENTOS], 0);
	}

	@Override
	public double getPrecio() {
		double suma = 0.0;
		// Comprobar si hay ingrediente base.
		if (getIngredienteBase() != null) {
			suma = getIngredienteBase().getPrecio();
		}

		// Sumatorio de los complementos.
		for (int i = 0; i < complementosCopa.length; i++) {
			if ((complementosCopa[i] != null) && (complementosCopa[i] instanceof Ingredientable)) {
				suma += complementosCopa[i].getPrecio();
			}
		}

		return suma;
	}

	public void showPrecio() {
		double suma = 0.0;

		// Comprobar si hay ingrediente base.
		if (getIngredienteBase() != null) {
			suma = getIngredienteBase().getPrecio();
			System.out.println(getIngredienteBase().getDescripcion() + ":\t" + getIngredienteBase().getPrecio() + "€");
		}

		// Sumatorio de los complementos mostrando cada sumando.
		for (int i = 0; i < complementosCopa.length; i++) {
			if ((complementosCopa[i] != null) && (complementosCopa[i] instanceof Ingredientable)) {
				suma += complementosCopa[i].getPrecio();
				System.out
						.println(complementosCopa[i].getDescripcion() + ":\t" + complementosCopa[i].getPrecio() + "€");
			}
		}

		// Total
		System.out.println("→ Total:\t" + suma + "€");
	}

	public boolean addComplemento(Ingredientable complemento, boolean imprimir) {
		boolean retorno = false;
		if (getNumeroComplementos() < getComplementosCopa().length) {
			getComplementosCopa()[getNumeroComplementos()] = complemento;
			setNumeroComplementos(getNumeroComplementos() + 1);
			if (imprimir) {
				System.out.println("Añadido:\t" + complemento.getDescripcion());
			}
			retorno = true;
		} else {
			if (imprimir) {
				System.out.println("No añadido:\t" + complemento.getDescripcion());
			}
		}
		return retorno;
	}

	public boolean addComplemento(Ingredientable complemento) {
		return addComplemento(complemento, true);
	}

	public boolean addComplementos(Ingredientable[] complementos, boolean imprimir) {
		boolean retorno = false;
		for (Ingredientable complemento : complementos) {
			addComplemento(complemento, imprimir);
		}
		return retorno;
	}

	public boolean addComplementos(Ingredientable[] complementos) {
		return addComplementos(complementos, true);
	}

	public static Complemento[] ImportarExtras() {
		Complemento[] complemento = new Complemento[Extra.EXTRAS.length];
		for (int i = 0; i < Extra.EXTRAS.length; i++) {
			complemento[i] = new Complemento(Extra.EXTRAS[i].getDescription(), (double) Extra.EXTRAS[i].getPrice());
		}

		return complemento;
	}

	@Override
	public String toString() {
		String textito;

		textito = "Copa";
		if (getIngredienteBase() != null) {
			textito += " de helado de " + getIngredienteBase().getSabor().toLowerCase();
		} else {
			textito += " sin bola de helado";
		}

		// Repasar complementos para añadir descripcion.
		if (numeroComplementos > 0) {
			textito += " con";
			for (int i = 0; i < numeroComplementos; i++) {
				if (numeroComplementos == 1) {
					textito += " " + getComplementosCopa()[i].getDescripcion().toLowerCase();
				} else if (i == numeroComplementos - 1) {
					textito += "\b y " + getComplementosCopa()[i].getDescripcion().toLowerCase(); // La b borra la coma.
				} else {
					textito += " " + getComplementosCopa()[i].getDescripcion().toLowerCase() + ",";
				}
			}
			textito += ".";
		} else {
			textito += ".";
		}
		return textito;
	}

	public String stringPecio() {
		String textito;
		DecimalFormat euros = new DecimalFormat("0.00");

		textito = euros.format(getPrecio()) + "€";
		return textito;
	}

	@Override
	public int compareTo(CopaHelado o) {
		int resultado = 0;

		if (getPrecio() < o.getPrecio()) {
			resultado = 1;
		} else if (getPrecio() == o.getPrecio()) {
			resultado = 0;
		} else {
			resultado = -1;
		}

		return resultado;
	}

}
