import java.util.Arrays;

public class Transposition {

    public static void main(String[] args) {
        String s = "ABCDEFG";
        int col = 2;
        int filas = s.length() / col;
        StringBuilder r = new StringBuilder();
        double ver = (double) s.length() / col;

        if(ver > filas) filas++;

        String[][] matrix = newMatrix(col, filas, s);
        String[][] res = new String[matrix[0].length][matrix.length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j <matrix[0].length ; j++) {
                matrix[j][i] = res[j][i];
            }
        }

        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[0].length; j++) {
                if(res[i][j] != null){
                    r.append(res[i][j]);
                }
            }
        }
        
        
        System.out.println(r.toString());

    }

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
        String[][] mat2 = new String[matrix[0].length][matrix.length];

        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                mat2[i][j] = matrix[j][i];
            }
        }

        for (int i = 0; i < mat2.length; i++) {
            for (int j = 0; j < mat2[0].length; j++) {

                if(mat2[i][j] != null) res.append(mat2[i][j]);

            }
        }
        return res.toString();
    }

    static String cypher(String s, String key) {
        return "";
    }

    static String decypher(String s, int dim) {
        // Creamos una string vacía donde guardaremos el resultado
        StringBuilder res = new StringBuilder();

        // Creamos contadores para los espacios
        int contA = 0, contB = 0;

        while((s.length() + contA) % dim != 0) contA++;

        // Redondeamos el resultado por si tiene decimales
        double decim = (double) s.length() / dim;
        int filas = s.length() / dim;

        if(decim > filas) filas++;

        // Creamos una matriz con los tamaños de filas y columnas que hemos obtenido
        char[][] matrix = new char[filas][dim];

        // Sustituimos los valores null por asteríscos
        if (contA > 0) {

            for (int i = matrix.length; i > 0; i--) {
                int innerCont = matrix[0].length - 1;

                for (int j = contA; j > innerCont ; j--) {
                    matrix[i - 1][innerCont] = '*';
                    innerCont--;
                }
                break;
            }
        }


        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {

                if(matrix[j][i] == '*') continue;
                matrix[j][i] = s.charAt(contB);
                contB++;

            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {

                if(matrix[i][j] == '*') continue;
                res.append(matrix[i][j]);

            }
        }


        return res.toString();
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


    private int[] orderArray(int[] array) {

        for (int i = array.length, x = 0; i > x; i--, x++) {
            for (int j = 1; j < i; j++) {
                if(array[j - 1] > array[j]){
                    int canvi1 = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = canvi1;
                }
            }

            for (int k = (i-1); k > x; k--) {
                if(array[k - 1] > array[k]) {
                    int canvi2 = array[k];
                    array[k] = array[k - 1];
                    array[k - 1] = canvi2;
                }
            }
        }

        return array;
    }

}
