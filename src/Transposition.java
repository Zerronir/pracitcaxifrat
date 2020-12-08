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
        // Creamos una String para almacenar el resultado
        StringBuilder res = new StringBuilder();

        // Asignamos el tamaño de la matriz en filas y columnas
        int cols = key.length(), filas = s.length() / cols;

        // Redondeamos el número de filas
        double ver = (double) s.length() / cols;
        if(ver > filas) filas++;
        // Creamos una array donde tendremos la clave y la contraseña
        String[][] matrix = new String[filas][cols];

        // Introducimos el valor de la frase que queremos cifrar
        // dentro de la array
        int x = 0;
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < cols; j++) {
                if(x < s.length()) {
                    StringBuilder c = new StringBuilder();
                    c.append(s.charAt(x));
                    matrix[i][j] = c.toString();
                    c.delete(0, c.length()-1);
                    x++;
                }
            }
        }

        int[] letras = new int[key.length()];
        StringBuilder orderedKey = new StringBuilder();

        // Llenamos la array con los caracteres de la clave
        for (int i = 0; i < letras.length; i++) {
            letras[i] = key.charAt(i);
        }

        int[] orderedChar = orderArray(letras);
        char c;
        // Iteramos el array asignando cada valor char dentro de la string
        for (int value : orderedChar) {
            c = (char) value;
            // Devolvemos la array ordenada
            orderedKey.append(c);
        }

        StringBuilder clave = new StringBuilder();
        clave.append(key);

        for (int i = 0; i < orderedKey.length(); i++) {
            int pos = clave.toString().indexOf(orderedKey.charAt(i));

            clave.replace(pos, pos+1, "+");
            for (int j = 0; j < matrix.length; j++) {
                if(matrix[j][pos] != null) res.append(matrix[j][pos]);
            }

        }

        return res.toString();
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
                //break;
            }
        }


        for (int i = 0; i < matrix[0].length; i++) {
            for (int j = 0; j < matrix.length; j++) {

                if(matrix[j][i] == '*') {continue;}
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
        StringBuilder res = new StringBuilder();

        int cols = key.length(), filas = s.length() / cols;
        double x = (double) s.length() / cols;

        if (x > filas) filas++;

        int numNulls = (filas*cols) - s.length();
        String [][] matrix = new String[filas][cols];

        if(numNulls > 0) {
            int p = cols-1;
            for (int i = numNulls; i > 0; i--) {
                matrix[filas-1][p] = "*";
                p--;
            }
        }

        int [] letras = new int[key.length()];
        StringBuilder orderedKey = new StringBuilder();

        for (int i = 0; i < letras.length; i++) {
            letras[i] = key.charAt(i);
        }

        // Ordenamos la clave
        int[] orderedChars = orderArray(letras);
        char c;
        for (int i = 0; i < orderedChars.length; i++) {
            c = (char) orderedChars[i];
            orderedKey.append(c);
        }

        String[][] mat1 = new String[filas][cols];
        StringBuilder key1 = new StringBuilder(key.toString());

        int[] index = new int[orderedKey.length()];
        for (int i = 0; i < orderedKey.length(); i++) {
            int pos = key1.toString().indexOf(orderedKey.charAt(i));

            key1.replace(pos, pos+1, "%");
            mat1[filas-1][i] = matrix[filas-1][pos];
            index[i] = pos;

        }
        // Eliminamos el contenido de la clave temporal
        key1.delete(0, key1.length());
        // Volvemos a rellenar la key temporal con el valor de la clave
        key1.append(key);

        int cont = 0;
        for (int i = 0; i < cols; i++) {
            for (int j = 0; j < filas; j++) {
                if(cont < s.length()){
                    if(mat1[j][i] != "*"){
                        StringBuilder c1 = new StringBuilder();
                        c1.append(s.charAt(cont));
                        mat1[j][i] = c1.toString();
                        c1.delete(0, c1.length()-1);
                    } else {
                        continue;
                    }
                    cont++;
                }
            }
        }

        StringBuilder dash = new StringBuilder();

        for (int i = 0; i < orderedKey.length(); i++) {
            dash.append("-");
        }

        String[][] mat2 = new String[filas][cols];

        for (int i = 0; i < index.length; i++) {
            int pos = index[i];

            for (int j = 0; j < filas; j++) {
                mat2[j][pos] = mat1[j][i];
            }

        }


        for (int i = 0; i < mat2.length; i++) {
            for (int j = 0; j < mat2[0].length; j++) {
                if(!mat2[i][j].equals("*")) res.append(mat2[i][j]);
            }
        }

        return res.toString();
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

    private static int[] orderArray(int[] array) {

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
