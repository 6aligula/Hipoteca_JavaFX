package dad.hipotecalc.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import dad.hipotecalc.model.Cuota;
import dad.hipotecalc.model.Hipoteca;
import dad.hipotecalc.service.HipotecaService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class HipotecaController implements Initializable {
	// servicio
	private HipotecaService hipotecaService;

	public void setHipotecaService(HipotecaService hipotecaService) {
        this.hipotecaService = hipotecaService;
    }

	@FXML
	private TextField capitalText;

	@FXML
	private TextField clienteText;

	@FXML
	private DatePicker fechaDatePicker;

	@FXML
	private TextField interesesText;

	@FXML
	private TextField plazoText;

	@FXML
	private GridPane view;

	public HipotecaController() {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/HipotecaView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	public void getDataUser() {
		try {
			// Asumiendo que los campos de texto podrían estar vacíos
			if (clienteText.getText().isEmpty() || capitalText.getText().isEmpty()
					|| interesesText.getText().isEmpty() || plazoText.getText().isEmpty()) {
				throw new NullPointerException("Todos los campos son requeridos");
			}

			String cliente = clienteText.getText();
			LocalDate fecha = fechaDatePicker.getValue();
			double capital = Double.parseDouble(capitalText.getText());
			double intereses = Double.parseDouble(interesesText.getText());
			int plazo = Integer.parseInt(plazoText.getText());

			if (capital <= 0 || intereses <= 0 || plazo <= 0) {
				throw new IllegalArgumentException("Capital, intereses y plazo deben ser mayores que cero");
			}
			System.out.println("Cliente: " + cliente);
			System.out.println("fecha: " + fecha);
			System.out.println("capital: " + capital);
			System.out.println("intereses: " + intereses);
			System.out.println("plazo: " + plazo);

			hipotecaService.getHipoteca().setCliente(cliente);
			hipotecaService.getHipoteca().setFecha(fecha);
			hipotecaService.getHipoteca().setCapital(capital);
			hipotecaService.getHipoteca().setIntereses(intereses);
			hipotecaService.getHipoteca().setPlazo(plazo);
			hipotecaService.getHipoteca().calcularCuotas();

		} catch (NumberFormatException e) {
			System.out.println("Error en el formato de los números ingresados");
		} catch (NullPointerException e) {
			System.out.println("Algunos campos requeridos están vacíos");
		}

	}

	public GridPane getView() {
		return view;
	}

}
