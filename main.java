public class Monstre {

    public static void main(String[] args) {

    }

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
                monde[ligne][colonne] = 3;
                batteries--;
            }
        }
    }

    public static coid initMonstres(int[][] monstres, int[][] monde) {
        int vampires = 10;

        while (vampires > 0) {
            int ligne = (int) (Math.random() * monde.length);
            int colonne = (int) (Math.random() * monde[ligne].length);
            if (monde[ligne][colonne] == 0) {
                monde[ligne][colonne] = 2;
                vampires--;
                monstres[vampires][0] = ligne;
                monstres[vampires][1] = colonne;
            }
        }
    }

    public static void afficheMonde(int ligne, int col, int puissance, boolean triche) {
        // A implémenter
    }

    public static void afficheCase(int numero) {
        switch (numero) {
            case 1:
                System.out.print('P');
                break;
            case 2:
                System.out.print('W');
                break;
            case 3:
                System.out.print('z');
                break;
        }
    }
