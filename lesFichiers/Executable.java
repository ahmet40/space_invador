import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
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
import javafx.scene.image.Image;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.BackgroundImage;
import javax.swing.*;


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
    private Stage primaryStage;
    private boolean rejoue=false;
    private int jouer=-1;

    

    /**
     * permet de lancer l'application
     * @param args un tableau de chaine de caracteres
     */
    public static void main(String[] args) {
        launch(args);
    }

    public Stage getStage(){
        return this.primaryStage;
    }
    public void quitter(){
        Platform.exit();
    }

    /**
     * permet de placer dans notre page les differents chainePositionne qu l'on a crée avec les class Projectile, Vaisseau, aliens
     */
    private void afficherCaracteres(){
        caracteres.getChildren().clear();
        int hauteur = (int) root.getHeight();
        
        for(ChainePositionnee c : gestionnaire.getChaines().chaines){
            String texteEnRouge = c.getC();                                                         // on prend la chaine
            Text t = new Text (c.x*largeurCaractere,hauteur - c.y*hauteurTexte, texteEnRouge);      // on met la chaine dans le texte 
            if (c.isRouge){                                                                         // si le boolean de la chaine est à true
                t.setFill(Color.DARKRED);                                                            // on change la couleur
            }
            else if (c.getIsWhite()){t.setFill(Color.WHITE);}
            else if (c.getIsGreen()){t.setFill(Color.LIGHTGREEN);}
            else if (c.getIsBlue()){t.setFill(Color.CYAN);}
            t.setFont(Font.font ("Monospaced", 10));
            caracteres.getChildren().add(t);
            
        }
    }


    /**
     * permet de lancer l'animation du jeu avec la methode gestionnaire.jouerUnTours et afficher Caracteres
     */
    public void lancerAnimation() {
        //this.root.getChildren().clear();
        if (root.getChildren().contains(vbox)){this.root.getChildren().remove(this.vbox);}    // va supprimer la vbox qui est dans le root.
        this.caracteres = new Group();
        this.root.getChildren().addAll(caracteres);
        System.out.println(this.root.getChildren());
        //this.hbox=fin();
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(0),
                    new EventHandler<ActionEvent>() {
                        @Override public void handle(ActionEvent actionEvent) {
                            if (gestionnaire.gagner()){
                                jouer=1;
                                String nv="Vous venez de gagnez, vous avez tué tous les aliens votre score est : "+gestionnaire.getScore().getScore();

                                primaryStage.setScene(fin(primaryStage, nv));
                            }                            
                            if (gestionnaire.perdu()){
                                jouer=1;
                                String nv="Vous venez de perdre, vous avez tué : " +(28-gestionnaire.getLesAliens().size())+" alien, "+ "votre score est : "+ gestionnaire.getScore().getScore();

                                primaryStage.setScene(fin(primaryStage, nv));

                            }
                            else{
                                gestionnaire.jouerUnTour();
                                afficherCaracteres();
                                
                            }
                        }
                }),
                new KeyFrame(Duration.seconds(0.025))
        );
        timeline.setCycleCount(this.jouer);
        
        timeline.play();
       // if (!root.getChildren().contains(caracteres)){root.getChildren().add(caracteres);}
        
    }

    public Scene Acceuil(){
            this.root= new AnchorPane();
            this.gestionnaire = new GestionJeu();
            // Créer un titre centré
            Text title = new Text("SPACE INVADERS");
            title.setFill(Color.LIGHTGREEN);
            title.setFont(Font.font("Arial", 40));
            VBox.setMargin(title, new javafx.geometry.Insets(50, 0, 50, 0)); //ajoute une marge de 50 pixels en haut et en bas
            VBox.setVgrow(title, javafx.scene.layout.Priority.ALWAYS);

            // Créer un bouton centré en dessous du titre
            Button button = new Button("Jouer");
            button.setOnAction(new ControlleurBoutonJouer(this));
            VBox.setMargin(button, new javafx.geometry.Insets(0, 0, 50, 0)); // ajoute une marge de 50 pixels en bas
            VBox.setVgrow(button, javafx.scene.layout.Priority.ALWAYS);

            // Ajouter le titre et le bouton à un VBox (vertical box) pour les aligner verticalement
            this.vbox = new VBox();
            this.vbox.setAlignment(Pos.CENTER);
            this.vbox.getChildren().addAll(title, button);
            this.vbox.setPadding(new Insets((gestionnaire.getHauteur()*10)/2-70,(gestionnaire.getLargeur()*10)/2+30,(gestionnaire.getHauteur()*10)/2-70,((gestionnaire.getLargeur()*10)/2)/2-120));

            root.getChildren().add(vbox);
            Image img=new Image("Space.png",gestionnaire.getLargeur()+300,gestionnaire.getHauteur()+300,true,true);         // on crée l'image que l'on veut mettre
            root.setBackground(new Background(new BackgroundImage(img,BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
                // on place l'image en tant qu'arrière plan dans notre page.
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
            return scene;
    }

    @Override
    /**
     * Permet de crée une premiere page avec un titre, une couleur noir et un boutton
     */
        public void start(Stage primaryStage) {
            this.primaryStage=new Stage();
            this.primaryStage.setTitle("IUTO Space Invader");
                this.primaryStage.setScene(Acceuil());
                this.primaryStage.setResizable(false);
                if (!rejoue){this.primaryStage.show();}
                else{
                    root=new AnchorPane();
                    this.primaryStage.setScene(Acceuil());
                    this.primaryStage.setResizable(false);
                    this.primaryStage.show();
                    
                }
        }


        /**
         * Cette methode permet de cree une nouvelle scene sur la même page. 
         * @param stage     Le stage que nous utilisons depuis le debut.
         * @param ch        La chaine de caractere su l'on va affichet en tant que texte.
         */
        public Scene fin(Stage stage,String ch){
            this.root= new AnchorPane();
            this.gestionnaire = new GestionJeu();
            Text title = new Text(ch);
            title.setFill(Color.LIGHTGREEN);
            title.setFont(Font.font("Arial", 15));
            VBox.setMargin(title, new javafx.geometry.Insets(50, 0, 50, 0)); //ajoute une marge de 50 pixels en haut et en bas
            VBox.setVgrow(title, javafx.scene.layout.Priority.ALWAYS);

            Button button1 = new Button("Quitter");                         // creation d'un boutton qui va permettre de quiter l'application
            button1.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e){
                    quitter();
                    System.out.println("on vien de sortir");
                }
            });
            Button button2=new Button("Rejouer");                           // creation d'un bouton qui va permettre de rejouer une partie
            button2.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e){
                    System.out.println("on va rejouer");
                    primaryStage.setScene(Acceuil());
                }
            });
            
            VBox.setMargin(button1, new javafx.geometry.Insets(0, 0, 50, 0)); // ajoute une marge de 50 pixels en bas
            VBox.setVgrow(button1, javafx.scene.layout.Priority.ALWAYS);
            VBox.setMargin(button2, new javafx.geometry.Insets(0, 0, 50, 0)); // ajoute une marge de 50 pixels en bas
            VBox.setVgrow(button2, javafx.scene.layout.Priority.ALWAYS);
            
            // Ajouter le bouton à un VBox (vertical box) pour les aligner verticalement
            this.vbox = new VBox();
            this.vbox.setAlignment(Pos.CENTER);
            this.vbox.getChildren().addAll(title,button1,button2);
            this.vbox.setPadding(new Insets((gestionnaire.getHauteur()*10)/2-70,(gestionnaire.getLargeur()*10)/2+30,(gestionnaire.getHauteur()*10)/2-70,((gestionnaire.getLargeur()*10)/2)/2-150));
            root.getChildren().add(vbox);

            Image img=new Image("Space.png",gestionnaire.getLargeur()+300,gestionnaire.getHauteur()+300,true,true);         // on crée l'image que l'on veut mettre
            root.setBackground(new Background(new BackgroundImage(img,BackgroundRepeat.REPEAT,BackgroundRepeat.REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT)));
                // on place l'image en tant qu'arrière plan dans notre page.

            Text t=new Text("█");
            t.setFont(Font.font("Monospaced",10));
            hauteurTexte =(int) t.getLayoutBounds().getHeight();
            largeurCaractere = (int) t.getLayoutBounds().getWidth();
            Scene scene = new Scene(root,gestionnaire.getLargeur()*largeurCaractere,gestionnaire.getHauteur()*hauteurTexte);
            return scene;
        }
    }
