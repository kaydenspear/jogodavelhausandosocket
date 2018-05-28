/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpclient;
import java.io.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import jogo.Tabuleiro;

/**
 *
 * @author 1545-6 IRON V4
 */
public class TCPClient {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        ObjectInputStream in;
        ObjectOutputStream out;
        Socket s = null;
        Tabuleiro velha=new Tabuleiro();
        Scanner scan = new Scanner(System.in);
        int coluna,linha;
        try {
            int port = 6868;
            InetAddress server = InetAddress.getLocalHost();
            s = new Socket(server, port);
            in = new ObjectInputStream(s.getInputStream());
            out = new ObjectOutputStream(s.getOutputStream());
            Random rand = new Random();
            System.out.println("test");
            int vez = rand.nextInt(1);
            if(vez==0)
                do{
                System.out.println("Sua Linha?" + '\n');
                linha = scan.nextInt();
                System.out.println("Sua Coluna?" + '\n');
                coluna = scan.nextInt();
                }while (velha.jogada(linha, coluna, 'o'));
            System.out.println("Thread:Mandando Jogada");
            out.writeObject(velha);
            do{
                try {
                    System.out.println("Aguardando Jogada");
                    velha = (Tabuleiro) in.readObject();
                    System.out.println("Jogada Recebida: ");
                    velha.printvelha();
                    do
                    {
                    System.out.println("Sua Linha?"+'\n');
                    linha = scan.nextInt();
                    System.out.println("Sua Coluna?"+'\n');
                    coluna = scan.nextInt();
                    }while(velha.jogada(linha,coluna,'o'));
                    System.out.println("Mandando Jogada");
                    out.writeObject(velha);
                } catch (IOException ex) {
                    Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
                }
            }while(!velha.isTermino());
        } finally {if (s != null )
                try {
                    s.close();
                } catch (IOException ex) {
            Logger.getLogger(TCPClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
    }
}
