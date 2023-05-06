import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.control.Button;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.application.Platform;
import javax.swing.*;
import javafx.scene.layout.HBox;

/**
 * creation de la class executable qui va lancer notre application.
 */
public class Executable extends Application {
    private Pane root;
    private Group caracteres;
    private GestionJeu gestionnaire;
    private int hauteurTexte;
    private int largeurCaractere;
    private VBox vbox;
    private Button rejouer;
    private HBox hbox;
    

    /**
     * permet de lancer l'application
     * @param args un tableau de chaine de caracteres
     */
    public static void main(String[] args) {
        launch(args);
    }


    /**
     * permet de quitter l'application
     */
    //public void quitte(){
    //    Platform.exit();
    //}

    /**
     * permet de placer dans notre page les differents chainePositionne qu l'on a crée avec les class Projectile, Vaisseau, aliens
     */
    private void afficherCaracteres(){
        caracteres.getChildren().clear();
        int hauteur = (int) root.getHeight();
        
        for(ChainePositionnee c : gestionnaire.getChaines().chaines)
        {
            if (c.isRouge){                                 // si le boolean de la chaine est à true

                    String texteEnRouge = c.getC();             // on prend la chaine
                    Text t = new Text (c.x*largeurCaractere,hauteur - c.y*hauteurTexte, texteEnRouge);      // on met la chaine dans le texte 
                    t.setFill(Color.PURPLE);                                                                   // on change la couleur 
                    t.setFont(Font.font ("Monospaced", 10));
                    caracteres.getChildren().add(t);

            }
            else if (c.getIsWhite()){

                String texteEnRouge = c.getC();             // on prend la chaine
                Text t = new Text (c.x*largeurCaractere,hauteur - c.y*hauteurTexte, texteEnRouge);      // on met la chaine dans le texte 
                t.setFill(Color.WHITE);                                                                   // on change la couleur 
                t.setFont(Font.font ("Monospaced", 10));
                caracteres.getChildren().add(t);
            }
            else if (c.getIsGreen()){

                String texteEnRouge = c.getC();             // on prend la chaine
                Text t = new Text (c.x*largeurCaractere,hauteur - c.y*hauteurTexte, texteEnRouge);      // on met la chaine dans le texte 
                t.setFill(Color.BLUE);                                                                   // on change la couleur 
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


    /**
     * permet de lancer l'animation du jeu avec la methode gestionnaire.jouerUnTours et afficher Caracteres
     */
    public void lancerAnimation() {
        //this.root.getChildren().clear();
        if (root.getChildren().contains(vbox)){this.root.getChildren().remove(this.vbox);}    // va supprimer la vbox qui est dans le root.
        if (root.getChildren().contains(hbox)){root.getChildren().remove(hbox);}
        this.hbox=fin();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                    new EventHandler<ActionEvent>() {
                        @Override public void handle(ActionEvent actionEvent) {
                            if (gestionnaire.gagner()){
                                JFrame j=new JFrame();      // permet de crée un pop-up
                                JOptionPane.showMessageDialog(j,"Vous venez de gagnez, vous avez tué tous les aliens votre score est : "+ gestionnaire.getScore().getScore());
                                // permet de mettre un message au-dessus du boutton OK dans le pop-up
                                System.exit(0);  //permet de quitter si le boutton pop-up est activé (si on appuie dessus)
                            }                            
                            if (gestionnaire.perdu()){
                                if (!root.getChildren().contains(hbox)){root.getChildren().add(hbox);}
                                
                                System.out.println(root.getChildren().toString());
                               // root.getChildren().remove(hbox);
                                //JFrame j=new JFrame();      // permet de crée un pop-up
                                //if (gestionnaire.getLesAliens().size()==0){
                                //    JOptionPane.showMessageDialog(j,"Vous venez de perdre, vous avez tué : " +(10-gestionnaire.getLesAliens().size())+" alien, "+ "votre score est : "+ gestionnaire.getScore().getScore());
                                //    // permet de mettre un message au-dessus du boutton OK dans le pop-up
                                //}
                                //else{
                                //    JOptionPane.showMessageDialog(j,"Vous venez de perdre, vous avez tué : " +(10-gestionnaire.getLesAliens().size())+" aliens,"+ " votre score est : "+ gestionnaire.getScore().getScore());
                                //    // permet de mettre un message au-dessus du boutton OK dans le pop-up
                                //}
                                //
                                //System.exit(0);
                                //Timeline parentTimeline =new Timeline();
                                //if (parentTimeline != null) {
                                //    //root.getChildren().clear();
                                //     
                                //    //parentTimeline.jumpTo(Duration.seconds(2));
                                //    root.getChildren().add(fin());
                                //    System.out.println(root.getChildren().toString());
                                //    //return;
//
                                //}
                                
                                
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

    /**
     * Permet de crée une premiere page avec un titre, une couleur noir et un boutton
     */
        public void start(Stage primaryStage) {
            primaryStage.setTitle("IUTO Space Invader");
            caracteres = new Group();
            root= new AnchorPane(caracteres);
            gestionnaire = new GestionJeu();
            
            this.vbox = new VBox();               // on initialise notre VBox
            vbox.setSpacing(10);
            vbox.setPadding(new Insets((gestionnaire.getHauteur()*10)/2,(gestionnaire.getLargeur()*10)/2-5,(gestionnaire.getHauteur()*10)/2,250));
            Button buttonJouer = new Button("Jouer");       // on crée un boutton
            buttonJouer.setOnAction(new ControlleurBoutonJouer(this));      // on met un controlleur au boutton
            vbox.getChildren().add(buttonJouer);
            root.getChildren().add(vbox);               // on met dans le root (AnchorPane) la vbox


            root.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
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
        public HBox fin(){
        
           HBox hbButtons = new HBox(3);               // on crée une hBox
           hbButtons.setPadding(new Insets((gestionnaire.getHauteur()*10)/2,(gestionnaire.getLargeur()*10)/2-5,(gestionnaire.getHauteur()*10)/2,250));
           this.rejouer= new Button("reJouer");       // on crée un boutton
           this.rejouer.setOnAction(new ControlleurBoutonReJouer(null));
           hbButtons.getChildren().addAll(this.rejouer);            // on ajoute ce boutton a la hbox
           hbButtons.setAlignment(Pos.CENTER);                     // on place la hbox au centre de la fenetre
           return hbButtons;
        
        }
    }
