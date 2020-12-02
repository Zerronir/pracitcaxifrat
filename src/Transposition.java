import javax.swing.*;
import java.util.Arrays;

public class Transposition {

    static String cypher(String s, int dim) {
        StringBuilder res = new StringBuilder();

        // Definim l'ordre de les columnes i les files
        int col = dim;
        int fil = s.length() / col;
        // Verificam les fileres i ajustam el tamany per a que cuadrin
        double ver = (double) s.length() / col;

        if(ver > fil) fil++;

        // Cream una matriu a on filtrarem per al resultat
        String[][] matrix = newMatrix(col, fil, s);

        return res.toString();
    }

    static String cypher(String s, String key) {
        return "";
    }

    static String decypher(String s, int dim) {
        return "";
    }

    static String decypher(String s, String key) {
        return "";
    }


    static String[][] newMatrix(int dim, int filas, String s){
        String[][] res = new String[filas][dim];
        // Iniciam un comptador
        int comptador = 0;

        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < dim; j++) {
                // Si el comptador es igual que la llargaria de la pass continuam
                if(comptador == s.length())continue;

                // Posicionam les lletres dintre de la array
                StringBuilder c = new StringBuilder();
                c.append(s.charAt(comptador));
                // Afegim el caracter a l'array
                res[i][j] = c.toString();
                // Eliminam l'excés de llargaria
                c.delete(0, c.length() - 1);
                comptador++;
            }
        }

        // Retornam la nova array
        return res;
    }


    private static char escapeChar(char c) {
        switch (c) {
            case 'À': case 'Á': case 'Ä':
                return 'A';
            case 'È': case 'É': case 'Ë':
                return 'E';
            case 'Ì': case 'Í': case 'Ï':
                return 'I';
            case 'Ò': case 'Ó': case 'Ö':
                return 'O';
            case 'Ù': case 'Ú': case 'Ü':
                return 'U';
            default: return c;
        }
    }
}
