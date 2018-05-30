/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tcpclient;
import java.io.*;
import java.io.IOException;
import static java.lang.System.exit;
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
            out = new ObjectOutputStream(s.getOutputStream());
            in = new ObjectInputStream(s.getInputStream());
            Random rand = new Random();
            int vez = rand.nextInt(2);
            if(vez==0){
                do{
                velha.printvelha();
                System.out.println("Sua Linha?" + '\n');
                linha = scan.nextInt();
                System.out.println("Sua Coluna?" + '\n');
                coluna = scan.nextInt();
                }while (velha.jogada(linha, coluna, 'o'));
                System.out.println("Thread:Mandando Jogada");
                out.writeObject(velha);
            }
            else 
                out.writeObject(velha);
            do{
                try {
                    System.out.println("Aguardando Jogada");
                    velha = (Tabuleiro) in.readObject();
                    velha.acabou();
                    if(velha.isTermino()){
                        break;
                    }
                    System.out.println("Jogada Recebida: ");
                    velha.printvelha();
                    do
                    {
                    System.out.println("Sua Linha?"+'\n');
                    linha = scan.nextInt();
                    System.out.println("Sua Coluna?"+'\n');
                    coluna = scan.nextInt();
                    velha.printvelha();
                    }while(velha.jogada(linha,coluna,'o'));
                    System.out.println("Mandando Jogada");
                    out.writeObject(velha);
                    velha.acabou();
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
