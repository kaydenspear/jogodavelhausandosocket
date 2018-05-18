/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogo;

/**
 *
 * @author 1545-6 IRON V4
 */
public class Tabuleiro {
    static private char velha[][]=new char[3][3];
    private boolean termino = false;
    

    public void printvelha() {
        //print simples do tabuleiro para auxiliar a escolha da jogada do usuario
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean jogada(int linha, int coluna) {
        //testa a jogada [linha][coluna], caso seja valida retorna true, se nao, retorna falso e espera a proxima jogada
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static char[][] getVelha() {
        return velha;
    }

    public static void setVelha(char[][] velha) {
        Tabuleiro.velha = velha;
    }

    public boolean isTermino() {
        return termino;
    }

    public void setTermino(boolean termino) {
        this.termino = termino;
    }
    
}
