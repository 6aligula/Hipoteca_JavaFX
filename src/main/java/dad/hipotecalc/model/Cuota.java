package dad.hipotecalc.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Cuota {

	private IntegerProperty numero = new SimpleIntegerProperty();
	private IntegerProperty anyo = new SimpleIntegerProperty();
	private IntegerProperty mes = new SimpleIntegerProperty();
	private DoubleProperty capitalInicial = new SimpleDoubleProperty();
	private DoubleProperty cuota = new SimpleDoubleProperty();
	private DoubleProperty intereses = new SimpleDoubleProperty();
	private DoubleProperty capitalAmortizado = new SimpleDoubleProperty();
	private DoubleProperty capitalPendiente = new SimpleDoubleProperty();

	public IntegerProperty anyoProperty() {
		return this.anyo;
	}

	public int getAnyo() {
		return this.anyoProperty().get();
	}

	public void setAnyo(final int anyo) {
		this.anyoProperty().set(anyo);
	}

	public IntegerProperty mesProperty() {
		return this.mes;
	}

	public int getMes() {
		return this.mesProperty().get();
	}

	public void setMes(final int mes) {
		this.mesProperty().set(mes);
	}

	public DoubleProperty capitalInicialProperty() {
		return this.capitalInicial;
	}

	public double getCapitalInicial() {
		return this.capitalInicialProperty().get();
	}

	public void setCapitalInicial(final double capitalInicial) {
		this.capitalInicialProperty().set(capitalInicial);
	}

	public DoubleProperty cuotaProperty() {
		return this.cuota;
	}

	public double getCuota() {
		return this.cuotaProperty().get();
	}

	public void setCuota(final double cuota) {
		this.cuotaProperty().set(cuota);
	}

	public DoubleProperty interesesProperty() {
		return this.intereses;
	}

	public double getIntereses() {
		return this.interesesProperty().get();
	}

	public void setIntereses(final double intereses) {
		this.interesesProperty().set(intereses);
	}

	public DoubleProperty capitalAmortizadoProperty() {
		return this.capitalAmortizado;
	}

	public double getCapitalAmortizado() {
		return this.capitalAmortizadoProperty().get();
	}

	public void setCapitalAmortizado(final double capitalAmortizado) {
		this.capitalAmortizadoProperty().set(capitalAmortizado);
	}

	public DoubleProperty capitalPendienteProperty() {
		return this.capitalPendiente;
	}

	public double getCapitalPendiente() {
		return this.capitalPendienteProperty().get();
	}

	public void setCapitalPendiente(final double capitalPendiente) {
		this.capitalPendienteProperty().set(capitalPendiente);
	}


	public final IntegerProperty numeroProperty() {
		return this.numero;
	}

	public final int getNumero() {
		return this.numeroProperty().get();
	}

	public final void setNumero(final int numero) {
		this.numeroProperty().set(numero);
	}

	@Override
	public String toString() {
		return 
				String.format("%6d", getNumero()) + "\t" + 
				String.format("%3d", getAnyo()) + "\t" + 
				String.format("%3d", getMes()) + "\t" + 
				String.format("%15.2f", getCapitalInicial()) + "\t" + 
				String.format("%13.2f", getCuota()) + "\t" + 
				String.format("%18.2f", getCapitalAmortizado()) + "\t" + 
				String.format("%9.2f", getIntereses()) + "\t" + 
				String.format("%17.2f", getCapitalPendiente());
	}

}
