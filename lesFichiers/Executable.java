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

import javax.security.auth.callback.TextInputCallback;

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
import javax.swing.*;


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
            if (c.isRouge){                                 // si le boolean de la chaine est à true

                    String texteEnRouge = c.getC();             // on prend la chaine
                    Text t = new Text (c.x*largeurCaractere,hauteur - c.y*hauteurTexte, texteEnRouge);      // on met la chaine dans le texte 
                    t.setFill(Color.RED);                                                                   // on change la couleur 
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
                                JFrame j=new JFrame();
                                JOptionPane.showMessageDialog(j,"Vous venez de gagnez, vous avez tué tous les aliens");
                                System.exit(0);
                            }
                            if (gestionnaire.perdu()){
                                JFrame j=new JFrame();
                                if (gestionnaire.getLesAliens().size()==0){
                                    JOptionPane.showMessageDialog(j,"Vous venez de perdre, vous avez tué : " +(10-gestionnaire.getLesAliens().size())+" alien");
                                }
                                else{
                                    JOptionPane.showMessageDialog(j,"Vous venez de perdre, vous avez tué : " +(10-gestionnaire.getLesAliens().size())+" aliens");
                                }
                                System.exit(0);
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
            HBox hbButtons = new HBox(3);               // on crée une hBox
            hbButtons.setPadding(new Insets((gestionnaire.getHauteur()*10)/2,(gestionnaire.getLargeur()*10)/2-5,(gestionnaire.getHauteur()*10)/2,250));

            Button buttonJouer = new Button("Jouer");       // on crée un boutton
            
            buttonJouer.setOnAction(new ControlleurBoutonJouer(this));      // on met un controlleur au boutton
            hbButtons.getChildren().addAll(buttonJouer);            // on ajoute ce boutton a la hbox
            hbButtons.setAlignment(Pos.CENTER);                     // on place la hbox au centre de la fenetre
            root.getChildren().add(hbButtons);                      // on met dans le root (AnchorPane) la hBox

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
        }
}
