/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datainsert.controlers;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;

/**
 * Esta classe estática contém aguns métodos úteis para o componente JList a fim de 
 * facilitar o desenvolvimento do aplicativo
 * @author Yule Vaz
 */
public class ListUtils {
    
    /**
     * Adiciona um item Object em uma lista requisitada mantendo os itens anteriores da lista
     * @param list Lista em que o Objeto será inserido
     * @param newItem Item a ser inserido em uma lista
     */
    public static void addContent( javax.swing.JList list, Object newItem ) {
            ListModel aux = list.getModel();
            DefaultListModel listModel = new DefaultListModel();
            boolean isIn = false;
            for ( int i = 0; i < aux.getSize(); i++ ) {
                listModel.addElement(aux.getElementAt(i));
                if ( String.valueOf(newItem).compareTo(String.valueOf(aux.getElementAt(i))) == 0 ) isIn = true;
            }
            if ( !isIn ) listModel.addElement(newItem);
            list.setModel(listModel);
        }

    /**
     * Remove itens dado determinados índices de uma lista 
     * @param list Lista cujos itens serão removidos
     * @param index Índices dos itens a serem removidos
     */
    public static void removeContent( javax.swing.JList list, int [] index ) {
            ListModel aux = list.getModel();
            DefaultListModel listModel = (DefaultListModel)aux;
            
            for ( int i = index.length-1; i >= 0 ; i-- ) {
                listModel.removeElementAt(index[i]);
            }
            list.setModel(listModel);
        }
        
    /**
     * Remove todos os itens de uma lista
     * @param list Lista cujos itens serão removidos
     */
    public static void removeAllContent( javax.swing.JList list ) {
             ListModel aux = list.getModel();
             if ( aux.getSize() != 0 ) {
                DefaultListModel listModel = (DefaultListModel)aux;
                for ( int i = listModel.getSize()-1; i >= 0 ; i-- ) {
                    listModel.removeElementAt(i);
                }
                list.setModel(listModel);
            }
        }
    
    public static void sort( javax.swing.JList list ) {
        ArrayList<String> vec = new ArrayList<String>();
        
        ListModel aux = list.getModel();
        DefaultListModel model = new DefaultListModel();
        
        for ( int i = 0; i < aux.getSize(); i++ ) {
            vec.add(String.valueOf(aux.getElementAt(i)));
        }
        
        Collections.sort(vec);
        
        for ( int i = 0; i < vec.size(); i++ ) {
            model.addElement(vec.get(i));
        }
        
        list.setModel(model);
        
    }
    
    public static void list2List( javax.swing.JList list1, javax.swing.JList list2) {
        
        int [] idx = list1.getSelectedIndices();
        Object element;
        
        for ( int j = 0; j < idx.length; j++ ) {
            element = list1.getModel().getElementAt(idx[j]);
            addContent(list2, element);
        }
        
        removeContent(list1,idx);
        sort(list1);
        sort(list2);
        
    }
}
