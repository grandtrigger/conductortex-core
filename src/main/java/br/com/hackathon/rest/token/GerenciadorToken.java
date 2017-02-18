/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.hackathon.rest.token;

import br.com.hackathon.rest.criptografia.Encriptor;
import br.com.hackathon.rest.enumeracoes.MensagensCodigo;
import br.com.hackathon.rest.exception.TokenException;
import br.com.hackathon.rest.util.ConversorJSON;
import br.com.hackathon.rest.util.MensagensBase;
import br.com.hackathon.rest.validador.StringValidador;
import com.google.gson.JsonSyntaxException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.NoSuchPaddingException;
import javax.inject.Inject;

/**
 *
 * @author Felipe de Brito Lira <felipedebritolira@gmail.com>
 * @date 03/06/2016
 *
 */
public class GerenciadorToken {

    @Inject
    private Encriptor encriptor;
    
    @Inject
    private StringValidador stringValidador;
    
    @Inject
    private ConversorJSON<TokenEstrutura> conversorJSON;
    
    @Inject
    private MensagensBase mensagensBase;
    
    private final Long DURACAO = 5L;
    
    public GerenciadorToken() {
    }

    /**
     * Método responsável por gerar um novo token. Recebe um email
     * com o elemento para compor o token e um Long como o tempo de duração
     * deste token. Caso a duração não seja informada ou seja menor ou igual a
     * zero, por default a duração passa a ser de 5 minutos. Se o email 
     * esteja nulo ou vazio, o token será gerado com informações da
     * classe.
     *
     * @param email
     * @param duracao
     * @return String
     * @throws java.security.NoSuchAlgorithmException
     * @throws javax.crypto.NoSuchPaddingException
     */
    public String gerador(String email, Long duracao) throws NoSuchAlgorithmException, NoSuchPaddingException {
        email = stringValidador.isValid(email) ? email : GerenciadorToken.class.toString();
        TokenEstrutura token = new TokenEstrutura(email, (duracao <= 0 ? DURACAO : duracao) );
        return this.encriptor.encrypt( conversorJSON.converteJSON(token) );
    }
    
    /**
     * 
     * @param str
     * @return Boolean
     * @throws br.com.hackathon.rest.exception.TokenException
     * @throws java.security.NoSuchAlgorithmException
     * @throws javax.crypto.NoSuchPaddingException
     */
    public Boolean isTokenExpirado(String str) throws TokenException, NoSuchAlgorithmException, NoSuchPaddingException{
        if( !this.stringValidador.isValid(str) ){
            throw new TokenException( mensagensBase.get(MensagensCodigo.MS001) );
        }
        try {
            TokenEstrutura token = (TokenEstrutura)this.conversorJSON.converteObject(  new String( this.encriptor.decrypt(str) ) );
            return token.isExpirado();
        } catch (JsonSyntaxException e) {
            throw new TokenException( e );
        }
    }

    /**
     * 
     * @param str
     * @param duracao
     * @return String
     * @throws TokenException 
     * @throws java.security.NoSuchAlgorithmException 
     * @throws javax.crypto.NoSuchPaddingException 
     */
    public String atualizarToken(String str, Long duracao) throws TokenException, NoSuchAlgorithmException, NoSuchPaddingException{
        if( !this.stringValidador.isValid(str) ){
            throw new TokenException( mensagensBase.get(MensagensCodigo.MS001) );
        }
        try {
            TokenEstrutura token = (TokenEstrutura)this.conversorJSON.converteObject( new String( this.encriptor.decrypt(str) ) );
            token.setDuracaoMinutos(duracao);
            token.atualizar();
            return this.encriptor.encrypt( conversorJSON.converteJSON(token) );
        } catch (JsonSyntaxException e) {
            throw new TokenException( e );
        }
    }
    
}
