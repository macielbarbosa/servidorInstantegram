/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorinstantegram;

import java.awt.image.BufferedImage;

/**
 *
 * @author labinfo11
 */
public class Publicacao {
    private Usuario usuario;
    private String mensagem;
    private BufferedImage foto;
    private String data;
    private short curtidas;

    public Publicacao(Usuario usuario, String mensagem, BufferedImage foto, String data) {
        this.usuario = usuario;
        this.mensagem = mensagem;
        this.foto = foto;
        this.data = data;
        this.curtidas = 0;
    }
    
    public void curtir(){
        curtidas++;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public BufferedImage getFoto() {
        return foto;
    }

    public String getData() {
        return data;
    }

    public short getCurtidas() {
        return curtidas;
    }
    
    public void setCurtidas(short curtidas){
        this.curtidas = curtidas;
    }
    
}
