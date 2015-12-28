/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datainsert;

import java.awt.Component;
import java.awt.Font;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

/**
 * Modifica a renderização de uma JTable para que esta aceite
 * a inserção de componentes JComboBox em suas células
 * @author Yule Vaz
 */
public class TableRenderers {
    
    /**
     * Cria um editor de células de tabelas
     */
    public class MyComboBoxEditor extends DefaultCellEditor {
        
                /**
                 * Constrói o editor de células de tabelas
                 * @param itens Itens a serem inseridos nos combo box
                 */
                public MyComboBoxEditor(String[] itens) {
                    super(new JComboBox(itens));
                }
            }
    
    /**
     * Cria um renderizador de células de tabelas
     */
    public class MyComboBoxRenderer extends JComboBox implements TableCellRenderer {
                /**
                 * Constrói o renderizador de células de tabelas
                 * @param itens Itens a serem inseridos nos combo box
                 */
                public MyComboBoxRenderer(String[] items) {
                    super(items);
                }

                public Component getTableCellRendererComponent(JTable table, Object value,
                        boolean isSelected, boolean hasFocus, int row, int column) {
                    Font font = new Font("sansserif", Font.PLAIN, 12);
                    setFont(font);
                    if (isSelected) {
                        setForeground(table.getSelectionForeground());
                        super.setBackground(table.getSelectionBackground());
                    } else {
                        setForeground(table.getForeground());
                        setBackground(table.getBackground());
                    }

                    // Select the current value
                    setSelectedItem(value);
                    return this;
                }

      }
    
    /**
     * Modifica célula de uma tabela para combo box
     * @param table Tabela a ser modificada
     * @param itens Itens a serem inseridos
     * @param column Coluna a ser modificada
     */
     public void setCellToCombo( JTable table, String [] itens, int column ) {
            TableColumn col = table.getColumnModel().getColumn(column);
            col.setCellRenderer(new MyComboBoxRenderer(itens));
            col.setCellEditor(new MyComboBoxEditor(itens));
     }
    
}
