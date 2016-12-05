package com.support.android.designlibdemo.controle;

/**
 * @author fernando
 */

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketException;

public class ClienteTCP {

    private Socket s; //socket res
    private static String ipServ = ""; //ip do servidor
    private static int portaServ = 0; //porta do servidor

    //Método construtor criando o socket, estabelecendo conexão com o servidor
    public ClienteTCP(String ip, int p) {
        try {
            Log.d("FOI", "IP e porta do servidor: " + ip + ", " + p);
            s = new Socket(ip, p);//ip: ip, p: porta
        } catch (SocketException e) {
            e.printStackTrace();
            Log.d("FOI", "erro conexao: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("FOI", "erro conexao: " + e.getMessage());
        }
    }

    public String comunica(String msg) {
        String msgRecebida = "";
        try {
            //bufer de leitura
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));

            //buffer de escrita
            PrintStream ps = new PrintStream(s.getOutputStream());

            //envia mensagem ao servidor
            ps.println(msg);

            //recebe mensagem resposta do servidor
            String linha;
            while (!(linha = br.readLine()).isEmpty()) {
                msgRecebida += linha;
            }
            //retorna o que foi feito no servidor
            System.out.println(msgRecebida);

            //fecha conexão
            s.close();
        } catch (Exception e) {
            Log.d("FOI", "erro no envio: " + e.getMessage());
            e.printStackTrace();
        }
        return msgRecebida;
    }
}

