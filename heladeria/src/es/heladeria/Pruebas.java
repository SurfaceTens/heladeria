package es.heladeria;

import java.util.ArrayList;
import java.util.Comparator;

import es.heladeria.helado.*;

public class Pruebas {
	final static Ingredientable[] CHARRATA = { new Complemento("Fuego", 0.5), new IngredienteBase("Resina", 3.2),
			new Complemento("Generador", 50.0) };


	public static void mainPruebas() {
		ArrayList<CopaHelado> escaparate = new ArrayList<>();
		IngredienteBase madera = new IngredienteBase("Madera", 4.65);
		double precio = 0.0;

		CopaHelado heladito = new CopaHelado();
		heladito.addComplemento(new IngredienteBase("Pistacho", 2.5), false);
		heladito.addComplementos(CopaHelado.COMPLEMENTOS_6, false);

		CopaHelado heladoBurro = new CopaHelado(madera, CHARRATA, 3);
		CopaHelado furesa = new CopaHelado(new IngredienteBase("Furesa", 2.8));
		furesa.addComplementos(CopaHelado.COMPLEMENTOS_EXTRA);

		escaparate.add(heladoBurro);
		escaparate.add(heladito);
		escaparate.add(new CopaHelado(new IngredienteBase("Cocholate", 3)));
		escaparate.add(furesa);
		escaparate.add(new CopaHelado());
		escaparate.add(heladito);
		escaparate.add(new CopaHelado(new IngredienteBase("Cocholate", 3)));
		escaparate.add(heladoBurro);

		// Ordenar
		escaparate.sort(null); // Orden natural
		escaparate.sort(new Comparator<CopaHelado>() {

			@Override
			public int compare(CopaHelado o1, CopaHelado o2) {
				// TODO Auto-generated method stub
				return (int) (o2.getNumeroComplementos() - o1.getNumeroComplementos());
			}
		});

		for (CopaHelado copa : escaparate) {
			precio += copa.getPrecio();
		}

		escaparate.forEach(System.out::println);
		System.out.println("Valor escaparate:");
		escaparate.forEach(elemento -> System.out.print(elemento.getPrecio() + "€ + "));
		System.out.println("\b\b\b");
		System.out.println("Total: " + precio + "€");
		
		System.out.println();
		escaparate.forEach(elemento -> elemento.showPrecio());

	}
}
