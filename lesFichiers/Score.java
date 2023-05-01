/**
 * cette class permet d'avoir un score dans notre jeu
 */
public class Score {
    private int score;
    /**
     * methode qui intialise notre score à 0
     */
    public Score(){
        this.score=0;
    }
    /**
     * permet d'ajouter une valeur à notre score
     * @param val la valeur à ajouter
     */
    public void ajoute(int val){
        this.score+=val;
    }


    /**
     * permet de renvoyer le score 
     * @return un entier representant le score
     */
    public int getScore() {
        return score;
    }

    @Override
    /**
     * cette methode permet de changer la façon dont notre score va s'afficher
     */
    public String toString(){
        return "Score : "+score+"";
    }

}