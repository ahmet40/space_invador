import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Pemet de crée la class ControlleurBoutonJouer qui va attendre une action 
 */
public class ControlleurBoutonReJouer implements EventHandler<ActionEvent>{ 

    private Executable appli;
    
    /**
     * permet d'initialiser l'appli
     * @param appli l'appli à initialiser
     */
    public ControlleurBoutonReJouer(Executable appli){
        this.appli = appli;
    }

    @Override
    /**
     * permet de lancer le jeu et supprimer le bouton une fois que l'on va cliquer dessus
     */
    public void handle(ActionEvent event) {
        System.out.println("en train d'apuyer");
        this.appli.lancerAnimation() ;       //une fois que le boutton est activé on lance le jeu.

        Button rejouer = (Button) event.getSource();        // on crée un boutton
        Pane parent = (Pane) rejouer.getParent();           // on l'ajoute dans le pane
        parent.getChildren().remove(rejouer);               // et on supprime le boutton. Cela va permettre de supprimer le boutton qui est sur la page.

    }

          
}