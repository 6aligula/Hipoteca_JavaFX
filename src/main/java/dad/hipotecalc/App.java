package dad.hipotecalc;

import dad.hipotecalc.controller.CuotasController;
import dad.hipotecalc.controller.HipotecaController;
import dad.hipotecalc.controller.MainController;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends javafx.application.Application {
	
	private MainController mainController;
	private HipotecaController hipotecaController;
	private CuotasController cuotasController;

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		mainController = new MainController();
		
		primaryStage.setTitle("Hipotecalc");
		primaryStage.getIcons().add(new Image("/images/logo32x32.png"));
		primaryStage.setScene(new Scene(mainController.getView()));
		primaryStage.show();

	}

}
