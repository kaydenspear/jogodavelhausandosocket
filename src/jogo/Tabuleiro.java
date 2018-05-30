/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

/**
 *
 * @author 1545-6 IRON V4
 */
public class Tabuleiro implements Serializable{
    private char velha[][]=new char[3][3];
    private boolean termino = false;

    public Tabuleiro() {
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                setVelha(i,j,' ');
            }
        }
    }
    
    public boolean jogada(int linha,int coluna,char simbolo){
        if(linha<0||linha>2||coluna<0||coluna>2)
            return true;
        if(this.velha[linha][coluna]==' '){
            this.velha[linha][coluna]=simbolo;
            return false;
        }
        return true;
    }
    
    public void printvelha(){
        System.out.println(" 0 1 2");
        System.out.println("0" + velha[0][0] + "|" + velha[0][1] + "|"
                + velha[0][2]);
        System.out.println(" -----");
        System.out.println("1" + velha[1][0] + "|" + velha[1][1] + "|"
                + velha[1][2]);
        System.out.println(" -----");
        System.out.println("2" + velha[2][0] + "|" + velha[2][1] + "|"
                + velha[2][2]);
    }
    public boolean isTermino() {
        return termino;
    }

    public void setTermino(boolean termino) {
        this.termino = termino;
    }

    private boolean isLinha() {
        if(this.velha[0][0]==this.velha[0][1]&&this.velha[0][1]==this.velha[0][2]&&this.velha[0][0]!=' ')
            return true;
        if(this.velha[1][0]==this.velha[1][1]&&this.velha[1][1]==this.velha[1][2]&&this.velha[1][0]!=' ')
            return true;
        if(this.velha[2][0]==this.velha[2][1]&&this.velha[2][1]==this.velha[2][2]&&this.velha[2][0]!=' ')
            return true;
        return false;
    }

    private boolean isColuna() {
        if(this.velha[0][0]==this.velha[1][0]&&this.velha[1][0]==this.velha[2][0]&&this.velha[0][0]!=' ')
            return true;
        if(this.velha[0][1]==this.velha[1][1]&&this.velha[1][1]==this.velha[2][1]&&this.velha[0][1]!=' ')
            return true;
        if(this.velha[0][2]==this.velha[1][2]&&this.velha[1][2]==this.velha[2][2]&&this.velha[0][2]!=' ')
            return true;
        return false;
    }

    private boolean isDiagonal() {
        if(this.velha[0][0]==this.velha[1][1]&&this.velha[1][1]==this.velha[2][2]&&this.velha[1][1]!=' ')
            return true;
        if(this.velha[0][2]==this.velha[1][1]&&this.velha[1][1]==this.velha[2][0]&&this.velha[1][1]!=' ')
            return true;
        return false;
    }
    
    public void acabou() {
        if(isLinha()||isColuna()||isDiagonal())
            this.setTermino(true);
    }

    private void setVelha(int linha, int coluna, char simbolo) {
        this.velha[linha][coluna]=simbolo;
    }

    public void setVelha(char[][] velha) {
        this.velha = velha;
    }

    public char[][] getVelha() {
        return velha;
    }
    
    /*
    private void readObject(ObjectInputStream aInputStream) throws ClassNotFoundException, IOException {
        aInputStream.defaultReadObject();
    }

    private void writeObject(ObjectOutputStream aOutputStream) throws IOException {
        aOutputStream.defaultWriteObject();
    }*/
}