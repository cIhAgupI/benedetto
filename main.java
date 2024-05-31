public class Monstre {

    public static void main(String[] args) {

    }

    /*
     * Initialisation du monde avec comme choix 1 pour les arbres et 2 pour les
     * batteries. Le code considère également qu'une case inoccupée contient un 0.
     * Adapter les nombres en fonction des attendus
     */

    public static void initMonde(int[][] monde) {
        int arbres = 20;
        int batteries = 10;

        while (arbres > 0) {
            int ligne = (int) (Math.random() * monde.length);
            int colonne = (int) (Math.random() * monde[ligne].length);
            if (monde[ligne][colonne] == 0) {
                monde[ligne][colonne] = 1;
                arbres--;
            }
        }

        while (batteries > 0) {
            int ligne = (int) (Math.random() * monde.length);
            int colonne = (int) (Math.random() * monde[ligne].length);
            if (monde[ligne][colonne] == 0) {
                monde[ligne][colonne] = 2;
                batteries--;
            }
        }
    }

    /*
     * Initialisation des monstres avec comme choix 3 pour les vampires. Le code
     * considère également qu'une case inoccupée contient un 0.
     * Adapter les nombres en fonction des attendus
     */

    public static coid initMonstres(int[][] monstres, int[][] monde) {
        int vampires = 10;

        while (vampires > 0) {
            int ligne = (int) (Math.random() * monde.length);
            int colonne = (int) (Math.random() * monde[ligne].length);
            if (monde[ligne][colonne] == 0) {
                monde[ligne][colonne] = 3;
                vampires--;
                monstres[vampires][0] = ligne;
                monstres[vampires][1] = colonne;
            }
        }
    }

    
}
