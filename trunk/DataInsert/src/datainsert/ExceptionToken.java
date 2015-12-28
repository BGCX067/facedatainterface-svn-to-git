/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datainsert;

/**
 * Classe utilizada para armazenar e apresentar informações sobre exceções gerais
 * decorrentes de operações do usuário. 
 * O objeto DataExceptionToken funciona da mesma forma
 * que um objeto {@link DataExceptionToken} e que um objeto {@link DBExceptionToken}
 * Um token de exceção de dados serve para resgatar exceções de um método e de seus métodos aninhados
 * @author Yule Vaz
 */
public class ExceptionToken {
    
    /** 
     * Contagem do total de exceções sobre uma certa operação
     */
    protected int err;
    
    /**
     * Erro fatal
     */
    protected boolean fatal;
    
    /**
     * Constrói um objeto de exceções ExceptionToken com os parâmetros inicializados.
     */
    public ExceptionToken() {
        fatal = false;
        err = 0;
    }

    public void setFatal() {
        fatal = true;
    }
    
    public boolean isFatal() {
        return fatal;
    }
    
    /**
     * Incrementa em um o contador de exceções genéricas
     */
    public void addErr() {
        err++;
    }
    
    public int getErrCount() {
        return err;
    }
    
    /**
     * Reinicia os parâmetros deste ExceptionToken
     */
    public void reset() {
        err = 0;
        fatal = false;
    }
    
    /**
     * Apresentação das exceções de uma operação requisitada pelo usuário.
     * @return Informação sobre as exceções de determinada operação do usuário.
     */
    @Override
    public String toString() {
        String s;
        if ( err > 0 ) s = "Erros: " + String.valueOf(err) + "\n";
        else s = "A operação foi bem sucedida.\n";
        if ( fatal ) s += "FATAL.\n";
        
        return s;
        
    }
    
}
