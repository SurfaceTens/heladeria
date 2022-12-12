package es.heladeria;

import java.util.ArrayList;
import java.util.Comparator;

import es.heladeria.helado.*;

public class App {

	public static void main(String[] args) {
		ArrayList<CopaHelado> copasUsadas = new ArrayList<>();
		ArrayList<Ingredientable> extrasAparte = new ArrayList<>();
		// Ejecutar pruebas, comentar al terminar.
//		Pruebas.mainPruebas();

//		Requerimientos Examen.
		CopaHelado heladoVainilla = new CopaHelado();
		System.out.println(heladoVainilla);
		System.out.println();

		// Añadir 4 complementos.
		heladoVainilla.addComplemento(CopaHelado.COMPLEMENTOS_6[0]);
		heladoVainilla.addComplemento(CopaHelado.COMPLEMENTOS_6[0]);
		heladoVainilla.addComplemento(CopaHelado.COMPLEMENTOS_6[1]);
		heladoVainilla.addComplemento(CopaHelado.COMPLEMENTOS_6[1]);
		System.out.println(heladoVainilla);
		System.out.println();

		// No permitir añadir complementos como base.
		CopaHelado heladoGalleta = new CopaHelado(CopaHelado.COMPLEMENTOS_6[1]);
		System.out.println("Helado de galleta -> " + heladoGalleta);
		System.out.println();

		// Hacer helado de nata.
		IngredienteBase nata = new IngredienteBase("Nata");
		IngredienteBase chocolate = new IngredienteBase("Chocolate");
		final Ingredientable[] CHOCOLATE_Y_CARAMEL = { chocolate, CopaHelado.COMPLEMENTOS_EXTRA[1] };

		CopaHelado heladoNata = new CopaHelado(nata, CHOCOLATE_Y_CARAMEL, 2);
		heladoVainilla.addComplemento(CopaHelado.COMPLEMENTOS_6[0], false);
		heladoVainilla.addComplemento(CopaHelado.COMPLEMENTOS_6[0], false);
		heladoVainilla.addComplemento(CopaHelado.COMPLEMENTOS_6[1], false);
		heladoVainilla.addComplemento(CopaHelado.COMPLEMENTOS_6[1], false);
		System.out.println(heladoNata);
		System.out.println();

		// Ordenar lista con natural.
		copasUsadas.add(heladoVainilla);
		copasUsadas.add(heladoGalleta);
		copasUsadas.add(heladoNata);
		copasUsadas.sort(null);
		copasUsadas.forEach(elemento -> System.out.println(elemento.toString() + "\t" + elemento.stringPecio()));
		System.out.println();

		// Ordenar lista con comparador ejercicio 11.
		Comparator<CopaHelado> ordenComplementos = new Comparator<CopaHelado>() {

			@Override
			public int compare(CopaHelado o1, CopaHelado o2) {
				return (int) (o2.getNumeroComplementos() - o1.getNumeroComplementos());
			}
		};

		CopaHelado heladoGalleta2 = new CopaHelado(CopaHelado.COMPLEMENTOS_6[0]);
		// Añadido un helado que tenga el mismo numero de complementos que otro.
		heladoGalleta2.addComplemento(CopaHelado.COMPLEMENTOS_EXTRA[5], false);
		heladoGalleta2.addComplemento(CopaHelado.COMPLEMENTOS_EXTRA[3], false);
		copasUsadas.add(heladoGalleta2);

		copasUsadas.sort(null); // Al ordenar de manera natural antes, se conserva el orden por precio cuando
								// tienen los mismos complementos.
		copasUsadas.sort(ordenComplementos);
		copasUsadas.forEach(elemento -> System.out.println(elemento.toString() + "\t" + elemento.stringPecio() + ", "
				+ elemento.getNumeroComplementos() + " complementos."));
		System.out.println();
		
		// Imprimir cuenta con extras fuera de helado.
		extrasAparte.add(chocolate);
		extrasAparte.add(CopaHelado.COMPLEMENTOS_EXTRA[2]);
		ImprimirCuenta(copasUsadas,extrasAparte);
	}

	public static void ImprimirCuenta(ArrayList<CopaHelado> cuenta, ArrayList<Ingredientable> extras) {
		double total = 0.0;
		for (CopaHelado copa : cuenta) {
			total += copa.getPrecio();
		}
		
		System.out.println("--- Resumen de cuenta ---");
		cuenta.forEach(elemento -> System.out.println(elemento.toString() + "\b: " + elemento.stringPecio()));
		if (extras != null) {
			extras.forEach(elemento -> System.out.println(elemento.toString() + ": " + elemento.stringPecio()));
		}
		
		System.out.println("Total " + cuenta.size() + " productos: " + total + "€");
	}
	
	public static void ImprimirCuenta(ArrayList<CopaHelado> cuenta) {
		ImprimirCuenta(cuenta, null);
		
	}
}
