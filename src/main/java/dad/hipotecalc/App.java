package dad.hipotecalc;
import dad.hipotecalc.controller.MainController;
import dad.hipotecalc.service.HipotecaService;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends javafx.application.Application {
	
	private static HipotecaService hipotecaService = new HipotecaService();

	private MainController mainController;


	@Override
	public void start(Stage primaryStage) throws Exception {
		
		mainController = new MainController();
		// Inyecta la instancia de HipotecaService en MainController
		mainController.setHipotecaService(hipotecaService);
		
		primaryStage.setTitle("Hipotecalc");
		primaryStage.getIcons().add(new Image("/images/logo32x32.png"));
		primaryStage.setScene(new Scene(mainController.getView()));
		primaryStage.show();

	}

}
