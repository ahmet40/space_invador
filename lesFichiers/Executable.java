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
import java.net.URL;
import javax.swing.Action;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.util.Duration;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.application.Platform;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import java.awt.Canvas;


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
        for( ChainePositionnee c : gestionnaire.getChaines().chaines)
        {
            Text t = new Text (c.x*largeurCaractere,hauteur - c.y*hauteurTexte, c.c);
            t.setFont(Font.font ("Monospaced", 10));
            caracteres.getChildren().add(t);
        }
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
                                root =new AnchorPane();
                                Text text=new Text("Vous venez de perdre");
                
                                text.setX((gestionnaire.getLargeur()/2)-10);
                                text.setY(gestionnaire.getHauteur()/2+10);
                                Stage primaryStage=new Stage();
                                primaryStage.setTitle("IUTO Space Invader");
                                root.getChildren().add(text);
                                //root.setStyle("-fx-background-image: url"("./captures/space_fin.png"));
                                Scene scene = new Scene(root,gestionnaire.getLargeur()*largeurCaractere,gestionnaire.getHauteur()*hauteurTexte);
                                primaryStage.setScene(scene);
                                primaryStage.setResizable(false);
                                primaryStage.show();
                                Image backgroundImage = new Image("./capture/space_fin.png");
                                root.setStyle("-fx-background-color: red");
                                // Créer un objet BackgroundSize avec la largeur et la hauteur de la scène
                                //BackgroundSize backgroundSize = new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false);
                        
                                // Créer un objet BackgroundImage avec l'image, sa répétition et sa position
                                //BackgroundImage backgroundImg = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
                        
                                // Créer un objet Background avec l'image en arrière-plan
                                //Background background = new Background(backgroundImg);
                        
                                // Créer un conteneur VBox avec une taille spécifique
                                //VBox root = new VBox();
                                //root.setPrefSize(600, 400);
                        
                                // Appliquer l'arrière-plan à la racine
                                //root.setBackground(background);
                                
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
