import java.util.HashMap;
import java.util.Map;

public class Vigenere {

    static String encode(String s, String password) {
        password = password.toUpperCase();
        s = s.toUpperCase();
        StringBuilder key = new StringBuilder(newPass(password,s));

        // Libramos la frase de acentos y simbolos raros
        StringBuilder clean = new StringBuilder(clean(s));

        // Adaptamos la password para los espacios de las frases
        StringBuilder finalPass = new StringBuilder(clean(key.toString()));

        return cifrar(clean.toString(), finalPass.toString());
    }

    static String decode(String s, String password) {
        password = password.toUpperCase();
        s = s.toUpperCase();
        StringBuilder key = new StringBuilder(newPass(password,s));

        // Libramos la frase de acentos y simbolos raros
        StringBuilder clean = new StringBuilder(clean(s));

        return descifrar(clean.toString(), key.toString());
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
                if(
                      pass.charAt(j) == 181 /*Á*/ || pass.charAt(j) == 183 /*À*/
                   || pass.charAt(j) == 144 /*É*/ || pass.charAt(j) == 212 /*È*/
                   || pass.charAt(j) == 214 /*Í*/ || pass.charAt(j) == 224 /*Ì*/
                   || pass.charAt(j) == 222 /*Ó*/ || pass.charAt(j) == 227 /*Ò*/
                   || pass.charAt(j) == 233 /*Ú*/ || pass.charAt(j) == 235 /*Ù*/
                ) {
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

    // Ejecutamos el cifrado
    static String cifrar(String clean, String key){
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < clean.length(); i++) {
            // Calculamos el desplazamiento de la frase
            int delta = key.charAt(i) - 64;
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

    // Ejecutamos el descifrado
    static String descifrar(String clean, String key) {
        StringBuilder res = new StringBuilder();
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

}


