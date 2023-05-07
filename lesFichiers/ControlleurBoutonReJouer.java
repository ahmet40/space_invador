import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.event.ActionEvent;

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
        this.appli.quitter();                           //une fois que le boutton est activé on lance le jeu.
        System.out.println("hehehehehee");
        //appli.lancerAnimation();
        //Button boutonJouer = (Button) event.getSource();        // on crée un boutton
        //Pane parent = (Pane) boutonJouer.getParent();           // on l'ajoute dans le pane
        //parent.getChildren().remove(boutonJouer);               // et on supprime le boutton. Cela va permettre de supprimer le boutton qui est sur la page.
//
    }
          
}