/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorinstantegram;

import java.awt.image.BufferedImage;
import java.util.Date;

/**
 *
 * @author labinfo11
 */
public class Publicacao {
    private BufferedImage imgPerfil;
    private String nomeUsuario;
    private String mensagem;
    private BufferedImage foto;
    private Date data;
    private short curtidas;

    public Publicacao(BufferedImage imgPerfil, String nomeUsuario, String mensagem, BufferedImage foto, Date data, short curtidas) {
        this.imgPerfil = imgPerfil;
        this.nomeUsuario = nomeUsuario;
        this.mensagem = mensagem;
        this.foto = foto;
        this.data = data;
        this.curtidas = curtidas;
    }

    public BufferedImage getImgPerfil() {
        return imgPerfil;
    }

    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public String getMensagem() {
        return mensagem;
    }

    public BufferedImage getFoto() {
        return foto;
    }

    public Date getData() {
        return data;
    }

    public short getCurtidas() {
        return curtidas;
    }
    
    
}
