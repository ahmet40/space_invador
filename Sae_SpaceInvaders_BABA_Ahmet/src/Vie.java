
/**
 * Permet de crée un class vie
 */
public class Vie {
    private int vie;
    /**
     * On crée la methode pour crée une vie
     * @param vie le nombre de vie que nous allons attriubé et qui est representer par un entier
     */
    public Vie(int vie){
        this.vie=vie;
    }

    /**
     * Permet de retourner le nombre de vie
     * @return un entier representant le nombre de vie
     */
    public int getVie() {
        return vie;
    }


    /**
     * permet de changer le nombre de vie
     * @param vie le nouveau nombre de vie
     */
    public void setVie(int vie) {
        this.vie = vie;
    }

    @Override
    /**
     * Permet de renvoyer un texte en indiquant le nombre de vie
     * @return Un string representant le text de la class vie
     */
    public String toString(){
        String coeur="❤️ ";
        String ch="";
        
        if (vie>0){for (int i=0;i<vie;++i){ch+=coeur;}}
        return "vie : "+ ch;
    }

}
