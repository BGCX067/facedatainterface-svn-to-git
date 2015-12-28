/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ParseFrame.java
 *
 * Created on 06/03/2012, 17:01:41
 */
package datainsert.frames;

import datainsert.controlers.ListUtils;
import datainsert.controlers.ListenersControler;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Interface para a seleção de máscara de divisão de dados
 * @author Yule Vaz
 */
public class ParseFrame extends javax.swing.JFrame {

    /** Creates new form ParseFrame */
    private MainFrame frame;
    
    /**
     * Constrói esta interface
     * @param frame Interface principal do aplicativo
     */
    public ParseFrame(MainFrame frame) {
        this.frame = frame;
        initComponents();
        ActionListener act = new ParseActuator();
        ListenersControler.addButtonActionListener(act, jbtAdd);
        ListenersControler.addButtonActionListener(act, jbtRemove);
        for ( int i = 0; i < frame.getMask().get().size(); i++ ) {
            if (frame.getMask().get(i) == '\t')
                ListUtils.addContent(jlstMask, "<TAB>");
            else 
                ListUtils.addContent(jlstMask, "'" + frame.getMask().get(i) + "'");
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jbtAdd = new javax.swing.JButton();
        jbtRemove = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jlstMask = new javax.swing.JList();
        jftxtMask = new javax.swing.JFormattedTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Máscara");

        jbtAdd.setText("Adicionar");

        jbtRemove.setText("Remover");

        jScrollPane1.setViewportView(jlstMask);

        try {
            jftxtMask.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("*")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }

        jLabel2.setText("<TAB> => utilize o caractere ' / '");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jbtRemove, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jftxtMask, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                                .addComponent(jbtAdd, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jftxtMask, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtAdd)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbtRemove))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 98, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addComponent(jLabel2)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbtAdd;
    private javax.swing.JButton jbtRemove;
    private javax.swing.JFormattedTextField jftxtMask;
    private javax.swing.JList jlstMask;
    // End of variables declaration//GEN-END:variables

    /**
     * Ouvinte desta interface. Detecta eventos ocasionados pelo usuário e executa operações
     * dependendo de tais eventos
     */
    class ParseActuator implements ActionListener {

        /**
         * Executa uma ação dependendo do evento acionado
         * @param e Evento acionado
         */
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == jbtAdd) {
                if (jftxtMask.getText() != "") {
                    String s = jftxtMask.getText();
                    String lstItem;
                    if (s.compareTo("/") == 0) {
                            s = "\t";
                            lstItem = "<TAB>";
                    }
                    else 
                        lstItem = "'" + s.charAt(0) + "'";
                    frame.addMask(s.charAt(0));
                    ListUtils.addContent(jlstMask, lstItem);
                    jftxtMask.setText("");
                }
            }
            
            if (e.getSource() == jbtRemove) {
                if (jftxtMask.getText() != "") {
                    String rm, lstStr;
                    int [] idx = jlstMask.getSelectedIndices();
                    for ( int i = 0; i < idx.length; i++ ) {
                        lstStr = String.valueOf(jlstMask.getModel().getElementAt(idx[i]));
                        rm = lstStr.substring(1, lstStr.length()-1);
                        if (rm.compareTo("TAB") == 0)
                            rm = "\t";
                        frame.removeMask(rm.charAt(0));
                    }
                    ListUtils.removeContent(jlstMask, jlstMask.getSelectedIndices());
                    jftxtMask.setText("");
                }
            }
        }
        
    }
}
