/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorinstantegram;

import java.net.*;
import java.io.*;
import java.util.*;
/**
 *
 * @author labinfo11
 */
public class Main {

    public static void main(String arg[]) {
        ServerSocket servidor;
        // Thread para enviar noticias
        NewsServer Noticias = new NewsServer();
        Thread runNoticias = new Thread(Noticias);
        // Iniciar o Thread (serviÃ§o de notÃ­cias)
        runNoticias.start();

        try {
            servidor = new ServerSocket(12345);

            // loop infinito
            while (true) {
                // Aceitar uma conexao
                Socket cliente = servidor.accept();

                // Adicionar o cliente ao servico de noticias
                Noticias.novoUsuario(new DataOutputStream(cliente.getOutputStream()));
            }
        } catch (IOException E) {
            E.printStackTrace();
        }
    }
}
