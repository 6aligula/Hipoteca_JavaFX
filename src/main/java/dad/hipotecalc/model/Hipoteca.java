package dad.hipotecalc.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleExpression;
import javafx.beans.binding.IntegerExpression;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.ReadOnlyDoubleWrapper;
import javafx.beans.property.ReadOnlyListProperty;
import javafx.beans.property.ReadOnlyListWrapper;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

@XmlRootElement
@XmlType
public class Hipoteca {

	private StringProperty cliente = new SimpleStringProperty();
	private ObjectProperty<LocalDate> fecha = new SimpleObjectProperty<>();
	private DoubleProperty capital = new SimpleDoubleProperty(0.0);
	private DoubleProperty intereses = new SimpleDoubleProperty(0.0);
	private IntegerProperty plazo = new SimpleIntegerProperty(1);
	private ReadOnlyDoubleWrapper cuotaMensual = new ReadOnlyDoubleWrapper();
	private ReadOnlyDoubleWrapper interesMensual = new ReadOnlyDoubleWrapper();
	private ReadOnlyListWrapper<Cuota> cuotas = new ReadOnlyListWrapper<Cuota>(FXCollections.observableArrayList());

	public Hipoteca() {

		interesMensual.bind((intereses.divide(100)).divide(12)); // interesMensual = (intereses / 100) / 12

		// plazos = plazo * 12
		IntegerExpression plazos = plazo.multiply(12);

		// elevado = (interesMensual + 1)^-plazos
		DoubleExpression elevado = PowBinding.pow(interesMensual.add(1.0), plazos.negate());

		// cuotaMensual = (capital * interesMensual) / (1 - elevado)
		cuotaMensual.bind((capital.multiply(interesMensual)).divide(Bindings.subtract(1.0, elevado)));

	}

	public void calcularCuotas() {
		calcularCuotas(null);
	}

	public void calcularCuotas(Integer anyo) {
		if (anyo != null && (anyo < 1 || anyo > getPlazo())) {
			throw new IllegalArgumentException("El año debe estar entre 1 y " + getPlazo() + " y a puesto " + anyo);
		}

		List<Cuota> todas = new ArrayList<Cuota>();
		Double cuotaMensual = getCuotaMensual();
		Double capitalInicial = getCapital();
		for (int i = 1; i <= getPlazo(); i++) {
			for (int mes = 1; mes <= 12; mes++) {
				Cuota cuota = new Cuota();
				cuota.setNumero(((i - 1) * 12) + mes);
				cuota.setAnyo(i);
				cuota.setMes(mes);
				cuota.setCapitalInicial(capitalInicial);
				cuota.setCuota(cuotaMensual);
				cuota.setIntereses(capitalInicial * getInteresMensual());
				cuota.setCapitalAmortizado(cuotaMensual - cuota.getIntereses());
				cuota.setCapitalPendiente(capitalInicial - cuota.getCapitalAmortizado());
				capitalInicial = capitalInicial - cuota.getCapitalAmortizado();
				if (capitalInicial < cuotaMensual) {
					cuota.setCuota(cuotaMensual + capitalInicial);
				}
				todas.add(cuota);
			}
		}

		List<Cuota> desglose = todas.stream().filter(c -> (anyo != null) ? (c.getAnyo() == anyo) : true).collect(Collectors.toList());

		cuotas.setAll(desglose);
	}

	public void limpiar() {
		setCliente("");
		setFecha(null);
		setCapital(0);
		setIntereses(0);
		setPlazo(0);
		this.cuotas.clear();
	}

	public final DoubleProperty capitalProperty() {
		return this.capital;
	}

	@XmlAttribute
	public final double getCapital() {
		return this.capitalProperty().get();
	}

	public final void setCapital(final double capital) {
		this.capitalProperty().set(capital);
	}

	public final DoubleProperty interesesProperty() {
		return this.intereses;
	}

	@XmlAttribute
	public final double getIntereses() {
		return this.interesesProperty().get();
	}

	public final void setIntereses(final double intereses) {
		this.interesesProperty().set(intereses);
	}

	public final IntegerProperty plazoProperty() {
		return this.plazo;
	}

	@XmlAttribute
	public final int getPlazo() {
		return this.plazoProperty().get();
	}

	public final void setPlazo(final int plazo) {
		this.plazoProperty().set(plazo);
	}

	public final ReadOnlyDoubleProperty cuotaMensualProperty() {
		return this.cuotaMensual.getReadOnlyProperty();
	}

	public final double getCuotaMensual() {
		return this.cuotaMensualProperty().get();
	}

	public final ReadOnlyDoubleProperty interesMensualProperty() {
		return this.interesMensual.getReadOnlyProperty();
	}

	public final double getInteresMensual() {
		return this.interesMensualProperty().get();
	}

	public final ReadOnlyListProperty<Cuota> cuotasProperty() {
		return this.cuotas.getReadOnlyProperty();
	}

	public final ObservableList<Cuota> getCuotas() {
		return this.cuotasProperty().get();
	}

	public final StringProperty clienteProperty() {
		return this.cliente;
	}

	public final String getCliente() {
		return this.clienteProperty().get();
	}

	public final void setCliente(final String cliente) {
		this.clienteProperty().set(cliente);
	}

	public final ObjectProperty<LocalDate> fechaProperty() {
		return this.fecha;
	}

	public final LocalDate getFecha() {
		return this.fechaProperty().get();
	}

	public final void setFecha(final LocalDate fecha) {
		this.fechaProperty().set(fecha);
	}

}
