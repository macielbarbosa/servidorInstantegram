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
    protected static boolean isCadastrado(String login){
        String diretorio = "dados"+File.separator+"usuarios"+File.separator+login;
        return new File(diretorio).exists();
    }
    
    protected static void cadastro(Usuario usuario){
        String diretorio = "dados"+File.separator+"usuarios"+File.separator;
        new File(diretorio+usuario.getLogin()).mkdir();
    }
    
    /*protected static Usuario getUsuario(String nomeUsuario){
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
    /*protected static Usuario autenticacao(Socket cliente){
            try{
                
                    
                }
                catch(Exception Ex){
                    System.out.println("Erro na autenticacao");
                    continue;
                }
        
        BufferedReader bf;
        StringTokenizer st;
        String linha, key = "", loginUsuario, loginKey;
        
        for(int i=0; i<senha.getPassword().length;i++)
            key+=senha.getPassword()[i];
        try{
            bf = new BufferedReader(new FileReader("dados"+File.separator+"autenticacao.txt"));
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
    }*/

    public static void main(String arg[]) throws IOException {
        ServerSocket servidor;
        
        //NewsServer Noticias = new NewsServer();
        //Thread runNoticias = new Thread(Noticias);
        
        //runNoticias.start();

        try {
            servidor = new ServerSocket(12345);
            System.out.println("Servidor aberto");
            // loop infinito
            while (true) {
                // Aceitar uma conexao
                System.out.println("Esperando cliente...");
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado");
                ObjectInputStream entrada = new ObjectInputStream(cliente.getInputStream());
                String comando;
                try {
                    comando = entrada.readUTF();
                    Usuario novoUsuario = (Usuario) entrada.readObject();
                    
                    if(comando.equals("cadastrar")){
                        System.out.println("Cadastrando cliente...");
                        if(!Servidor.isCadastrado(novoUsuario.getLogin())){
                            new ObjectOutputStream(cliente.getOutputStream()).writeBoolean(true);
                            Servidor.cadastro(novoUsuario);
                            System.out.println("Cliente casdastrado");
                        }
                        else
                            new ObjectOutputStream(cliente.getOutputStream()).writeBoolean(false);
                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                }
                //if(comando.equals("entrar"))
                    //Servidor.autenticacao(cliente);
                cliente.close();
                    
                //new InstanteThread(2000).start();
            }
                
                
                // Adicionar o cliente ao servico de noticias
                //Noticias.novoUsuario(new DataOutputStream(cliente.getOutputStream()));
        } catch (IOException E) {
            E.printStackTrace();
        }
    }
}
