import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.text.Font;

import java.util.ArrayList;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.application.Platform;


public class Executable extends Application {
    private Pane root;
    private Group caracteres;
    private GestionJeu gestionnaire;
    private int hauteurTexte;
    private int largeurCaractere;

    


    public static void main(String[] args) {
        launch(args);
    }

    public void quitte(){
        Platform.exit();
    }

    private void afficherCaracteres(){
        caracteres.getChildren().clear();
        int hauteur = (int) root.getHeight();
        
        for(ChainePositionnee c : gestionnaire.getChaines().chaines)
        {
            if (c.isRouge){

                    String texteEnRouge = c.getC();
                    Text t = new Text (c.x*largeurCaractere,hauteur - c.y*hauteurTexte, texteEnRouge);
                    t.setFill(Color.RED);
                    t.setFont(Font.font ("Monospaced", 10));
                    caracteres.getChildren().add(t);
    
            }
            else{
                Text t = new Text (c.x*largeurCaractere,hauteur - c.y*hauteurTexte, c.c);
                t.setFont(Font.font ("Monospaced", 10));
                caracteres.getChildren().add(t);
            }
            
        }
    }

    public void lancerPageDeFin() {
        Application.launch(PageDeFin.class);
    }

    public void lancerAnimation() {
        
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                    new EventHandler<ActionEvent>() {
                        @Override public void handle(ActionEvent actionEvent) {
                            if (gestionnaire.gagner()){
                                EnsembleChaines e= new EnsembleChaines();
                                e.ajouteChaine(gestionnaire.getLargeur(),gestionnaire.getHauteur(),"GAGNE");
                                for(ChainePositionnee c : e.chaines){
                                    Text t = new Text (c.x*largeurCaractere,gestionnaire.getHauteur() - c.y*hauteurTexte, c.c);
                                    t.setFont(Font.font ("Monospaced", 10));
                                    caracteres.getChildren().add(t);
                                }
                            }
                            if (gestionnaire.perdu()){
                                System.out.println("Vous avez perdu");                   
                                quitte();
                                //Stage primaryStage = new Stage();
                                //final Button button1 = new Button("Salut tout le monde !"); 
                                //button1.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE); 
                                //final Button button2 = new Button("Hello !"); 
                                //final Pane root = new Pane(); 
                                //root.getChildren().addAll(button1, button2); 
                                //final Scene scene = new Scene(root, 350, 300); 
                                //primaryStage.setTitle("Test de StackPane"); 
                                //primaryStage.setScene(scene); 
                                //primaryStage.show(); 
                  //
                                
                            }
                            else{
                                
                                gestionnaire.jouerUnTour();
                                afficherCaracteres();
                                
                            }
                        }
                }),
                new KeyFrame(Duration.seconds(0.025))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    @Override
        public void start(Stage primaryStage) {
            System.out.println("je suis la");
            primaryStage.setTitle("IUTO Space Invader");
            caracteres = new Group();
            root= new AnchorPane(caracteres);
            gestionnaire = new GestionJeu();
            HBox hbButtons = new HBox(3);
            hbButtons.setPadding(new Insets((gestionnaire.getHauteur()*10)/2,(gestionnaire.getLargeur()*10)/2-5,(gestionnaire.getHauteur()*10)/2,250));

            Button buttonJouer = new Button("Jouer");
            
            buttonJouer.setOnAction(new ControlleurBoutonJouer(this));
            hbButtons.getChildren().addAll(buttonJouer);
            hbButtons.setAlignment(Pos.CENTER);
            root.getChildren().add(hbButtons);

            Text t=new Text("█");
            t.setFont(Font.font("Monospaced",10));
            hauteurTexte =(int) t.getLayoutBounds().getHeight();
            largeurCaractere = (int) t.getLayoutBounds().getWidth();

            Scene scene = new Scene(root,gestionnaire.getLargeur()*largeurCaractere,gestionnaire.getHauteur()*hauteurTexte);
            scene.addEventHandler(KeyEvent.KEY_PRESSED, (key) -> {
                if(key.getCode()==KeyCode.LEFT)
                    gestionnaire.toucheGauche();
                if(key.getCode()==KeyCode.RIGHT)
                    gestionnaire.toucheDroite();
                if(key.getCode()==KeyCode.SPACE)
                    gestionnaire.toucheEspace();
            });
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            
            primaryStage.show();
            //lancerAnimation();
        }
}
