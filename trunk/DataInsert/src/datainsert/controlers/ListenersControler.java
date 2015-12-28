/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datainsert.controlers;

import datainsert.dbinterface.DBInterface;
import java.awt.event.ActionListener;

/**
 * Controlador de Listeners(ouvinte) do aplicativo. Os Listeners serão utilizado para efetuar operações
 * dada alguma ação do usuário sobre a interface (Por exemplo: acionar um botão).
 * Os ouvintes determinarão também, qual operação será efetuada dado o componente a que foi efetuada
 * uma ação.
 * @author Yule Vaz
 */
public class ListenersControler {

    /**
     * Registra um ouvinte a um determinado botão
     * @param obs Ouvinte a ser registrado
     * @param button Botão em que o ouvinte será registrado
     */
    public static void addButtonActionListener( ActionListener obs, javax.swing.JButton button ) {

        button.addActionListener( obs );
        
    }

    /**
     * Registra um ouvinte a uma determinada combo box
     * @param obs Ouvinte a ser registrado
     * @param combo Combo box em que o ouvinte será registrado
     */
    public static void addComboActionListener( ActionListener obs, javax.swing.JComboBox combo ) {

        combo.addActionListener( obs );

    }  

    /**
     * Registra um ouvinte a vários botões
     * @param obs Ouvinte a ser registrado
     * @param button Botões em que o ouvinte será registrado
     */
    public static void addButtonActionListener( ActionListener obs, javax.swing.JButton [] button ) {

        for ( int i = 0; i < button.length; i++ )
            button[i].addActionListener(obs);

    }
    
    /**
     * Registra um ouvinte a um item de menu
     * @param obs Ouvinte a ser registrado
     * @param item Item de menu em que o ouvinte será registrado
     */
    public static void addMenuActionListener( ActionListener obs, javax.swing.JMenuItem item ) {
        item.addActionListener(obs);
    }

}
