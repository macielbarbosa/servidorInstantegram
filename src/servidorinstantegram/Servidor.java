/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorinstantegram;

import java.net.*;
import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/**
 *
 * @author labinfo11
 */
public class Servidor {
    protected static Usuario getUsuario(String nomeUsuario){
        try {
            String diretorio = "dados"+File.separator+"usuarios"+File.separator+nomeUsuario+File.separator;
            BufferedReader bf = new BufferedReader(new FileReader(diretorio+"identificacao"));
            String nome = bf.readLine();
            String sobrenome = bf.readLine();
            String sexo = bf.readLine();
            Usuario usuario = new Usuario(nome,sobrenome,sexo);
            bf = new BufferedReader(new FileReader(diretorio+"publicacoes"));
            while(true){
                String mensagem = bf.readLine();
                String caminhoImg = bf.readLine();
                String data = bf.readLine();
                String curtidas = bf.readLine();
                usuario.addPublicacao(new Publicacao(usuario,mensagem,ImageIO.read(new File(caminhoImg)),data));
                if(bf.readLine()==null)
                    break;
            }
            bf = new BufferedReader(new FileReader(diretorio+"seguidores"));
            while(true){
                String seguidor = bf.readLine();
                usuario.addSeguidor(seguidor);
                if(bf.readLine()==null)
                    break;
            }  
            bf = new BufferedReader(new FileReader(diretorio+"seguidos"));
            while(true){
                String seguido = bf.readLine();
                usuario.addSeguido(seguido);
                if(bf.readLine()==null)
                    break;
            }
            return usuario;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    protected static Usuario autenticacao(JTextField usuario, JPasswordField senha){
        BufferedReader bf;
        StringTokenizer st;
        String linha, key = "", loginUsuario, loginKey;
        
        for(int i=0; i<senha.getPassword().length;i++)
            key+=senha.getPassword()[i];
        try{
            bf = new BufferedReader(new FileReader("dados"+File.separator+"usuarios.txt"));
            linha = bf.readLine();
            while(linha!=null){    
                st = new StringTokenizer(linha,";");
                loginUsuario = st.nextToken();                
                loginKey = st.nextToken();
                if(loginUsuario.equals(usuario.getText()) && loginKey.equals(key)){
                    bf.close();
                    return Servidor.getUsuario(usuario.getText());
                }
                linha = bf.readLine();
                if(linha==null){
                    bf.close();
                }
            }
            
        }catch(IOException E){
            System.out.println(E);
        }
        return null;
    }

    public static void main(String arg[]) throws IOException {
        ServerSocket servidor;
        
        //NewsServer Noticias = new NewsServer();
        //Thread runNoticias = new Thread(Noticias);
        
        //runNoticias.start();

        try {
            servidor = new ServerSocket(12345);

            // loop infinito
            while (true) {
                // Aceitar uma conexao
                Socket cliente = servidor.accept();
                ObjectInputStream input = new ObjectInputStream(cliente.getInputStream());
                try{
                    JTextField usuario = (JTextField) input.readObject();
                    JPasswordField senha = (JPasswordField) input.readObject();
                    //new ObjectOutputStream(Servidor.autenticacao(usuario,senha));
                }
                catch(Exception Ex){
                    System.out.println("Fim dos livros");
                    continue;
                }
                new InstanteThread(2000).start();
            }
                
                
                // Adicionar o cliente ao servico de noticias
                //Noticias.novoUsuario(new DataOutputStream(cliente.getOutputStream()));
        } catch (IOException E) {
            E.printStackTrace();
        }
    }
}
