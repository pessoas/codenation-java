package challenge;

import java.util.Arrays;

public class CriptografiaCesariana implements Criptografia {

    @Override
    public String criptografar(String texto) {

        if(texto.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            String cripto = "";

            texto = texto.toLowerCase();

            for (int i = 0; i < texto.length(); i++) {
                if(texto.charAt(i) > 96 && texto.charAt(i) < 119) {
                    cripto += (char) (texto.charAt(i) + 3);
                } else if (texto.charAt(i) > 119 && texto.charAt(i) < 123) {
                    cripto += (char) (texto.charAt(i) - 23);
                } else {
                    cripto += texto.charAt(i);
                }
            }
            return cripto;
        }

    }

    @Override
    public String descriptografar(String texto) {

        if(texto.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            String decripto = "";

            texto = texto.toLowerCase();

            for (int i = 0; i < texto.length(); i++) {
                if(texto.charAt(i) > 99 && texto.charAt(i) < 123) {
                    decripto += (char) (texto.charAt(i) - 3);
                } else if (texto.charAt(i) < 100 && texto.charAt(i) > 96) {
                    decripto += (char) (texto.charAt(i) + 23);
                } else {
                    decripto += texto.charAt(i);
                }
            }
            return decripto;
        }

    }

/*
    public static void main(String[] args) {
        char a;
        char b = 'b';
        String n = "kjdlasj";
        a = (char) (n.charAt(0) + 3);

        n += a;

        System.out.println(n);
        System.out.println("djashkdj");

        CriptografiaCesariana aaaa = new CriptografiaCesariana();

        String jj;
        jj = aaaa.criptografar("renato");

        System.out.println(jj);
    }*/
}
