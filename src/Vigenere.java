import java.util.HashMap;
import java.util.Map;

public class Vigenere {
    final String alfabeto = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        String s = "YYYYY";
        String pass = "AAA";
        int a = 65;

        char c = 'Z';

        int x = (c - a) + 1;
        char z = (char) (a + x);

        if(z > 90){
            System.out.println((char) (z - 26));
        }

        System.out.println(z);

        //System.out.println(pruebas(s, pass));

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
        s = s.toUpperCase();
        password = password.toUpperCase();
        StringBuilder res = new StringBuilder();

        if(s.length() > password.length()){
            String newPass = match(s, password);
            for (int i = 0; i < s.length(); i++) {
                res.append((char) (s.charAt(i) +1 ));
            }
        } else {
            for (int i = 0; i < s.length(); i++) {
                res.append((char) (s.charAt(i) + 1));
            }
        }

        return res.toString();
    }

    static String decode(String s, String password) {
        return "";
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

    // Asignamos un valor númerico a cada letra del alfabeto
    static Map<Character, Integer> mapChars (String s) {
        HashMap<Character, Integer> alfabeto = new HashMap<Character, Integer>();
        s = s.toUpperCase();

        for (int i = 0; i < s.length(); i++) {
            alfabeto.put(escapeChar(s.charAt(i)), i);
        }

        return alfabeto;
    }

}


