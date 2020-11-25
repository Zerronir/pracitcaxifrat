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
        return "";
    }

    static String magic(String s, String ss) {
        return "";
    }
}
