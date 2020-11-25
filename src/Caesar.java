import java.util.Arrays;

public class Caesar {
    /**
     *
     * Hemos de pasarle los parametros de letras normales que se encuentran entre los caracteres
     * 65 y 90
     * Para ello lo que haremos será obtener el residuo del delta entre 26
     * si se nos escapa de esos valores lo que le restaremos es 26 para que vuelva a una letra normal
     *
     * */
    static String cypher(String s, int delta) {
        // Pasamos la string a mayúsculas y calculamos el ascii modulando el delta a %26
        s = s.toUpperCase();
        int ascii = delta % 26;

        // Creamos una nueva string con un StringBuilder para meterle cada caracter
        StringBuilder resultat = new StringBuilder();

        // Creamos un for para recorrer la frase a cifrar
        for (int i = 0; i < s.length(); i++) {
            char lletra = s.charAt(i);

            // Calculamos que el caracter esté entre 65 y 90 para sumarle el ascii y volver al inicio
            if(lletra >= 65 && lletra <= 90){
                // Sumamos el delta total a la letra
                lletra = (char) (lletra + ascii);

                // Si la letra supera el valor de 90 le restaremos 26 para volver a los caracteres normales
                if(lletra > 90) {
                    lletra = (char) (lletra - 26);
                }

                // Asignamos la letra a la string
                resultat.append(lletra);

            } else {
                resultat.append(lletra);
            }

        }

        // Tenemos que usar la función toString() para que el return nos funcione
        return resultat.toString();
    }

    static String decypher(String s, int delta) {

        /**
         *
         * Para descifrar el principio es el mismo que el de cifrar pero a la inversa
         * Seguimos necesitando los mismos valores puesto que han de coincidir
         *
         * Lo único que cambiaremos es el orden
         *
         * */

        s = s.toUpperCase();
        int ascii = delta % 26;
        StringBuilder resultat = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            char lletra = s.charAt(i);

            if(lletra >= 65 && lletra <= 90){

                // Le restamos el valor de ascii para encontrar el valor del caracter
                lletra = (char) (lletra - ascii);

                // Si el valor de letra es menor que 65 sumaremos 26 para que sea una letra válida
                if(lletra < 65) {
                    lletra = (char) (lletra + 26);
                }

                // Añadiremos el caracter de letra a la string
                resultat.append(lletra);

            } else {
                resultat.append(lletra);
            }

        }

        // Devolvemos el resultado
        return resultat.toString();
    }

    static String magic(String s, String ss) {
        s = s.toUpperCase();
        int E = maxValue(ss, 'E');
        int A = maxValue(ss, 'A');

        if(A < E) {
            char lletra = search(s);
            int delta = lletra - 69;

            StringBuilder resultat = new StringBuilder(decypher(s, delta));
            return resultat.toString();
        } else {

            char lletra = search(s);
            int delta = lletra - 65;
            StringBuilder resultat = new StringBuilder(decypher(s, delta));
            return resultat.toString();

        }

    }


    static int maxValue(String s, char c){
        int con = 0;

        s = s.toUpperCase();

        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == c){
                con++;
            }
        }

        return con;
    }

    static char search(String s){
        char lletra = s.charAt(0);

        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) < 65 || s.charAt(i) > 90){
                continue;
            }

            if(maxValue(s, s.charAt(i)) > maxValue(s, lletra)){
                lletra = s.charAt(i);
            }

        }

        return lletra;
    }

}
