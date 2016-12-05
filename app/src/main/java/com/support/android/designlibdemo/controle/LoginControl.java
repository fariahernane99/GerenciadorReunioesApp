package com.support.android.designlibdemo.controle;

import android.content.Context;
import android.util.Log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.support.android.designlibdemo.modelo.*;

public class LoginControl {

    private static Servidor servidorLogado;
    private static MessageDigest md = null;

    public void preencheBD() {
        //para usar um socket é necessário abrir uma nova thread no android
        try {
            Thread t = new Thread(new Runnable() {
                public void run() {
                    //conecta ao servidor tcp
                    ClienteTCP c = new ClienteTCP("172.16.2.168", 3322);

                    //envia o texto para o servidor e recebe na variável textoResposta
                    final String textoResposta = c.comunica("enviaServidores");

                    //printa resposta no log do android
                    Log.d("SOCKET", textoResposta);
                }
            });
            t.start();
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    public boolean autenticaLogin(String siape, String senha, Context context) {
        BD bd = new BD(context);
        senha = criptografar(senha);
        boolean encontrou = false;
        for (Servidor s : bd.getServidores()) {
            if ((siape.equals(s.getSiape())) && (s.getSenha().equals(senha))) {
                servidorLogado = s;
                encontrou = true;
            }
        }
        return encontrou;
    }

    public static Servidor retornaServidorLogado() {
        return servidorLogado;
    }

    static {
        //Try catch referente ao algoritmo do MD5 e seus possiveis erros
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }

    private static char[] hexCodes(byte[] text) {
        char[] hexOutput = new char[text.length * 2];
        String hexString;

        for (int i = 0; i < text.length; i++) {
            hexString = "00" + Integer.toHexString(text[i]);
            hexString.toLowerCase().getChars(hexString.length() - 2,
                    hexString.length(), hexOutput, i * 2);
        }
        return hexOutput;
    }

    public static String criptografar(String pwd) {
        if (md != null) {
            return new String(hexCodes(md.digest(pwd.getBytes())));
        }
        return null;
    }

}
