package dad.hipotecalc.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class HipotecaController implements Initializable {
	

	
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
		// TODO Auto-generated method stub
		

	}
	
	public GridPane getView() {
		return view;
	}

}
