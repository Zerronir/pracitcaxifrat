

public class Transposition {
    static String cypher(String s, int dim) {
        return "";
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
