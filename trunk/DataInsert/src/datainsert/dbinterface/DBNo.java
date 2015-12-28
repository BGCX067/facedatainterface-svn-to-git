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
 * Classe de representação da entidade Nó da base de dados
 * @author Yule Vaz
 */
public class DBNo {
    
    /**
     * ID único do nó
     */
    private int id;
    /**
     * Código alfanumérico do nó
     */
    private String cod;
    /**
     * Tabela referente aos dados adquiridos pelo nó
     */
    private String table;
    
    /**
     * Constrói um nó de configuracão vazia
     */
    public DBNo() {
        id = -1;
        cod = null;
        table = null;
    }
    
    /**
     * Constrói um nó pre-configurando seu id com o parâmetro de entrada
     * @param id ID único do nó
     */
    public DBNo( int id ) {
        this.id = id;
        cod = null;
        table = null;
    }
    
    /**
     * Constrói um nó pré-configurando seu id, código e tabela de dados com os parâmetros de entrada
     * @param id ID único do nó
     * @param cod Código alfanumérico do nó
     * @param table Tabela referente aos dados adquiridos pelo nó
     */
    public DBNo( int id, String cod, String table ) {
        this.id = id;
        this.cod = cod;
        this.table = table;
    }
    
    public int getId() {
        return id;
    }
    
    public String getCod() {
        return cod;
    }
    
    public String getTable() {
        return table;
    }
    
    public void setId( int id ) {
        this.id = id;
    }
    
    public void setCod( String cod ) {
        this.cod = cod;
    }
    
    public void setTable( String table ) {
        this.table = table;
    }
    
    /**
     * Busca na base de dados um Nó de determinado ID (pré-configurado nesta representação) 
     * e configura código e tabela a partir dos dados da base de dados
     * @param conn Objeto de conexão com a base de dados
     */
    public void inDatabase( Connection conn ) {
        if ( id != -1 ) { 
            
            String [] cols = new String[2];
            String [] cond = new String[1];
            
            cols[0] = "codigofabricacao";
            cols[1] = "nometabeladados";
            
            cond[0] = "id = " + id;
            
            String query = DBInterface.createSelectQuery("no", cols, cond);
            
            try {
                Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = stmt.executeQuery(query);
                rs.beforeFirst();
                rs.next();
                cod = rs.getString(cols[0]);
                table = rs.getString(cols[1]);
            } catch (SQLException ex) {
                Logger.getLogger(DBNo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     * Cria objetos de representação de todos os nós da base de dados
     * @param conn Objeto de conexão com a base de dados
     * @return Objetos de representação de todos os nós da base de dados
     */
    public static DBNo [] allFromDatabase( Connection conn ) {
        
        DBNo [] nos = null;
        
        String query = "SELECT id, codigofabricacao, nometabeladados" +
                       " FROM no";
        
        try {
            
            Statement stmt = conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(query);
            nos = new DBNo[DBInterface.getRowCount(rs)];
            
            while(rs.next()) {
                nos[rs.getRow()-1] = new DBNo(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DBNo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return nos;
        
    }
    
    /**
     * Reseta a representação de um nó
     */
    public void reset() {
        id = -1;
        cod = "";
        table = "";
    }
    
    public DBDispositivo getDevice( Connection conn, String canal ){
             try {
            String query = "SELECT canal" + canal + " FROM configurar_no "
                    + "WHERE UPPER(codigofabricacao) = UPPER('" + this.cod + "')";
            
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            
            DBDispositivo dev = new DBDispositivo(rs.getString(1));
            
            return dev;
        } catch (SQLException ex) {
            Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }
    
    public boolean isEmpty() {
        return (id == -1 || cod == null || table == null ||
                cod.trim().compareTo("") == 0 || table.trim().compareTo("") == 0);
    }
    
}
