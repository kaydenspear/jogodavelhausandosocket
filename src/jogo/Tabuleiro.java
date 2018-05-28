/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;
import java.util.*;

/**
 *
 * @author 1545-6 IRON V4
 */
public class Tabuleiro {
    private char velha[][]=new char[3][3];
    private boolean termino = false;

    public Tabuleiro() {
        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                setVelha(i,j,' ');
            }
        }
    }
    
    public boolean jogada(int linha,int coluna,char simbolo){
        isTermino(linha,coluna);
        if(linha<0||linha>2||coluna<0||coluna>2)
            return false;
        if(this.velha[linha][coluna]==' '){
            this.velha[linha][coluna]=simbolo;
            return true;
        }
        return false;
    }
    
    public void printvelha(){
        for(int i=0;i<2;i++){
            for(int j=0;j<2;j++){
                System.out.println(velha[i][j]+' ');
            }
            System.out.println("");
        }
    }
    public boolean isTermino() {
        return termino;
    }

    public void setTermino(boolean termino) {
        this.termino = termino;
    }

    private boolean isLinha(int linha) {
        if(this.velha[linha][0]==this.velha[linha][1]&&this.velha[linha][0]==this.velha[linha][2]&&this.velha[linha][1]!=' ')
            return true;
        return false;
    }

    private boolean isColuna(int coluna) {
        if(this.velha[0][coluna]==this.velha[1][coluna]&&this.velha[0][coluna]==this.velha[2][coluna]&&this.velha[1][coluna]!=' ')
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
    
    private void isTermino(int linha, int coluna) {
        if(isLinha(linha)||isColuna(coluna)||isDiagonal())
            this.termino=true;
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
}