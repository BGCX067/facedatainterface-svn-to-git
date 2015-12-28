/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datainsert.dbinterface;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que representa um canal referente aos Nós utilizados no banco de dados (seja do experimento FACE, ou Irrigap
 * , ou algum posterior) contendo o ID do canal e o código do dispositivo contido neste.
 * @author Yule Vaz
 */
public class DBCanal implements Cloneable {
    
    /**
     * ID do Canal
     */
    private int id;
    /**
     * Código alfanumérico do dispositivo
     */
    private String cod;
    
    /**
     * Constrói um objeto DBCanal com parâmetros pré determinados
     * @param id ID do canal cuja representação será construída
     * @param cod Código alfanumérico do dispositivo
     */
    private DBCanal( int id, String cod ) {
        this.id = id;
        this.cod = cod;
    }
    
    /**
     * Constrói um objeto DBCanal com ID pré determinados
     * @param id ID do canal cuja representação será construída
     */
    public DBCanal( int id ) {
        this.id = id;
        cod = null;
    }
    
    public int getId() {
        return id;
    }
    
    public String getCod() {
        return cod;
    }
    
    public void setId( int id ) {
        this.id = id;
    }
    
    public void setCod( String cod ) {
        this.cod = cod;
    }
    
    /**
     * Configura, de forma consistente com a base de dados, o código alfanumérico do dispositivo dado
     * que o id deste se encontra pré-configurado
     * @param conn Objeto de conexão com a base de dados
     * @param no Nó que possuí o canal cujo código alfanumérico (do dispositivo) da representação será configurado
     */
    public void inDatabase( Connection conn, String no ) {
        
        String [] cols = new String[1];
        cols[0] = "canal" + String.valueOf(id);
        String [] cond = new String[1];
        cond[0] = "codigofabricacao = " + "'"+no+"'";
        String query = DBInterface.createSelectQuery("configurar_no", cols, cond);
        
        try {
            
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            rs.next();
            cod = rs.getString("canal"+id);
            
        } catch (SQLException ex) {
            Logger.getLogger(DBCanal.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    /**
     * Método estático que retorna a representação de todos os canais e os respectivos dispositivos
     * de um Nó pré-determinado
     * @param conn Objeto de conexão com a base de dados
     * @param no Nó em que serão resgatados os dados sobre os canais e seus respectivos dispositivos
     * @return Representação de todos os canais e os respectivos dispositivos destes
     */
    public static DBCanal [] allFromDatabase( Connection conn, String no ) {
        
        DBCanal [] canais = new DBCanal[15];
        
        String [] cols = new String[1];
        String [] cond = new String[1];
        
        for ( int i = 0; i < 15; i++ ) {
            canais[i] = new DBCanal(i);
            canais[i].inDatabase(conn, no);
        }
        
        return canais;
        
    }
    
    /**
     * Verifica na base de dados a quantidade de canais válidos
     * @param conn Objeto de conexão com a base de dados
     * @param no Nó cujos canais válidos serão verificados
     * @return O número de canais válidos
     */
    public static int qtdValidChannels( Connection conn, String no ) {
        
        DBCanal [] canais = allFromDatabase(conn,no);
        
        int count = 0;
        
        for ( int i = 0; i < 15; i++ ) {
            if ( canais[i].getCod().compareTo("-1") != 0 ) count++;
        }
        
        return count;
        
    }
    
    /**
     * Método estático que retorna a representação de todos os canais válidos e os respectivos dispositivos
     * de um Nó pré-determinado
     * @param conn Objeto de conexão com a base de dados
     * @param no Nó em que serão resgatados os dados sobre os canais e seus respectivos dispositivos
     * @return Representação de todos os canais válidos e os respectivos dispositivos destes
     */
    public static DBCanal [] allValidFromDatabase( Connection conn, String no ) {
        
        DBCanal canal = new DBCanal(-1);
        ArrayList<DBCanal> canalArr = new ArrayList<DBCanal>();
        
        String [] cols = new String[1];
        String [] cond = new String[1];
        
        for ( int i = 0; i < 15; i++ ) {
            canal.id = i;
            canal.inDatabase(conn, no);
            if ( canal.cod.compareTo("-1") != 0 )
                canalArr.add( (DBCanal)canal.clone() );
        }
        
        DBCanal [] canais = new DBCanal[canalArr.size()];
        
        for ( int i = 0; i < canalArr.size(); i++ ) {
            canais[i] = (DBCanal) canalArr.get(i).clone();
        }
        
        return canais;
        
    }
    
    @Override
    public Object clone() {
        Object clone = new DBCanal(this.id, this.cod);
        return clone;
    }
    
    /**
     * Reseta a representacão de canal
     */
    public void reset() {
        id = -1;
        cod = "";
    }
    
}
