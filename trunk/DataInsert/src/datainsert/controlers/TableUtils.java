/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datainsert.controlers;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * Classe que contém métodos estáticos de auxilio a manipulação de JTable.
 * @author Yule Vaz
 */
public class TableUtils {
    /**
     * Remove todo o conteúdo de uma tabela
     * @param table Tabela da qual será removido todo o conteúdo
     */
    public static void removeAllContent( JTable table ) {
            
            TableModel model = table.getModel();
            
            String [] title = new String[model.getColumnCount()];
            
            for ( int i = 0; i < model.getColumnCount(); i++ )
                 title[i] = model.getColumnName(i); 
            
            DefaultTableModel newModel = new DefaultTableModel(null,title);
            table.setModel(newModel);
        }
    
    /**
     * Limpa seleção da tabela
     * @param table Tabela da qual as seleções serão limpas
     */
    public static void deselectAll( JTable table ) {
            ListSelectionModel model = table.getSelectionModel();
            model.clearSelection();
        }
}
