
public class Vigenere {

    public static void main(String[] args) {
        String s = "AAAAAAA";
        String pass = "AAA";

        pruebas(s, pass);

    }

    static void pruebas (String s, String pass){
        s = s.toUpperCase();
        pass = pass.toUpperCase();
        StringBuilder res = new StringBuilder();

        if(s.length() > pass.length()){
            String newPass = appending(s, pass);
            for (int i = 0; i < s.length(); i++) {
                res.append((char) (s.charAt(i) + pass.charAt(i)));
            }
        }

        System.out.println(res.toString());
    }

    static String appending(String s, String pass){
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            res.append(pass);
        }
        return res.toString();
    }

    static String encode(String s, String password) {
        s = s.toUpperCase();
        password = password.toUpperCase();
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < password.length(); i++) {
            res.append((char) (s.charAt(i) + 1));
        }

        //System.out.println(res.toString());



        return res.toString();
    }

    static String decode(String s, String password) {
        return "";
    }
}


