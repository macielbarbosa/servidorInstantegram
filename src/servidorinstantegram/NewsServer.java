/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorinstantegram;

import java.net.*;
import java.io.*;
import java.util.*;

public class NewsServer implements Runnable {
    
    private ArrayList<DataOutputStream> usuarios;

    public NewsServer() {
        this.usuarios = new ArrayList<DataOutputStream>();
    }

    /**
     * Adicionar um cliente
     */
    public void novoUsuario(DataOutputStream C) {
        usuarios.add(C);
    }

    // execucao do Thread
    public void run() {
        int nnoticia = 1;
        while (true) {
            System.out.println("Enviando notÃ­cias a " + usuarios.size() + " usuarios [" + new Date() + "]");
            for (DataOutputStream c :  usuarios) {
                try {
                    c.writeUTF("Nova Noticia " + nnoticia);

                } catch (IOException E) {
                    System.out.println("Error: " + E.getMessage());
                }
            }
            nnoticia++;
            try {
                //Pausa 2s
                Thread.sleep(2000);
            } catch (InterruptedException E) {
                E.printStackTrace();
            }
        }
    }
}

