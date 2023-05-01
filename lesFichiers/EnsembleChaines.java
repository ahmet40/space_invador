import java.util.List;
import java.util.ArrayList;
import java.lang.Math;

/**
 * cette class permet d'avoir un ensemble de chaine.
 */
public class EnsembleChaines {
    ArrayList<ChainePositionnee> chaines;
    /**
     * cette methode va permettre de construire notre ensemble de chaine
     */
    public EnsembleChaines(){chaines= new ArrayList<ChainePositionnee>(); }

    /**
     * cette methode permet d'ajouter une chaine 
     * @param x position x de la chaine
     * @param y position y de la chaine
     * @param c la chaine
     */
    public void ajouteChaine(int x, int y, String c){
        chaines.add(new ChainePositionnee(x,y,c));}

    /**
     * permet de faire une union des chaine
     * @param e un ensemble de chaine
     */
    public void union(EnsembleChaines e){
        for(ChainePositionnee c : e.chaines)
            chaines.add(c);
    }

    /**
     * cette methode permet de verifier si l'objet qui à pour coordonee x,y
     * appartient à l'une des chaines dans l'ensemble
     * @param x un entier representant la position x de l'objet
     * @param y un entier representant la position y de l'objet 
     * @return un boolean 
     */
    public boolean contient(int x,int y){
        for (ChainePositionnee c:chaines){
            for (int i=0;i<c.getC().length();i++){
                if ((int)Math.round(c.x+i)==x && (int) (Math.round(c.y))==y){return true;}
            }
            //System.out.println((int)Math.round(c.y));
            //if ((int)Math.round(c.x)==x && (int) (Math.round(c.y))==y){return true;}
        }
        return false;
    }
}