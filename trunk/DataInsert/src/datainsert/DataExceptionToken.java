/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package datainsert;

/**
 * Classe utilizada para armazenar e apresentar informações sobre exceções
 * decorrentes de operações sobre a análise e divisão do arquivo texto a ser importado. 
 * O objeto DataExceptionToken funciona da mesma forma
 * que um objeto {@link ExceptionToken} e que um objeto {@link DBExceptionToken}
 * @author Yule Vaz
 */
public class DataExceptionToken extends ExceptionToken {
    
    /** 
     * Contagem das exceções de formatação do arquivo texto importado. 
     */
    protected int formatErr;
    
    /**
     * Contagem dos possíveis dados corrompidos dentro do texto importado.
     */
    protected int corruptedData;
    
    /**
     * Constrói um objeto de exceções DataExceptionToken com os parâmetros inicializados.
     */
    public DataExceptionToken() {
        formatErr = 0;
        corruptedData = 0;
    }
    
     /**
     * Incrementa um às exceções de formatação bem como às exceções gerais {@link ExceptionToken#err}
     */
    public void addFormatErr() {
        err++;
        formatErr++;
    }
    
    /**
     * Incrementa um às exceções de corrupção de dados bem como às exceções gerais {@link ExceptionToken#err}
     */
    public void addCorruptedData() {
        err++;
        corruptedData++;
    }
    
    public int getFormatErrCount() {
        return formatErr;
    }
    
    public int getCorruptedDataCount() {
        return corruptedData;
    }
    
    @Override
    public String toString() {
        String s;
        s = super.toString();
        if ( formatErr > 0 ) s += "Erros de formatação: " + String.valueOf(formatErr) + "\n";
        if ( corruptedData > 0 ) s += "Dados corrompidos ou incoerentes: " + String.valueOf(corruptedData) + "\n";
        return s;
    }
    
    @Override
    public void reset() {
        super.reset();
        formatErr = 0;
        corruptedData = 0;
    }
    
}
