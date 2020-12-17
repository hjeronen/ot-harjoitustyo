
package tamagotchi.ui;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;

/**
 * PetCemeteryScene FXML Controller class.
 *
 * @author Heli
 */
public class PetCemeterySceneController implements Initializable {
    
    private GUI userinterface;
    
    @FXML
    private AnchorPane gameAnchor;
    
    @FXML
    private TableView table;
    
    @FXML
    private TableColumn id;
    
    @FXML
    private TableColumn name;
    
    @FXML
    private TableColumn age;
    
    public void setApplication(GUI ui) {
        this.userinterface = ui;
    }
    
    /**
     * Switches back to MainGameScene.
     * 
     * @throws Exception 
     */
    @FXML
    private void handleButtonActionBack() throws Exception {
        this.userinterface.setMainGameScene();
    }
    
    /**
     * Prepares TableView-element.
     * Fetches user's previous Pets from SQL-database and shows them in TableView.
     * Each TableColumn has individual callback-method to define the correct 
     * data to be shown on that cell.
     */
    public void setUpTable() {
        this.id.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                String[] x = p.getValue();
                if (x != null && x.length>0) {
                    return new SimpleStringProperty(x[0]);
                } else {
                    return new SimpleStringProperty("<no name>");
                }
            }
        });
        
        this.name.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                String[] x = p.getValue();
                if (x != null && x.length>1) {
                    return new SimpleStringProperty(x[1]);
                } else {
                    return new SimpleStringProperty("<no name>");
                }
            }
        });
        
        this.age.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<String[], String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<String[], String> p) {
                String[] x = p.getValue();
                if (x != null && x.length>2) {
                    return new SimpleStringProperty(x[2]);
                } else {
                    return new SimpleStringProperty("<no name>");
                }
            }
        });
        
        ArrayList<String> pets = this.userinterface.getPetCare().getPetCemetery().getAll();
        for (String pet : pets) {
            String[] parts = pet.split(";");
            String[][] data = {parts};

            this.table.getItems().addAll(data);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}

