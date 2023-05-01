import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Button;


public class PageDeFin extends Application { 
  
    @Override 
    public void start(Stage primaryStage) { 
        primaryStage = new Stage();
        final Button button1 = new Button("Salut tout le monde !"); 
        button1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); 
        final Button button2 = new Button("Hello !"); 
        final Pane root = new Pane(); 
        root.getChildren().addAll(button1, button2); 
        final Scene scene = new Scene(root, 350, 300); 
        primaryStage.setTitle("Test de StackPane"); 
        primaryStage.setScene(scene); 
        primaryStage.show(); 
    } 
  
    public static void main(String[] args) { 
        launch(args); 
    } 
}