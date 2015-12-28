/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datainsert.controlers;

import java.util.ArrayList;

/**
 * Classe controladora da máscara para a divisão consistente dos dados
 * @author Yule Vaz
 */
public class ParseMaskControler {
    
    /**
     * Conjunto de caracteres utilizados como divisores de dados
     */
    private ArrayList<Character> mask;
    private boolean def;
    
    /**
     * Constrói um controlador de máscara a partir de um conjunto de caracteres pré-determinado
     * @param mask Conjunto de caracteres utlizado no controlador de máscara
     */
    public ParseMaskControler(ArrayList<Character> mask) {
        this.mask = mask;
        this.def = true;
    }
    
    /**
     * Constrói um controlador de máscara sem um conjunto de caracteres pré-determinado
     */
    public ParseMaskControler() {
        this.mask = new ArrayList<Character>();
        this.add(' ');
        this.add('\t');
        this.def = true;
    }
    
    /**
     * Adiciona um caracter ao conjunto de máscara de uma instância ParseMaskControler
     * @param s Caracter a ser inserido ao conjunto
     */
    public void add(Character s) {
        if (!mask.contains(s))
            mask.add(s);
        this.def = false;
    }
    
    /**
     * Remove um caracter ao conjunto de máscara de uma instância ParseMaskControler
     * @param s Caracter a ser removido do conjunto
     */
    public void remove(Character s) {
        mask.remove(s);
        this.def = false;
    }
    
    /**
     * @param i Posição em que situa-se o caracter
     * @return O caracter encontrado na posição i
     */
    public Character get(int i) {
        return mask.get(i);
    }
    
    /**
     * 
     * @param i Índice do caracter a ser sobrescrito
     * @param s Caracter a ser inserido
     */
    public void set(int i, Character s) {
        mask.set(i, s);
        this.def = false;
    }
    
    /**
     * 
     * @return Conjunto de caracteres da máscara
     */
    public ArrayList<Character> get() {
        return mask;
    }
    
    /**
     * 
     * @param mask Novo conjunto de caracteres a ser inseridos na máscara
     */
    public void set(ArrayList<Character> mask) {
        this.mask = mask;
        this.def = false;
    }
    
    public boolean isDefault() {
        return def;
    }
    
}
