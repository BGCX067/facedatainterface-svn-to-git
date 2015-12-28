/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datainsert.dbinterface;

import datainsert.DBExceptionToken;
import datainsert.DataExceptionToken;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * Classe de interface com a base de dados. Possui a interface de conexão bem como métodos de busca,
 * inserção e controle de formatação para garantir a consistência entre o aplicativo
 * e a base de dados
 * @author Yule Vaz
 */
public class DBInterface {

    /**
     * Objeto de conexão entre o aplicativo e a base de dados
     */
    private Connection conn;
    /**
     * Verifica se o aplicativo está conectado à base de dados
     */
    private boolean isConnected;
    /**
     * IP em que se encontra o banco de dados
     */
    private String ip;
    /**
     * Porta a qual o serviço de banco de dados está "escutando"
     */
    private String port;
    /**
     * Usuário registrado que acessou a base de dados
     */
    private String usr;
    /**
     * Base de dados que se encontra acessada
     */
    private String dataBase;
    
    public DBInterface() {
        conn = null;
        ip = null;
        port = null;
        usr = null;
        isConnected = false;
    }
    
    /**
     * Configura uma interface com a base de dados com dados de conexão pré-determinados
     * @param ip IP em que se encontra o banco de dados
     * @param port Porta a qual o serviço de banco de dados está "escutando"
     * @param usr Usuário registrado que está requisitando acesso à base de dados
     * @param pass Senha do respectivo usuário
     * @param dataBase Base de dados a ser acessada
     */
    public DBInterface( String ip, String port, String usr, String pass, String dataBase ) {
        
        this.ip = ip;
        this.port = port;
        this.usr = usr;
        this.dataBase = dataBase;
        this.isConnected = false;
        
        String url = "jdbc:postgresql://"+ip+":"+port+"/" + dataBase;

        try {

            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, usr, pass);
            this.isConnected = true;
            JOptionPane.showMessageDialog(null, "Conexão com banco de dados estabelecida.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Não foi possivel estabelecer a conexão ao banco de dados.");
        } catch (SQLException ex) {
                Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Não foi possivel estabelecer a conexão ao banco de dados.");
        }
        
    }
    
    /**
     * Configura uma interface com a base de dados com dados de conexão localhost default pré-determinados.
     * @param usr Usuário registrado que está requisitando acesso à base de dados
     * @param pass Senha do respectivo usuário
     * @param dataBase Base de dados a ser acessada
     */
    public DBInterface( String usr, String pass, String dataBase ) {

        conn = null;
        ip = "localhost";
        port = "5432";
        String url = "jdbc:postgresql://localhost:5432/" + dataBase;
        isConnected = false;

        try {

            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, usr, pass);
            isConnected = true;
            JOptionPane.showMessageDialog(null, "Conexão com banco de dados estabelecida.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Não foi possivel estabelecer a conexão ao banco de dados.");
        } catch (SQLException ex) {
                Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Não foi possivel estabelecer a conexão ao banco de dados.");
        }

    }
    
    public void setIp( String ip ) {
        this.ip = ip;
    }
    
    public void setPort ( String port ) {
        this.port = port;
    }
    
    public void setUser( String usr ) {
        this.usr = usr;
    }

    public void setDataBase( String dataBase ) {
        this.dataBase = dataBase;
    }
    
    public String getIp() {
        return ip;
    }
    
    public String getPort() {
        return port;
    }
    
    public String getUser() {
        return usr;
    }
    
    public String getDataBase() {
        return dataBase;
    }
    
    /**
     * Inicia a conexão com a base de dados.
     * @param ip IP em que se encontra o banco de dados
     * @param port Porta a qual o serviço de banco de dados está "escutando"
     * @param usr Usuário registrado que está requisitando acesso à base de dados
     * @param pass Senha do respectivo usuário
     * @param dataBase Base de dados a ser acessada
     */
    public void start( String ip, String port, String usr, String pass, String dataBase ) {
        
        this.ip = ip;
        this.port = port;
        this.usr = usr;
        this.dataBase = dataBase;
        
        String url = "jdbc:postgresql://"+ip+":"+port+"/" + dataBase;

        try {

            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, usr, pass);
            isConnected = true;
            JOptionPane.showMessageDialog(null, "Conexão com banco de dados estabelecida.");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Não foi possivel estabelecer a conexão ao banco de dados.");
        } catch (SQLException ex) {
                Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, "Não foi possivel estabelecer a conexão ao banco de dados.");
        }
        
    }
    
    /**
     * Fecha uma conexao com a base de dados
     */
    public void close() {
        
        try {
            if ( !conn.isClosed() ) {
                conn.close();
                isConnected = false;
                JOptionPane.showMessageDialog(null, "Conexão com banco de dados terminada.");
            }
            else JOptionPane.showMessageDialog(null, "Conexão com banco de dados inexistente.");
        } catch (SQLException ex) {
            Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public boolean isConnected() {
        return isConnected;
    }

    /**
     * 
     * @return Objeto de conexão com a base de dados 
     */
    public Connection getConn() {

        return conn;

    }
    
    /**
     * Cria uma query de seleção utilizando os parametros de entrada
     * @param tablename Nome da tabela cujos dados quer-se selecionar
     * @param cols Colunas a selecionar
     * @return Query SQL
     */
    public static String createSelectQuery( String tablename, String [] cols ) {
        
        String query = "SELECT ";
        String join = "";
        
        for ( int i = 0; i < cols.length-1; i++ ) {
            query += cols[i] + ", ";
        }
        
        query += cols[cols.length-1] + " FROM " + tablename;
        
        return query;
        
    }
    
    /**
     * Cria uma query de seleção utilizando os parametros de entrada
     * @param tablename Nome da tabela cujos dados quer-se selecionar
     * @param cols Colunas a selecionar
     * @param conditions Condições a serem verificadas na seleção
     * @return Query SQL
     */
    public static String createSelectQuery( String tablename, String [] cols, String [] conditions ) {
        
        String query = "SELECT ";
        String join = "";
        
        for ( int i = 0; i < cols.length-1; i++ ) {
            query += cols[i] + ", ";
        }
        
        query += cols[cols.length-1] + " FROM " + tablename + " WHERE ";
        
        for ( int i = 0; i < conditions.length-1; i++ ) {
            query += conditions[i] + " AND ";
        }
        
        query += conditions[conditions.length-1];
        return query;
        
    }
    
    /**
     * Cria uma query de inserção utilizando os parametros de entrada
     * @param tablename Nome da tabela cujos dados quer-se inserir
     * @param cols Colunas aonde inserir os dados
     * @param data Valores a serem inseridos
     * @return Query SQL
     */
    public static String createInsertQuery( String tablename, String [] cols, String [] data ) {
        
        String query = "INSERT INTO " + tablename + "(";
        
        for ( int i = 0; i < cols.length-1;i++ )
            query += cols[i] + ", ";
        
        query += cols[cols.length-1] + ") VALUES (";
        
        for ( int i = 0; i < data.length-1;i++ )
            query += data[i] + ", ";
        
        query += data[data.length-1] + ")";
        
        return query;
        
    }
    
    /**
     * Insere os dados em uma base dados
     * @param tablename Nome da tabela cujos dados quer-se inserir
     * @param cols Colunas aonde inserir os dados
     * @param data Dados a serem inseridos
     * @param expt Token de exceção de dados para resgatar exceções deste método ou de métodos aninhados
     */
    public void insert( String tablename, String [] cols, String [] data, DBExceptionToken expt ) {
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(createInsertQuery(tablename, cols, data));
        } catch (SQLException ex) {
            expt.addSQLQueryErr();
        }
        
    }
    
    /**
     * Não está implementado ainda
     * @param tablename
     * @param cols
     * @param data
     * @param expt
     * @return 
     */
    public String createRemoveQuery( String tablename, String [] data, DBExceptionToken expt) {
        
        String query = "DELETE FROM " + tablename;
        
        if ( data != null ) {
            if ( data.length > 0 ) {
                query += " WHERE ";
                for ( int i = 0; i < data.length-1; i++ )
                    query += data[i] + " AND ";
                
                query += data[data.length-1];
            }
        }
        
        return query;
    }
    
    /**
     * Não está implementado ainda
     * @param tablename
     * @param cols
     * @param conditions
     * @param expt 
     */
    public void remove( String tablename, String [] cond, DBExceptionToken expt ) {
        try {

                String query = createRemoveQuery(tablename,cond,expt);   
                JOptionPane.showMessageDialog(null, "REMOÇÃO EXECUTADA\n Query: " + query);
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query);

        } catch (SQLException ex) {
            Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static int getRowCount(ResultSet rs) throws SQLException  
    {  
        
       int size;
       
       rs.last();
       size = rs.getRow();
       rs.beforeFirst();  
       
       return size;  
       
    }
    
    /**
     * Formata uma data com formato definido por dd/MM/yyyy de forma que esta esteja com formato consistente em relação à base de dados
     * @param date Data a ser formatada
     * @return Data formatada 
     */
    public static String formatDate(String date) {
        String [] str = new String[3];
        String r;
        
        int j = 0;
        int i = 0;
        
        str = date.split("/");
        
        str[2] = str[2].substring(0,4);
        str[1] = str[1].substring(0,2);
        str[0] = str[0].substring(0,2);
        r = str[2] + "-" + str[1] + "-" + str[0] + " " + "00:00:00";
        return r;
        
    }
    
    /**
     * Converte a data para um valor decimal
     * @param date Data a ser convertida
     * @param format Formato da data utilizado
     * @param expt Token de exceção de dados para resgatar exceções deste método ou de métodos aninhados
     * @return 
     */
    public static String getDateNum( String date, String format, DataExceptionToken expt ) {
        
        float r = 0;
        DateFormat outform = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat formatter = DBInterface.getFormatter(date, format);
        
        try { 
            Date data1 = outform.parse("1899-01-01");  
            String d = outform.format(formatter.parse(date));
            Date data2 = outform.parse(d);
            long diff = data2.getTime() - data1.getTime();  // milisegundos
            r = diff/86400000;
        } catch (ParseException ex) {
            expt.addFormatErr();
        }
        
        return String.valueOf(r);
        
    }
    
    /**
     * Formata uma data com formato designado na entrada do método de forma que esta esteja consistente
     * com a base de dados
     * @param date Data a ser formatada
     * @param format Formato da data a ser formatada
     * @param expt Token de exceção. 
     * @return Data formatada
     */
     public static String formatDate(String date, String format, DataExceptionToken expt ) {
        
         String r = null;
         
         try {

            int j = 0;
            int i = 0;

            DateFormat formatter = new SimpleDateFormat();
            DateFormat outform = new SimpleDateFormat("yyyy-MM-dd");
            formatter = DBInterface.getFormatter(date, format); 

            Date d = formatter.parse(date);

            r = outform.format(d) + " 00:00:00";
            
        } catch (ParseException ex) {
            expt.addCorruptedData();
        }
         
         return r;
         
    }
     
    /**
      * 
      * @param date Data cujo formatador é requerido
      * @param format Formato da data
      * @return Retorna formatador de data
      */ 
    private static DateFormat getFormatter( String date, String format ) {
        
        DateFormat formatter = new SimpleDateFormat();
        
        if (format.compareTo("DD/MM/YY") == 0) {

                    if (date.contains("/")) {
                        formatter = new SimpleDateFormat("dd/MM/yy");
                    }

                    if (date.contains("-")) {
                        formatter = new SimpleDateFormat("dd-MM-yy");
                    }
                    
            } else if (format.compareTo("MM/DD/YY") == 0) {

                    if (date.contains("/")) {
                        formatter = new SimpleDateFormat("MM/dd/yy");
                    }

                    if (date.contains("-")) {
                        formatter = new SimpleDateFormat("MM-dd-yy");
                    }
                    
            } else if (format.compareTo("YY/MM/DD") == 0) {

                    if (date.contains("/")) {
                        formatter = new SimpleDateFormat("yy/MM/dd");
                    }

                    if (date.contains("-")) {
                        formatter = new SimpleDateFormat("yy-MM-dd");
                    }
                    
            } else if (format.compareTo("YYYY/MM/DD") == 0) {
                
                if (date.contains("/")) {
                    formatter = new SimpleDateFormat("yyyy/MM/dd");
                }

                if (date.contains("-")) {
                    formatter = new SimpleDateFormat("yyyy-MM-dd");
                }
                
            } else if (format.compareTo("MM/DD/YYYY") == 0) {
                
                if (date.contains("/")) {
                    formatter = new SimpleDateFormat("MM/dd/yyyy");
                }

                if (date.contains("-")) {
                    formatter = new SimpleDateFormat("MM-dd-yyyy");
                }
                
            } else {

                if (date.contains("/")) {
                    formatter = new SimpleDateFormat("dd/MM/yyyy");
                }

                if (date.contains("-")) {
                    formatter = new SimpleDateFormat("dd-MM-yyyy");
                }
                
            }
        
        return formatter;
        
    }
    
    public Date [] getDate(String tablename, DBExceptionToken expt) {
        
        try {
            String query = "SELECT data FROM " + tablename + " GROUP BY data ORDER BY data";
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(query);
            
            Date [] d = new Date[DBInterface.getRowCount(rs)];
            rs.next();
            for ( int i = 0; i < d.length; i++ ) { 
                d[i] = rs.getDate(1);
                rs.next();
            }
            
            return d;
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
        return null;
    }
    
    public String [] devicesFromDataTable(String tablename, String [] cond, DBExceptionToken expt) {
        try {
            String query = "SELECT canal_aquisicao FROM "
                    + tablename + " GROUP BY canal_aquisicao ORDER BY canal_aquisicao";
            
            if ( cond != null ) {
                if ( cond.length > 0 ) {
                    query += " WHERE ";
                    for ( int i = 0; i < cond.length-1; i++ )
                        query += cond[i] + " AND ";

                    query += cond[cond.length-1];
                }
            }
            
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(query);
            
            String [] dev = new String[getRowCount(rs)];
            rs.next();
            for ( int i = 0; i < dev.length; i++ ) { 
                dev[i] = rs.getString(1);
                rs.next();
            }

            return dev;
        } catch (SQLException ex) {
            Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
        
    }
    
 
    
    
}
