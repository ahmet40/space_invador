/**
 * class permetant de positionner une chaine de charactere
 */
public class ChainePositionnee{
    int x,y;
    String c;
    Boolean isRouge;
    Boolean isWhite;
    Boolean isGreen;
    Boolean isBlue;
    /**
     * permet de construire la chaine à potionner au coordonnee a,b
     * @param a un entier representant la position x
     * @param b un entier representant la position y
     * @param d une chaine de charactere representant la chaine
     */
    public ChainePositionnee(int a,int b, String d){x=a; y=b; c=d;isRouge=false;isWhite=false;isGreen=false;isBlue=false;}
    /**
     * permet de renvoyer le string 
     * @return la chaine de charactere
     */
    public String getC() {
        return c;
    }

    /**
     * permet de retourner la valeur du boolean IsRouge
     * @return le boolean
     */
    public Boolean getIsRouge() {
        return isRouge;
    }

    /**
     * Permet de changer la valeur du boolean isRouge
     * @param isRouge  la nouvelle valeur du boolean
     */
    public void setIsRouge(Boolean isRouge) {
        this.isRouge = isRouge;
    }

    /**
     * permet de retourner la valeur du boolean IsWhite
     * @return le boolean
     */
    public Boolean getIsWhite() {
        return isWhite;
    }

    /**
     * Permet de changer la valeur du boolean isWhite
     * @param isWhite  la nouvelle valeur du boolean
     */
    public void setIsWhite(Boolean isWhite) {
        this.isWhite = isWhite;
    }

    /**
     * permet de retourner la valeur du boolean IsGreen
     * @return le boolean
     */
    public Boolean getIsGreen() {
        return isGreen;
    }

    /**
     * Permet de changer la valeur du boolean isGreen
     * @param isGreen  la nouvelle valeur du boolean
     */
    public void setIsGreen(Boolean isGreen) {
        this.isGreen = isGreen;
    }

    /**
     * Permet de changer la valeur du boolean isGreen
     * @return le boolean
     */
    public Boolean getIsBlue() {
        return isBlue;
    }

        /**
     * Permet de changer la valeur du boolean isBlue
     * @param isBlue la nouvelle valeur du boolean
     */
    public void setIsBlue(Boolean isBlue) {
        this.isBlue = isBlue;
    }

}