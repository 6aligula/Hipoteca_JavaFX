package dad.hipotecalc.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import dad.hipotecalc.model.Cuota;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import javafx.collections.ObservableList;
import javafx.scene.control.TableCell;

public class CuotasController implements Initializable {
  /*
   * Cuota: Define el tipo del objeto de modelo que esta columna estará mostrando,
      lo que significa que cada fila en esta columna representará una instancia de Cuota.

    *Number: Define el tipo del valor que cada celda en la columna contendrá. 
    Esto también determina qué tipo de valores esperar y cómo se manejarán. Por ejemplo, 
    si estás usando PropertyValueFactory, buscará un método getter que devuelva un Number.

    Al especificar los tipos, te beneficias de la verificación de tipo en tiempo de compilación
    y no necesitas hacer casting en tiempo de ejecución.
   */

  @FXML
  private TableView<Cuota> cuotasTable;
  
  @FXML
  private TableColumn<Cuota, Number> numeroColumn;
  
  @FXML
  private TableColumn<Cuota, Number> anoColumn;
  
  @FXML
  private TableColumn<Cuota, Number> mesColumn;
  
  @FXML
  private TableColumn<Cuota, Number> inicialColumn;
  
  @FXML
  private TableColumn<Cuota, Number> cuotaMesColumn;
  
  @FXML
  private TableColumn<Cuota, Number> amortizadoColumn;
  
  @FXML
  private TableColumn<Cuota, Number> interesesColumn;
  
  @FXML
  private TableColumn<Cuota, Number> pendienteColumn;

  /*HBox: Es el tipo del campo view, que es una clase en JavaFX que representa un contenedor horizontal.
   Un HBox coloca sus nodos hijos en una sola fila horizontalmente. 

   view: Es el nombre del campo. Este debe coincidir con el atributo fx:id que le has dado al 
   componente HBox en tu archivo FXML. Esto es lo que permite que el FXMLLoader inyecte la instancia 
   del HBox definido en FXML en tu controlador*/
  @FXML
  private HBox view;

  public CuotasController() {

    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/CuotasView.fxml"));
      loader.setController(this);
      loader.load();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

  }

  private void formatTableColumnToDecimal(TableColumn<Cuota, Number> column, int decimalPlaces) {
    column.setCellFactory(tc -> new TableCell<Cuota, Number>() {
      @Override
      protected void updateItem(Number item, boolean empty) {
        super.updateItem(item, empty);
        if (empty) {
          setText(null);
        } else {
          setText(String.format("%." + decimalPlaces + "f", item));
        }
      }
    });
  }


  @Override
  public void initialize(URL location, ResourceBundle resources) {
    // Aquí vinculamos las propiedades de la clase Cuota a las columnas de la tabla
    /*
     * Debes asegurarte de que los nombres de las propiedades utilizadas en `PropertyValueFactory` 
     * coincidan exactamente con los nombres de los métodos getter de las propiedades en tu clase 
     * `Cuota`. Por ejemplo, si tienes un método `getNumero()`, entonces debes usar 
     * `new PropertyValueFactory<>("numero")`.
      Para conectar esto con tu `MainController`, después de calcular las cuotas, debes pasar la lista 
      de cuotas al `CuotasController`. Aquí hay un ejemplo de cómo podrías hacer esto:
     */
    numeroColumn.setCellValueFactory(new PropertyValueFactory<>("numero"));
    anoColumn.setCellValueFactory(new PropertyValueFactory<>("anyo"));
    mesColumn.setCellValueFactory(new PropertyValueFactory<>("mes"));
    inicialColumn.setCellValueFactory(new PropertyValueFactory<>("capitalInicial"));
    cuotaMesColumn.setCellValueFactory(new PropertyValueFactory<>("cuota"));
    amortizadoColumn.setCellValueFactory(new PropertyValueFactory<>("capitalAmortizado"));
    interesesColumn.setCellValueFactory(new PropertyValueFactory<>("intereses"));
    pendienteColumn.setCellValueFactory(new PropertyValueFactory<>("capitalPendiente"));
    // primer metodo de formateo:
    // formatTableColumnToDecimal(amortizadoColumn, 2);
    // formatTableColumnToDecimal(interesesColumn, 2);
    
    //segundo metodo de formateo:
    // Lista de columnas a las que se aplicará el formateo
    List<TableColumn<Cuota, Number>> columnsToFormat = Arrays.asList(
      inicialColumn, cuotaMesColumn, amortizadoColumn, interesesColumn, pendienteColumn
    );

    // Aplicar el formateo a las columnas especificadas
    columnsToFormat.forEach(column -> formatTableColumnToDecimal(column, 2));

  }

  public HBox getView() {
    return view;
  }

  // Método para actualizar los datos de la tabla
  public void setCuotas(ObservableList<Cuota> cuotas) {
    cuotasTable.setItems(cuotas);
  }

}
