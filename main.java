import java.util.Random;
import java.util.Scanner;

public class Monstre {

    public static void main(String[] args) {
        public static void main(String[] args) {
            int[][] monde = new int[30][100];
            int[][] monstres = new int[10][2];
            boolean triche = false;

            int ligne = 15;
            int col = 50;
            int puissance = 1;
    
            initMonde(monde);
            initMonstres(monstres, monde);
            
            Scanner scanner = new Scanner(System.in);
    
            while (true) {
                afficheMonde(monde, ligne, col, puissance, triche);
                System.out.println("Entrez une direction (2: bas, 4: gauche, 6: droite, 8: haut) ou 9 pour activer/désactiver triche : ");
                int direction = scanner.nextInt();
                if (direction == 9) {
                    triche = !triche;
                } else {
                    int nouvelleLigne = ligne;
                    int nouvelleCol = col;

                    switch (direction) {
                        case 2:
                            nouvelleLigne++;
                            break;
                        case 4:
                            nouvelleCol--;
                            break;
                        case 6:
                            nouvelleCol++;
                            break;
                        case 8:
                            nouvelleLigne--;
                            break;
                    }

                    if (nouvelleLigne >= 0 && nouvelleLigne < 30 && nouvelleCol >= 0 && nouvelleCol < 100 && monde[nouvelleLigne][nouvelleCol] != 1) {
                        ligne = nouvelleLigne;
                        col = nouvelleCol;
                        if (monde[ligne][col] == 3) {
                            puissance++;
                            monde[ligne][col] = 0;
                        }
                    }
                    deplaceMonstres(monde, monstres, ligne, col, puissance);
                }
                if (compteBatteries(monde, 0, 0, 29, 99) == 0) {
                    System.out.println("Vous avez gagné ! Tous les vampires ont disparu.");
                    break;
                }
            }
        }
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

    public static void afficheMonde(int[][] monde, int ligne, int col, int puissance, boolean triche) {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 100; j++) {
                if (triche || distanceAuCarre(ligne, col, i, j) <= puissance * puissance) {
                    if (i == ligne && j == col) {
                        System.out.print('k');
                    } else {
                        afficheCase(monde[i][j]);
                    }
                } else {
                    System.out.print(' ');
                }
            }
            System.out.println();
        }
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
            default:
                System.out.print('.');
        }
    }

    public static void deplaceMonstres(int[][] monde, int[][] monstres, int ligne, int col, int puissance) {
        for (int i = 0; i < monstres.length; i++) {
            int row = monstres[i][0];
            int colm = monstres[i][1];
            if (row == -1 && colm == -1)
                continue;

            if (distanceAuCarre(ligne, col, row, colm) <= puissance * puissance) {
                monde[row][colm] = 0;
                monstres[i][0] = -1;
                monstres[i][1] = -1;
            } else {
                int nouvelleLigne, nouvelleCol;
                do {
                    nouvelleLigne = row + (int) (Math.random() * 3) - 1;
                    nouvelleCol = colm + (int) (Math.random() * 3) - 1;
                } while (nouvelleLigne < 0 || nouvelleLigne >= 30 || nouvelleCol < 0 || nouvelleCol >= 100
                        || monde[nouvelleLigne][nouvelleCol] == 1);

                monde[row][colm] = 0;
                monde[nouvelleLigne][nouvelleCol] = 2;
                monstres[i][0] = nouvelleLigne;
                monstres[i][1] = nouvelleCol;
            }
        }
    }

    public static int distanceAuCarre(int a, int b, int c, int d) {
        return (a - c) * (a - c) + (b - d) * (b - d);
    }

    public static int compteBatteries(int[][] monde, int a, int b, int c, int d) {
        if (a > c || b > d) {
            return 0;
        }
        if (a == c && b == d) {
            return monde[a][b] == 3 ? 1 : 0;
        }

        int ligneMilieu = (a + c) / 2;
        int colMilieu = (b + d) / 2;

        return compteBatteries(monde, a, b, ligneMilieu, colMilieu) +
                compteBatteries(monde, a, colMilieu + 1, ligneMilieu, d) +
                compteBatteries(monde, ligneMilieu + 1, b, c, colMilieu) +
                compteBatteries(monde, ligneMilieu + 1, colMilieu + 1, c, d);
    }
}
