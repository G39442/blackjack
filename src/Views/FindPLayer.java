//Error reading included file Templates/Classes/Templates/Licenses/license-default.txt
package Views;


import DataBase.*;
import java.time.*;
import java.util.*;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.scene.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.stage.*;



public class FindPLayer {
    
    javafx.stage.Stage window;
    javafx.scene.layout.StackPane root;
    javafx.scene.Scene scene;
    TableView<DataBase.recBlackJack> table;
    TextField field_name;
    DatePicker minDate;
    DatePicker maxDate;
    
    public FindPLayer() {
        
        window= new Stage();
        root = new StackPane();
        minDate = new DatePicker();
        maxDate = new DatePicker();
        HBox b1= new HBox();
        
        HBox b3= new HBox();
        Label label_name = new Label("Début nom du joueur ");
        field_name =new TextField();
        b1.getChildren().addAll(label_name,field_name);
        
        HBox b2= new HBox();
        Label label_max =new Label("Maximum ");
        label_max.setPrefWidth(50);
        TextField field_max =new TextField();
        
        Label label_min = new Label("Minimum ");
        TextField field_min =new TextField();
        
        b2.getChildren().addAll(label_min,minDate,label_max,maxDate);
        b2.setAlignment(javafx.geometry.Pos.BOTTOM_LEFT);
        
        String cssDefault = "-fx-border-color: black;\n"
                + "-fx-border-insets: 5;\n"
                + "-fx-border-width: 1;\n";
        
        b2.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
        b2.setStyle(cssDefault);
        b2.setSpacing(10);
        
        VBox v = new VBox();
        Button search = new javafx.scene.control.Button("search");
        search.setOnAction(e -> search());
        b3.getChildren().add(search);
        b3.setAlignment(Pos.BOTTOM_RIGHT);
        b3.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
        v.getChildren().addAll(b1,b2);
        v.setStyle(cssDefault);
        v.setPadding(new javafx.geometry.Insets(5, 5, 5, 5));
        
        
        
        TableColumn<recBlackJack, String>  name = new TableColumn<>("Name");
        name.setMinWidth(100);
        name.setCellValueFactory(new PropertyValueFactory<>("leNom"));
        
        
        TableColumn<recBlackJack, Date> date = new TableColumn<>("Date Mise A Jour");
        date.setMinWidth(120);
        date.setCellValueFactory(new PropertyValueFactory<>("laDate"));
        
        
        TableColumn<recBlackJack, Integer> nParties = new TableColumn<>("Nombre Parties");
        nParties.setMinWidth(100);
        nParties.setCellValueFactory(new PropertyValueFactory<>("nParties"));
        
        
        TableColumn<recBlackJack, Integer> sGains = new TableColumn<>("Somme Gains");
        sGains.setMinWidth(100);
        sGains.setCellValueFactory(new PropertyValueFactory<>("sGains"));
        
        table = new TableView<>();
        
        table.getColumns().addAll(name,date,nParties,sGains );
        
        VBox vBox = new VBox();
        vBox.getChildren().addAll(table);
        
        vBox.setStyle(cssDefault);
        VBox vbox_Boxes = new VBox();
        vbox_Boxes.getChildren().addAll(v,b3,vBox);
        Scene scene = new Scene(vbox_Boxes);
        vbox_Boxes.setStyle("-fx-background-color : black");
        scene.getStylesheets().add("style/BlackJackCSS.css");
        
        window.setScene(scene);
        window.setResizable(true);
        window.show();
    }
    
    public ObservableList<recBlackJack> getDataPlayer(String name,LocalDate minD,LocalDate maxD){
        ObservableList<recBlackJack> datePlayer = FXCollections.observableArrayList();
        List dataP = DataBase.recBlackJackDb.getPlayer(name,minD,maxD);
        for (int i = 0; i < dataP.size() ; i++) {
            recBlackJack r = (recBlackJack) dataP.get(i);
            datePlayer.add(r);
        }
        return datePlayer;
    }
    
    private void search(){
        if(maxDate.getValue()==null||minDate.getValue()==null){
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setContentText("les champs de la date ne devrait pas être vide");
            alert.showAndWait();
        }else{
            LocalDate maxD = this.maxDate.getValue();
            LocalDate minD = this.minDate.getValue();
            table.setItems(getDataPlayer(field_name.getText(),minD,maxD));
        }
        
    }
    
}
