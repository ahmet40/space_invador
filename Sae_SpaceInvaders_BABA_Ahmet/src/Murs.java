
/**
 * permet d'initialiser la class Murs
 */
public class Murs {
    private double posX;
    private double posY;
    private Vie laVie;

    /**
     * Constructeur de la class murs
     * @param posX  la position x à laquelle on va le placer
     * @param posY la position y à laquelle on va le placer
     */
    public Murs(double posX,double posY){               // permet de crée l'instance du Murs du jeu
        this.posX=posX;     
        this.posY=posY;
        this.laVie=new Vie(7);                  
    }   
    /**
     * permet de renvoyer la position en X du Murs
     * @return un double la position du Murs
     */
    public double getPosX() {           
        return posX;                            // on retourne la position X du Murs
    }

    /**
     * Permet de retourner la position Y du murs.
     * @return un double representant cette postion.
     */
    public Double getPosY() {
        return posY;
    }



    /**
     * Permet de retourner l'attribut Vie du Murs
     * @return Vie la vie du Murs
     */
    public Vie getLaVie() {
        return laVie;
    }

    /**
     * Permet de baiser de 1 le nombre de vie du Murs
     */
    public void setLaVie() {
        this.laVie.setVie(this.getLaVie().getVie()-1);
    }



    /**
     * methode qui va representer notre Murs sous forme d'un ensemble de chaine
     * @return l'ensemble de chaine 
     */
    public EnsembleChaines getEnsembleChaines(){
        EnsembleChaines ch=new EnsembleChaines();                                   // crée un ensemble de chaine vide
        ch.ajouteChaine((int)posX,(int)posY+2,"█████████");                        
        ch.ajouteChaine((int)posX,(int)posY+1,String.format("███ %s ███",this.laVie.getVie()) );  
        ch.ajouteChaine((int)posX,(int)posY,"██     ██");                              // on va placer dans cette ensemble l'image de notre Murs
        
        for (ChainePositionnee c:ch.getChaines()){c.setIsWhite(true);}      
        // on parcours notre ensemble et pour chaque chaine on chan,ge la valeur de setIsWhite. Cela va nous permettre de mettre notre Murs en blanc.
        return ch;  
    }

    /**
     * permet de voir si la position X,Y est contenu dans notre Murs. Pour cela on va faire appel à notre methode getEnsembleChaine 
     * et pour cette ensemble on regarde si il contient la position X,Y.
     * @param x la position x representez par un entier
     * @param y la position y representez par un entier
     * @return  un boolean indiquant si la position X,Y et contenu dans le Murs
     */
    public boolean contient(int x,int y){
        return this.getEnsembleChaines().contient(x, y);                // on regarde si la position x,y et contenu dans le Murs.
    }
}