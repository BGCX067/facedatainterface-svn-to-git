/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datainsert.dbinterface;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe de representação dos dispositivos contidos na base de dados. Também efetua a busca
 * para uma configuração consistente desta representação para com a base de dados.
 * @author Yule Vaz
 */
public class DBDispositivo {
    
    /**
     * Código alfanumérico do dispositivo
     */
    private String cod;
    /**
     * Descrição do dispositivo
     */
    private String label;
    
    /**
     * Constrói uma representação vazia de um dispositivo da base de dados
     */
    public DBDispositivo() {
        cod = "";
        label = "";
    }
    
    /**
     * Constrói a representação de um dispositivo com código pré-determinado
     * @param cod Código do dispositivo que queremos representar
     */
    public DBDispositivo( String cod ) {
        this.cod = cod;
        label = "";
    }
    
    public String getCod() {
        return cod;
    }
    
    public String getLabel() {
        return label;
    }
    
    public void setCod( String cod ) {
        this.cod = cod;
    }
    
    public void setLabel( String label ) {
        this.label = label;
    }
    
    /**
     * Configura, consistentemente com a base de dados,
     * a representação de um dispositivo dado que esta possua um código pré-determinado
     * @param conn Objeto de conexão com a base de dados
     */
    public void inDatabase( Connection conn ) {
        
        if ( cod.compareTo("-1") != 0 ) {
        
            String [] cols = new String[1];
            String [] cond = new String[1];

            cols[0] = "rotulo";
            cond[0] = "codigo_fabricacao = "+"'"+cod+"'";

            String query = DBInterface.createSelectQuery("configurar_dispositivo", cols, cond);

            try {
                Statement stmt = conn.createStatement(
                        ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery(query);
                rs.beforeFirst();
                rs.next();
                label = rs.getString("rotulo");
            } catch (SQLException ex) {
                Logger.getLogger(DBDispositivo.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        else label = "Dispositivo não configurado";
        
    }
    
    /**
     * Reseta os atributos da representação do dispositivo
     */
    public void reset() {
        cod = "";
        label = "";
    }
    
}
