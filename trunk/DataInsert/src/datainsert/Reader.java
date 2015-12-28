/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package datainsert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * Executa a leitura e análise de formato do arquivo a ser importado. Separa os dados de forma
 * que o aplicativo possa manipula-los
 * @author Yule Vaz
 */
public class Reader {

    /**
     * Lê um BufferedReader retirado de um arquivo texto e separa as linhas deste
     * @param buf BufferedReader retirado de um arquivo texto pré-selecionado
     * @param expt Token de exceção utilizado para contar as exceções desta operação, bem como operações
     *        encapsuladas dentro desta.
     * @return Retorna o conteúdo do arquivo texto separado em linhas, cada item do {@link java.util.ArrayList}
     *         faz referência a uma linha do arquivo texto
     */
    public static ArrayList<String> read(BufferedReader buf, ExceptionToken expt ) {

        ArrayList<String> text = new ArrayList<String>();
        String linha = "";
        int i = 0;

        try {
            while ((linha = buf.readLine()) != null) {
                text.add(linha);
                i++;
            }
        } catch (IOException ex) {
            expt.addErr();
        }

//        System.out.println("source= " + data.toString());
        return text;

    }

    /**
     * Lê uma cadeia de caracteres e separa tal cadeia com o uso de uma máscara. Neste caso o espaçamento
     * e a tabulação
     * @param s Cadeia de caracteres que será dividida
     * @return Retorna a sequência de Strings resultante desta separação
     */
    public static ArrayList<String> parse( String s ) {

        ArrayList<String> slist = new ArrayList<String>();

        char [] cvet = s.toCharArray();
        char c = ' ';
        String word = "";

        for ( int i = 0 ; i < cvet.length; i++ ) {

            word += String.valueOf(cvet[i]);

            if ( i == cvet.length-1 || ( cvet[i] == '	'  && cvet[i+1] != '	') ) {
                if ( !word.equals(" ") ) slist.add(word);
                word = "";
            }

        }

        return slist;

    }
    
    /**
     * Lê uma cadeia de caracteres e separa tal cadeia com o uso de uma máscara. Neste caso 
     * definida pelo usuário do aplicativo
     * @param s Cadeia de caracteres que será dividida
     * @param mask Máscara escolhida pelo usuário
     * @return Retorna a sequência de Strings resultante desta separação
     */
    public static ArrayList<String> parse( String s, Character [] mask ) {

        ArrayList<String> slist = new ArrayList<String>();

        char [] cvet = s.toCharArray();
        char c = ' ';
        String word = "";
        boolean stop = false;

        for ( int i = 0 ; i < cvet.length; i++ ) {

            word += String.valueOf(cvet[i]);
            stop = false;
            
            for ( int j = 0; j < mask.length && !stop; j++ ) {
                if ( i == cvet.length-1 || ( cvet[i] == mask[j].charValue()  && cvet[i+1] != '	') ) {
                    if ( !word.equals(" ") ) slist.add(word);
                    word = "";
                    stop = true;
                }
            }
        }

        return slist;

    }
    
    public static String fileToStr( String path ) throws FileNotFoundException, IOException {
        String str = new String("");
        String aux;
        BufferedReader buf = new BufferedReader( new FileReader(new File(path)) );
        
        while ((aux = buf.readLine()) != null)
            str.concat(aux);
        
        return str;
    }
    
    public static String fileToStr( URL path ) throws FileNotFoundException, IOException, URISyntaxException {
        String str = new String("");
        String aux;
        BufferedReader buf = new BufferedReader( new FileReader(new File(path.toURI().getPath())) );
        
        while ((aux = buf.readLine()) != null)
            str += "\n"+aux;
        
        return str;
    }

}
