/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datainsert.controlers;

import datainsert.ExceptionToken;
import datainsert.Reader;
import datainsert.TableRenderers;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * Classe que faz o controle de conteúdo do aplicativo seja este oriundo do arquivo
 * de importação ou de uma tabela ou de uma lista. Aplica transições entre os dados dos
 * componentes.
 * @author Yule Vaz
 */
public class ContentControler {

//    public static void setTableRenderer( JTable jtabDados ) {
//
//        TableCellRenderer defaultRenderer = jtabDados.getDefaultRenderer(TableButton.class);
//
//        jtabDados.setDefaultRenderer(TableButton.class, new JTableButtonRenderer(defaultRenderer));
//    }

    /**
     * Arquivo a ser importado para os componentes do aplicativo
     */
    private File file;
    private ArrayList<String> str;
    /**
     * Lista com os dados do arquivo devidamente divididos
     */
    private ArrayList<String> parsed;
    /**
     * Controlador de tabelas
     */
    private TableControler tbControler;
    /**
     * Controlador de combo box
     */
    private ComboControler cmbControler;
    /**
     * Controlador de listas
     */
    private ListControler lstControler;
    /**
     * Titulos das tabelas
     */
    private String [] titles;
    /**
     * IDs dos dispositivos
     */
    private String [] ids;
    /**
     * Renderizador de tabela
     */
    public final TableRenderers tableRenderers = new TableRenderers();
    
    public ContentControler() {
        tbControler = new TableControler();
        cmbControler = new ComboControler();
        lstControler = new ListControler();
        str = new ArrayList<String>();
        parsed = new ArrayList<String>();
    }

    public TableControler getTableControler() {
        return tbControler;
    }

    public ComboControler getComboControler() {
        return cmbControler;
    }

    public ListControler getListControler() {
        return lstControler;
    }
    
    public void close() {
        file = null;
    }
    
    /**
     * Importa arquivo para os componentes do aplicativo
     * @param file Arquivo a ser importado
     * @param c Filtro de caractere para definir a separação dos dados
     * @param expt Token de exceção para o retorno das exceções desta função e de posteriores
     */
    public void loadFile( File file, Character [] c, ExceptionToken expt ) {
        try {
            this.file = file;
            BufferedReader buffreader;
            buffreader = new BufferedReader(new FileReader(file));
            str = Reader.read( buffreader, expt );
            parsed = Reader.parse(str.get(0),c);
            titles = new String[parsed.size()];
            for ( int j = 0; j < parsed.size(); j++ ) {
                            titles[j] = parsed.get(j);
    //                        tableObj[0][j] = new TableButton("B"+String.valueOf(j), j);

            }
        } catch (FileNotFoundException ex) {
            expt.addErr();
        }
    }

    public void setIds( String [] ids ) {
        this.ids = ids;
    }

    /**
     * Classe utilizada para executar transições de dados a partir de uma tabela
     */
    public class TableControler {

        /**
         * Modifica modelo da tabela. Utilizado para inserir os dados na tabela
         * @param obj Objetos a serem inseridos nas células da tabela
         * @param str Títulos das colunas da tabela
         * @param jtabDados Tabela a ser modificada
         */
        public void setModel(Object[][] obj, String[] str, JTable jtabDados) {
            jtabDados.setModel(new javax.swing.table.DefaultTableModel(obj, str));
        }

        /**
         * Modifica modelo da tabela. Utilizado para inserir os dados na tabela
         * @param tabModel Modelo a ser inserido na tabela
         * @param jtabDados Tabela a ser modificada
         */
        public void setModel(javax.swing.table.DefaultTableModel tabModel, JTable jtabDados ) {
            jtabDados.setModel(tabModel);
        }

        public javax.swing.table.DefaultTableModel getModel(Character [] mask) {

            javax.swing.table.DefaultTableModel tabModel = null;

                BufferedReader buffreader;
                Object [][] tableObj;

                tableObj = new Object[str.size()][parsed.size()];
                
                for ( int i = 0; i < str.size()-1;i++ ) {
                    parsed = Reader.parse(str.get(i+1),mask);
                    for ( int j = 0; j < parsed.size(); j++ ) {
                        tableObj[i][j] = parsed.get(j);
                    }
                }

                tabModel = new javax.swing.table.DefaultTableModel(tableObj,titles);

            return tabModel;

        }
        
        /**
         * Adiciona dados de interesse do aplicativo de uma tabela para uma lista
         * @param table Tabela em que os dados serão retirados
         * @param list Lista em que os dados serão inseridos
         * @param index Índices das colunas em que os dados serão retirados
         */
        public void addTable2Lst( javax.swing.JTable table, javax.swing.JList list, int [] index ) {
            DefaultTableModel tabModel = (DefaultTableModel)table.getModel();
            if ( tabModel.getRowCount() > 0 )  {
                ArrayList<String> vec = new ArrayList<String>();
                int i;
                DefaultListModel listModel = (DefaultListModel)list.getModel();
                DefaultListModel addModel = new DefaultListModel();
                int length;
                length = index.length+tabModel.getRowCount();

                for ( i = index.length-1; i >= 0 ;i-- ) {
                    vec.add(String.valueOf(tabModel.getValueAt(index[i],0)));
                    tabModel.removeRow(index[i]);
                }

                for ( i = 0; i < listModel.getSize(); i++ ) {
                    vec.add(String.valueOf(listModel.get(i)));
                }
                    
                Collections.sort(vec);
                for ( i = 0; i < vec.size(); i++ ) {
                    addModel.addElement(vec.get(i));          
                }
                list.setModel( addModel );
                table.setModel( tabModel );
            }
            
        }
        
    }
    /**
     * Controlador de conteúdo das combo box
     */
    public class ComboControler {

        public void setContent( javax.swing.JComboBox combo ) {
            combo.setModel(new MyComboBoxModel());
            //combo.setSelectedIndex();
        }
        
        public void setContent( javax.swing.JComboBox combo, String [] modelStr ) {
            ComboBoxModel comboModel = new MyComboBoxModel(modelStr);
            combo.setModel(comboModel);
        }

        /**
         * Modelo abstrato de combo box. Possui atributos tais quais o modelo de combo box e
         * que item se encontra selecionado. 
         */
        public class MyComboBoxModel extends AbstractListModel implements ComboBoxModel {
                  
                 /**
                  * Modelo registrado em uma combo box
                  */
                  private String[] modelStr;
                  /**
                   * Itens selecionado em uma combo box
                   */
                  private String selection = null;

                  /**
                   * Constrói um modelo de combo box com os conteúdos requisitados do arquivo importado
                   */
                  public MyComboBoxModel() {
                      modelStr = new String[titles.length-2];
                      for ( int i = 0; i < titles.length-2; i++ ) {
                          modelStr[i] = titles[i+2];
                      }
                  }
                  
                  /**
                   * Constrói um modelo de combo box com passagem de parâmetro.
                   * @param modelStr Itens de modelo recebidos para criar o novo modelo de combo box
                   */
                  public MyComboBoxModel( String [] modelStr ) {
                      this.modelStr = modelStr;
                  }

                  @Override
                  public Object getElementAt(int index) {
                    return modelStr[index];
                  }

                  @Override
                  public int getSize() {
                    return modelStr.length;
                  }

                  @Override
                  public void setSelectedItem(Object anItem) {
                    selection = (String) anItem; // Registrar um item na combo box
                  } // item from the pull-down list

                  // Método implementado da interface ComboBoxModel
                  @Override
                  public Object getSelectedItem() {
                    return selection; // Receber seleção da combo box
                  }
       }

    }
    
    /**
     * Classe controladora dos conteúdos de uma lista. Estes itens sao estáticos e utilizados apenas na
     * lista da interface de dispositivos. Utilizada também para a transferência de dados entre a lista de dispositivos
     * do arquivo de importação para a tabela de dispositivos a serem importados.
     */
    public class ListControler {
        
        /**
         * Adiciona o conteúdo de uma lista a uma tabela
         * @param list Lista em que o conteúdo se encontra
         * @param table Tabela em que o conteúdo será inserido
         * @param index Índices referentes aos itens da lista que serão inseridos na tabela
         */
        public void addLst2Table( javax.swing.JList list, javax.swing.JTable table, int index [] ) {
            ArrayList<String> vec = new ArrayList<String>();
            int i;
            DefaultListModel listModel = (DefaultListModel)list.getModel();
            TableModel tabModel = table.getModel();
            int length;
            
            if ( tabModel.getRowCount() > 0 ) length = index.length+tabModel.getRowCount();
            else length = index.length;
            
            for ( i = index.length-1; i >= 0 ;i-- ) {
                vec.add(String.valueOf(listModel.getElementAt(index[i])));
                listModel.remove(index[i]);
            }
            
            Object [][] obj = new Object[length][2];
            
            if ( tabModel.getRowCount() > 0 ) {
                for ( i = 0; i < tabModel.getRowCount(); i++ ) {
                    vec.add(String.valueOf(tabModel.getValueAt(i, 0)));
                }
            }
            Collections.sort(vec);
            for ( i = length-1; i >= 0 ;i-- ) {
                obj[i][0] = vec.get(i);
                obj[i][1] = "";
            }
            list.setModel(listModel);
            String [] str = { tabModel.getColumnName(0), tabModel.getColumnName(1) };
            table.setModel( new DefaultTableModel(obj,str));
            if ( ids != null ) tableRenderers.setCellToCombo(table, ids , 1);
            else {
                ids = new String[1];
                ids[0] = "";
                table.setModel( new DefaultTableModel(obj,str));
                tableRenderers.setCellToCombo(table, ids , 1);
            }
            
        }
        /**
         * Adiciona todo o conteúdo do arquivo de importação à lista.
         * @param list Lista em que o conteúdo será inserido
         */
        public void addAllContent( javax.swing.JList list ) {
            DefaultListModel listModel = new DefaultListModel();
            ArrayList<String> vec = new ArrayList<String>();
            for ( int i = titles.length-1; i > 1 ; i-- ) {
                vec.add(titles[i]);
            }
            Collections.sort(vec);
            for ( int i = vec.size()-1; i >= 0 ; i-- ) {
                listModel.addElement(vec.get(i));
            }
            list.setModel(listModel);
        }
        
    }
    
    /**
     * Verifica se existe um arquivo de importação selecionado
     * @return 
     */
    public boolean isFileAttached() {
        if ( file != null ) return true;
        else return false;
    }
}
