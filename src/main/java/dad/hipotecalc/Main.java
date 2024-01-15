package dad.hipotecalc;

import java.time.LocalDate;

import dad.hipotecalc.model.Cuota;
import dad.hipotecalc.model.Hipoteca;

public class Main {

	public static void main(String[] args) {
		
		App.launch(App.class, args);
		
		/*
		// EJEMPLO DE CÁLCULO DE HIPOTECA POR UN CAPITAL DE 20.000€, A UN INTERÉS DEL 3.5% Y CON UNA DURACIÓN 5 AÑOS
		
		Hipoteca hipoteca = new Hipoteca();
		hipoteca.setCliente("Chuck Norris");	// nombre del cliente
		hipoteca.setFecha(LocalDate.now());		// fecha del cálculo
		hipoteca.setCapital(20000);				// importe del préstamo
		hipoteca.setIntereses(3.5);				// porcentaje de interés aplicado
		hipoteca.setPlazo(5);					// duración del préstamo en años  
		
		// TODAS LAS CUOTAS (5 AÑOS = 5 * 12 = 60 CUOTAS)
		hipoteca.calcularCuotas();
		System.out.println(
			"Número" + "\t" + "Año" + "\t" + "Mes" + "\t" + "Capital inicial" + "\t" + 
			"Cuota mensual" + "\t" + "Capital amortizado" + "\t" + 
			"Intereses" + "\t" + "Capital pendiente");
		for (Cuota cuota : hipoteca.getCuotas()) {
			System.out.println(cuota);
		}
				
		// SOLO LAS DEL 3ER AÑO (POR EJEMPLO)
		hipoteca.calcularCuotas(3);
		System.out.println(
			"Número" + "\t" + "Año" + "\t" + "Mes" + "\t" + "Capital inicial" + "\t" + 
			"Cuota mensual" + "\t" + "Capital amortizado" + "\t" + 
			"Intereses" + "\t" + "Capital pendiente");
		for (Cuota cuota : hipoteca.getCuotas()) {
			System.out.println(cuota);
		}*/

	}

}
