
public class PlayFair {

    public static String decrypt(String text, String pass) {
        StringBuilder res = new StringBuilder();
        String[][]matriz=newMatrix(pass);

        //Hasta este punto lo que tenemos hecho es la matriz para cifrar rellenada.
        StringBuilder newText = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i)!=' '){
                newText.append(text.charAt(i));
            }
        }

        for (int i = 0,j=1,x=0; x< newText.length()/2; x++, i++, j++) {
            int[] filcol = saberFilCol(matriz,text.charAt(i));
            int[] filcol1 = saberFilCol(matriz,text.charAt(j));

            if (filcol[0] == filcol1[0]){
                //misma fila
                //Primera letra
                if (filcol[1] == 0){
                    res.append(matriz[filcol[0]][4]);
                }else {
                    res.append(matriz[filcol[0]][filcol[1]-1]);
                }
                //Segunda letra
                if (filcol1[1] == 0){
                    res.append(matriz[filcol1[0]][4]);
                }else {
                    res.append(matriz[filcol1[0]][filcol1[1]-1]);
                }


            }else if (filcol[1] == filcol1[1]){
                //misma columna

                //Primera letra
                if (filcol[0]  == 0){
                    res.append(matriz[4][filcol[1]]);
                }else {
                    res.append(matriz[filcol[0]-1][filcol[1]]);
                }

                //Segunda letra
                if (filcol1[0]  == 0){
                    res.append(matriz[4][filcol1[1]]);
                }else {
                    res.append(matriz[filcol1[0]-1][filcol1[1]]);
                }


            }else {
                //No coincide ninguna
                res.append(matriz[filcol[0]][filcol1[1]]);
                res.append(matriz[filcol1[0]][filcol[1]]);

            }

            i+=2;
            j+=2;

            res.append(" ");
        }
        res.deleteCharAt(res.length()-1);

        char c1 = res.charAt(res.length()-2);
        char c2 = res.charAt(res.length()-1);
        int pos1 = res.length()-1;
        int pos2 =res.length();

        if (c1 == c2){
            res.replace(pos1,pos2,"X");
        }

        return res.toString();
    }

    public static String encrypt(String text, String pass) {
        // Creamos una variable de una StringBuilder para almacenar el resultado del cifrado
        StringBuilder res = new StringBuilder();
        String[][] matrix = newMatrix(pass);
        StringBuilder cleanString = new StringBuilder();
        cleanString.append(clean(text.toUpperCase()));

        int longi = cleanString.length(), desplazamiento = 0;
        int contador=0;
        int [] posiciones = new int[cleanString.length()];
        for (int i = 1; i < longi; i+=2) {

            char caracter1 = cleanString.charAt(i-1);
            char caracter2 = cleanString.charAt(i);

            if (caracter1 == caracter2){
                cleanString.replace(i,i,"X");
                i=1;
            }
        }

        for (int i = 0; i <contador ; i++) {
            cleanString.replace(posiciones[i]+desplazamiento,posiciones[i]+desplazamiento,"X");
            //desplazamiento++;
        }

        // Comprobamos que el valor de la longitud de la array
        // modulo 2 no sea 0 y luego comprobamos que si el caracter
        // es X añadiremos una S y sino añadiremos una X
        if  (cleanString.length() % 2 != 0){
            if (cleanString.charAt(cleanString.length()-1) == 'X'){
                cleanString.append("S");
            }else {
                cleanString.append("X");
            }
        }

        for (int i = 1; i < cleanString.length(); i+=2) {

            int[] filcol = saberFilCol(matrix, cleanString.charAt(i-1));

            int[] filcol1 = saberFilCol(matrix,cleanString.charAt(i));

            if  (filcol[0] == filcol1[0]){
                //Si tienen misma fila

                if (filcol[1] >= 4){
                    res.append(matrix[filcol[0]][0]);
                }else {
                    res.append(matrix[filcol[0]][filcol[1]+1]);
                }

                if (filcol1[1]>=4){
                    res.append(matrix[filcol1[0]]    [0]);
                }else {
                    res.append(matrix[filcol1[0]]    [filcol1[1]+1]);
                }



            }else if (filcol[1] == filcol1[1]){
                //Si tienen misma columna
                //Se baja una fila pero no cambia la columna

                if (filcol[0] >= 4){
                    res.append(matrix[0][filcol[1]]);
                }else {
                    res.append(matrix[filcol[0]+1]    [filcol[1]]);
                }

                if (filcol1[0] >= 4){
                    res.append(matrix[0][filcol1[1]]);
                }else {
                    res.append(matrix[filcol1[0]+1]   [filcol1[1]]);
                }


            }else {
                //Si no tienen misma fila ni misma columna

                res.append(matrix[filcol[0]][filcol1[1]]);
                res.append(matrix[filcol1[0]][filcol[1]]);

            }

            res.append(" ");
        }

        res.deleteCharAt(res.length()-1);

        return res.toString();
    }

    private static int[] saberFilCol(String[][] matrix, char c) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                StringBuilder res = new StringBuilder();
                res.append(c);
                // Si la nueva string es igual a la matriz en posicion i y j acabamos el bucle
                if (res.toString().equals(matrix[i][j])) return new int[]{i,j};
            }
        }


        return null;
    }

    private static String clean(String s) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == 'J') {

                res.append('I');

            } else if (s.charAt(i) >= 65 && s.charAt(i) <= 90) {

                res.append(s.charAt(i));

            } else {
                res.append(escapeChar(s.charAt(i)));
            }
        }
        return res.toString();
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

    private static String[][] newMatrix(String pass){
        pass = pass.toUpperCase();
        String[] lletres = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W","X", "Y", "Z"};
        // Creamos dos Strings vacías en las que almacenaremos nuevas Strings que devolveremos a las funciones clave
        StringBuilder newPass = new StringBuilder();
        StringBuilder cleanPass = new StringBuilder();

        // Limpiamos la password de caracteres especiales
        cleanPass.append(clean(pass));

        // Quitamos los caracteres repetidos de la password
        for (int i = 0; i < cleanPass.length(); i++) {
            StringBuilder c = new StringBuilder();
            c.append(cleanPass.charAt(i));

            if(newPass.toString().contains(c.toString())) continue;
            else newPass.append(cleanPass.charAt(i));
        }

        // Cambiamos los caracteres a mayúsculas
        StringBuilder passUP = new StringBuilder(newPass.toString());

        String[][] matrix = new String[5][5];
        int startRows = passUP.length() / 5;
        double x = (double) passUP.length() / 5;

        if(x > startRows) startRows++;

        int cont = 0;
        for (int i = 0; i < startRows; i++) {
            for (int j = 0; j < 5; j++) {
                if(cont < passUP.length()) {
                    StringBuilder c = new StringBuilder();
                    c.append(passUP.charAt(cont));
                    matrix[i][j] = c.toString();
                    cont++;
                }
            }
        }

        cont = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(i < 0) i++;
                if(matrix[i][j] == null) {
                    if(passUP.toString().contains(lletres[cont])){
                        cont++;
                        i--;
                        j--;
                        continue;
                    } else {
                        matrix[i][j] = lletres[cont];
                    }
                    cont++;
                }
            }
        }

        return matrix;
    }

}
