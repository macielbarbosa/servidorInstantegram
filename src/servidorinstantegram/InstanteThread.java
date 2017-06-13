/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidorinstantegram;

/**
 *
 * @author jessa
 */
public class InstanteThread extends Thread {
    public long time;

    public InstanteThread(long time) {
        this.time = time;
    }
    @Override
    public void run() {
        while (true) {          
            try{
                
                Thread.sleep(this.time);
            }
            catch(InterruptedException E){
                E.printStackTrace();
            }
        }
    }
}
