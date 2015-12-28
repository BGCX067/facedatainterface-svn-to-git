/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * HelpFrame.java
 *
 * Created on 02/05/2012, 17:08:45
 */
package datainsert.frames.help;

import datainsert.Reader;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkEvent.EventType;
import javax.swing.event.HyperlinkListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

/**
 *
 * @author user
 */
public class HelpFrame extends javax.swing.JFrame {

    /** Creates new form HelpFrame */
    public HelpFrame() {
        
        Icon closedIcon = new ImageIcon("plus_icon.gif");
        
        initComponents();
        
        DefaultTreeCellRenderer renderer = (DefaultTreeCellRenderer) jtrHelp.getCellRenderer();
        
        renderer.setClosedIcon(null);
        renderer.setOpenIcon(null);
        jtrHelp.addTreeSelectionListener(new TreeActuator());
        
        jedpHelp.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent hev) {
            try {
            if (hev.getEventType() == EventType.ACTIVATED) 
                jedpHelp.setPage(hev.getURL());
                jtrHelp.setSelectionPath(new TreePath(""));
            }
            catch (IOException e) {
            // Exceptions thrown...............
            }
            }});
        
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtrHelp = new javax.swing.JTree();
        jScrollPane2 = new javax.swing.JScrollPane();
        jedpHelp = new javax.swing.JEditorPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Ajuda");
        javax.swing.tree.DefaultMutableTreeNode treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Arquivo de dados");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Base de dados");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Componentes de interface");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Inserção");
        javax.swing.tree.DefaultMutableTreeNode treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Formatação de data");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Seleção de separadores");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Seleção de dispositivos");
        treeNode2.add(treeNode3);
        treeNode3 = new javax.swing.tree.DefaultMutableTreeNode("Seleção de tabela de dados");
        treeNode2.add(treeNode3);
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Remoção");
        treeNode1.add(treeNode2);
        treeNode2 = new javax.swing.tree.DefaultMutableTreeNode("Conexão");
        treeNode1.add(treeNode2);
        jtrHelp.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane1.setViewportView(jtrHelp);

        jedpHelp.setEditable(false);
        jScrollPane2.setViewportView(jedpHelp);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 710, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 862, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 862, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JEditorPane jedpHelp;
    private javax.swing.JTree jtrHelp;
    // End of variables declaration//GEN-END:variables

    class TreeActuator implements TreeSelectionListener {
        
        public void valueChanged(TreeSelectionEvent e) {
            try {
                JTree tree = (JTree)e.getSource();
                TreePath path = tree.getSelectionPath();
                if ( path != null ) {
                    if (path.getLastPathComponent().toString().compareTo("Arquivo de dados") == 0)
                        jedpHelp.setPage(this.getClass().getResource("DataFileHelp.html"));

                    if (path.getLastPathComponent().toString().compareTo("Base de dados") == 0)
                        jedpHelp.setPage(this.getClass().getResource("DatabaseHelp.html"));

                    if (path.getLastPathComponent().toString().compareTo("Componentes de interface") == 0)
                        jedpHelp.setPage(this.getClass().getResource("InterfaceHelp.html"));

                    if (path.getLastPathComponent().toString().compareTo("Inserção") == 0) {
                        jedpHelp.setPage(this.getClass().getResource("InsertHelp.html"));
                    }

                    if (path.getLastPathComponent().toString().compareTo("Formatação de data") == 0)
                        jedpHelp.setPage(this.getClass().getResource("DateFormatHelp.html"));

                    if (path.getLastPathComponent().toString().compareTo("Seleção de separadores") == 0)
                        jedpHelp.setPage(this.getClass().getResource("SeparationHelp.html"));

                    if (path.getLastPathComponent().toString().compareTo("Seleção de dispositivos") == 0)
                        jedpHelp.setPage(this.getClass().getResource("DevicesSelectionHelp.html"));

                    if (path.getLastPathComponent().toString().compareTo("Seleção de tabela de dados") == 0)
                        jedpHelp.setPage(this.getClass().getResource("DataTableHelp.html"));

                    if (path.getLastPathComponent().toString().compareTo("Remoção") == 0)
                        jedpHelp.setPage(this.getClass().getResource("RemoveHelp.html"));

                    if (path.getLastPathComponent().toString().compareTo("Conexão") == 0)
                        jedpHelp.setPage(this.getClass().getResource("ConnectionHelp.html"));
                }    
            } catch (FileNotFoundException ex) {
                Logger.getLogger(HelpFrame.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(HelpFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
    }

}