package dad.hipotecalc.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import dad.hipotecalc.model.Cuota;
import dad.hipotecalc.model.Hipoteca;
import dad.hipotecalc.service.HipotecaService;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

public class MainController implements Initializable {
	private HipotecaService hipotecaService;

	public void setHipotecaService(HipotecaService hipotecaService) {
        this.hipotecaService = hipotecaService;
        hipotecaController.setHipotecaService(hipotecaService); // Propaga la instancia al otro controlador
    }

	// controllers

	private HipotecaController hipotecaController = new HipotecaController();
	private CuotasController cuotasController = new CuotasController();

	// view

	@FXML
	private Tab cuotasTab;

	@FXML
	private Tab hipotecaTab;

	@FXML
	private Button calcularButton;

	@FXML
	private CheckBox cuotasCheckbox;

	@FXML
	private TextField cuotasText;

	@FXML
	private Button exportarButton;

	@FXML
	private Button limpiarButton;

	@FXML
	private BorderPane view;
	/*
	 * Alternativamente, puedes establecer el controlador en tu código antes de
	 * cargar el archivo FXML.
	 * Esto se hace típicamente en el constructor o en algún método de
	 * inicialización en tu aplicación
	 * JavaFX, como se muestra en tu código de MainController:
	 * Aquí, setController(this) establece la instancia actual de MainController
	 * como el controlador
	 * para MainView.fxml. Esto es útil cuando quieres instanciar el controlador tú
	 * mismo o cuando
	 * necesitas pasar argumentos al controlador.
	 */

	public MainController() {

		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainView.fxml"));
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	// El FXMLLoader busca un método onCalcularAction en la clase del controlador
	// que haya sido marcado
	// con @FXML y que tenga un parámetro que sea compatible con el tipo de evento
	// disparado
	// (en este caso, ActionEvent). Cuando encuentra una coincidencia, vincula ese
	// método al evento
	// onAction del botón especificado en el FXML.
	@FXML
	void onCalcularAction(ActionEvent event) {
		hipotecaController.getDataUser();
		
		hipotecaService.getHipoteca().calcularCuotas();
		// Obtener la lista de cuotas y pasársela al CuotasController
		ObservableList<Cuota> cuotas = FXCollections.observableArrayList(hipotecaService.getHipoteca().getCuotas());
		cuotasController.setCuotas(cuotas);
		System.out.println(
				"Número" + "\t" + "Año" + "\t" + "Mes" + "\t" + "Capital inicial" + "\t" +
						"Cuota mensual" + "\t" + "Capital amortizado" + "\t" +
						"Intereses" + "\t" + "Capital pendiente");
		for (Cuota cuota : hipotecaService.getHipoteca().getCuotas()) {
			System.out.println(cuota);
		}

	}

	@FXML
	void onCuotasAction(ActionEvent event) {

	}

	@FXML
	void onExportarAction(ActionEvent event) {

	}

	@FXML
	void onLimpiarAction(ActionEvent event) {

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		hipotecaTab.setContent(hipotecaController.getView());
		cuotasTab.setContent(cuotasController.getView());

	}

	public BorderPane getView() {
		return view;
	}

}
