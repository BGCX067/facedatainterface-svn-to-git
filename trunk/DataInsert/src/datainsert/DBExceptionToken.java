/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datainsert;

/**
 * Classe utilizada para armazenar e apresentar informações sobre exceções
 * decorrentes de operações sobre o banco de dados. O objeto DBExceptionToken funciona 
 * da mesma forma.
 * que um objeto {@link ExceptionToken} e que um objeto {@link DataExceptionToken}
 * @author Yule Vaz
 */
public class DBExceptionToken extends DataExceptionToken {
    
    /** 
     * Contagem de exceções de queries ocorridas durante uma operação requisitada pelo usuário.
     */
    private int SQLQueryErr;
    
    /**
     * Verifica se houve uma exceção na requisição de conexão com o banco de dados.
     */
    private boolean SQLConnectionErr;
    
    /**
     * Constrói um objeto de exceções DBExceptionToken com os parâmetros inicializados.
     */
    public DBExceptionToken() {
        super();
        SQLQueryErr = 0;
        SQLConnectionErr = false;
    }
    
    /**
     * Incrementa um às exceções de queries bem como às exceções gerais {@link ExceptionToken#err}
     */
    public void addSQLQueryErr() {
        err++;
        SQLQueryErr++;
    }
    
    /**
     * Incrementa um às exceções de conexões bem como às exceções gerais {@link ExceptionToken#err}
     */
    public void someSQLConnectionErr() {
        err++;
        SQLConnectionErr = true;
    }
    
    public int getSQLQueryErrCount() {
        return SQLQueryErr;
    }
    
    public boolean isSQLConnectionErr() {
        return SQLConnectionErr;
    }
    
    @Override
    public String toString() {
        String s = super.toString();
        
        if ( SQLQueryErr > 0 )
            s += "Erros de queries: " + String.valueOf(SQLQueryErr) + "\n";
        if ( SQLConnectionErr ) s += " Problemas na conexão com o banco de dados.\n";
        return s;
        
    }
    
    @Override
    public void reset() {
        SQLQueryErr = 0;
        SQLConnectionErr = false;
    }
    
}
