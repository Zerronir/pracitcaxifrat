import java.util.HashMap;
import java.util.Map;

public class Vigenere {
    final String alfabeto = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        String s = "YYY";
        String pass = "AAA";
        int dif = 0;
        int A = 65;
        StringBuilder res = new StringBuilder();
        if(s.length() > pass.length()){
            StringBuilder ss = new StringBuilder(match(s, pass));

            for (int i = 0; i < s.length(); i++) {
                for (int j = 0; j < ss.length(); j++) {

                    dif = s.charAt(i) - ss.charAt(i) + 1;
                    System.out.println("dif -> " + dif + " res: " + (char) (s.charAt(i) + dif));

                }
            }

        } else {
            for (int i = 0; i < s.length(); i++) {
                dif = s.charAt(i) - pass.charAt(i) + 1 - A;
                char c = (char) (s.charAt(i) + dif);
                res.append(c);
            }
            System.out.println(res.toString());
        }


    }

    static String pruebas (String s, String pass){
        s = s.toUpperCase();
        pass = pass.toUpperCase();
        int a = 65;
        StringBuilder res = new StringBuilder();

        if(s.length() > pass.length()){
            String newPass = match(s, pass);

            for (int i = 0; i < s.length(); i++) {

                char c = s.charAt(i);

                if (c >= 65 && c <= 90) {
                    int letra = (c - a) + 1;

                    if(c > 90){
                         letra = (c - 26 - a) + 1;
                        res.append((char) (letra + 1));
                    }

                    res.append((char) (letra + 1));

                }

            }

        } else {
            for (int i = 0; i < s.length(); i++) {
                res.append((char) (pass.charAt(i) + 1));
            }
        }

        System.out.println(res.toString());

        return res.toString();

    }

    // Igualamos la longitud de la password añadiendo la misma palabra varias veces
    static String match(String s, String ss){
        StringBuilder res = new StringBuilder();
        int cont = ss.length();
        while(s.length() > cont){
            res.append(ss);
            cont++;
        }

        return res.toString();
    }

    static String encode(String s, String password) {
        password = password.toUpperCase();
        s = s.toUpperCase();
        StringBuilder res = new StringBuilder();
        StringBuilder key = new StringBuilder(newPass(password,s));

        // Libramos la frase de acentos y simbolos raros
        StringBuilder clean = new StringBuilder(clean(s));

        // Adaptamos la password para los espacios de las frases
        StringBuilder finalPass = new StringBuilder(clean(key.toString()));

        for (int i = 0; i < clean.length(); i++) {
            // Calculamos el desplazamiento de la frase
            int delta = finalPass.charAt(i) - 64;
            // Creamos un caracter para saber en cual estamos y limpiar código
            char c = clean.charAt(i);

            if (c >= 65 && c <= 90){
                c = (char) (delta + c);

                if(c > 90) {
                    c = (char) (c - 26);
                }

                res.append(c);
            } else {
                res.append(c);
            }

        }

        return res.toString();
    }

    static String decode(String s, String password) {
        password = password.toUpperCase();
        s = s.toUpperCase();
        StringBuilder res = new StringBuilder();
        StringBuilder key = new StringBuilder(newPass(password,s));

        // Libramos la frase de acentos y simbolos raros
        StringBuilder clean = new StringBuilder(clean(s));

        for (int i = 0; i < clean.length(); i++) {
            // Calculamos el desplazamiento de la frase
            int delta = key.charAt(i) - 64;
            // Creamos un caracter para saber en cual estamos y limpiar código
            char c = clean.charAt(i);

            if (c >= 65 && c <= 90){
                c = (char) (c - delta);

                if(c < 65) {
                    c = (char) (c + 26);
                }

                res.append(c);
            } else {
                res.append(c);
            }

        }

        return res.toString();
    }

    // Escapamos los caracteres especiales con acentos
    static char escapeChar(char c){
        char res = 0;

        switch (c){
            case 'Á': case 'À':
                res = 'A';
                break;
            case 'È': case 'É':
                res = 'E';
                break;
            case 'Ú' : case 'Ù':
                res = 'U';
                break;
            case 'Í': case 'Ì':
                res = 'I';
                break;
            case 'Ó': case 'Ò':
                res = 'O';
                break;
            default:
                res = c;
                break;
        }

        return res;
    }

    // Limpiamos la frase de caracteres extraños
    static String clean(String s){
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {

            if(s.charAt(i) >= 65 && s.charAt(i) <= 90) {
                res.append(s.charAt(i));
            } else {
                res.append(escapeChar(s.charAt(i)));
            }

        }

        return res.toString();
    }


    // Adaptamos la password que tenemos a la palabra que queremos cifrar
    static String newPass(String pass, String s){
        StringBuilder res = new StringBuilder();

        for (int i = 0, j = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            // Comprobamos que sean valores ascii aceptados en una frase, es decir, que no haya caracteres raros
            if (c >= 65 && c <= 90 || c >= 192 && c <= 220) {

                // Si la longitud de j llega a igualar a la de la contraseña original reiniciamos el contador
                if (j == pass.length()) j=0;

                // Buscamos si el caracter que está en i nos puede dar algún problema y lo escapamos
                if(pass.charAt(j) == 181 || pass.charAt(j) == pass.charAt(j)
                   || pass.charAt(j) == 144 || pass.charAt(j) == 212
                   || pass.charAt(j) == 224 || pass.charAt(j) == 214
                   || pass.charAt(j) == 222 || pass.charAt(j) == 227
                   || pass.charAt(j) == 233 || pass.charAt(j) == 235) {
                    res.append(escapeChar(pass.charAt(j)));

                } else {
                    // Si no es un caracter especial también lo añadimos a la nueva password
                    res.append(pass.charAt(j));
                }

                // Incrementamos el contador de j
                j++;

            } else {
                res.append(' ');
            }
        }

        return res.toString();
    }

}


