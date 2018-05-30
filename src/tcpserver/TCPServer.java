/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import jogo.Tabuleiro;

/**
 *
 * @author 1545-6 IRON V4
 */

public class TCPServer {
    static private Socket client = null;
    static private Tabuleiro velha;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            ServerSocket slisten = new ServerSocket(6868);
            while (true){
                System.out.println("Main:Aguardando Conexao.");
                client = slisten.accept();
                Connection conexao = new Connection(client);
            }
        } catch (IOException ex) {
            Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static class Connection extends Thread {
        ObjectInputStream in;
        ObjectOutputStream out;
        Socket client;
        public Connection(Socket client){
            this.client = client;
            try {
                this.out = new ObjectOutputStream(client.getOutputStream());
            } catch (IOException ex) {
                Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                this.in = new ObjectInputStream(client.getInputStream());
            } catch (IOException ex) {
                Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.start();
        }
        @Override
        public void run(){
            int linha,coluna;
            velha=new Tabuleiro();
            Scanner scan = new Scanner(System.in);
            do{
                try {
                    velha.printvelha();
                    System.out.println("Thread:Aguardando Jogada");
                    velha = (Tabuleiro) in.readObject();
                    System.out.println("Thread:Jogada Recebida: ");
                    velha.printvelha();
                    velha.acabou();
                    if(velha.isTermino()){
                        break;
                    }
                    do
                    {
                    System.out.println("Thread:Sua Linha?"+'\n');
                    linha = scan.nextInt();
                    System.out.println("Thread:Sua Coluna?"+'\n');
                    coluna = scan.nextInt();
                    velha.printvelha();
                    }while(velha.jogada(linha,coluna,'x'));
                    System.out.println("Thread:Mandando Jogada");
                    out.writeObject(velha);
                    velha.acabou();
                } catch (IOException ex) {
                    Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TCPServer.class.getName()).log(Level.SEVERE, null, ex);
                }
            }while(!velha.isTermino());
            System.out.println("Fim de Thread.");
        }
    }
    
}