import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.w3c.dom.Node;

public class ControlleurBoutonJouer implements EventHandler<ActionEvent>{ 

    private Executable appli;
    
    public ControlleurBoutonJouer(Executable appli){
        this.appli = appli;
    }

    @Override
    public void handle(ActionEvent event) {
        this.appli.lancerAnimation();
        appli.lancerAnimation();
        
        
        //Button boutonJouer = (Button) event.getSource(        Pane parent = (Pane) boutonJouer.getParent(        System.out.println(parent.getChildren()        parent.getChildren().remove(boutonJouer);

    }
          
}