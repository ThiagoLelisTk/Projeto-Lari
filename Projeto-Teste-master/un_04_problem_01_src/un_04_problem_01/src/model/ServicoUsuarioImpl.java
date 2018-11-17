/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author fabriciogmc
 */
public class ServicoUsuarioImpl implements ServicoUsuario{
    
    UsuarioDAOImpl uDao;
    List<Usuario> usuarioLista = null;
    
    int atualizar = 0;

    public ServicoUsuarioImpl(String nome_arq_dados_u){
        uDao = new UsuarioDAOImpl();
        
        usuarioLista = listarTodosUsuarios();
        
    }
    
    @Override
    public Usuario inserir(Usuario u) {
        
        if(u.getAprovado().equals("aprovado")){
            if(atualizar == 1){
                atualizar = 0;
                return u;
            }else{
                return uDao.inserir(u);
            }
        }else{
            return u;
        }
    }
    
    

    @Override
    public Usuario buscarPorNomeUsuario(String nomeUsuario) {
        
        return uDao.buscarPorNomeUsuario(nomeUsuario);
    }

    @Override
    public boolean removerUsuario(Usuario u) {
        
        return uDao.removerUsuario(u);
    }

    @Override
    public Usuario atualizarUsuario(Usuario uAnt, Usuario uAtual) {
        atualizar = 1;
     
        uAtual.setContatos(uAnt.getContatos());
        
        uAtual = inserir(uAtual.getNomeUsuario(), uAtual.getSenha(),  uAtual.getContatos(), uAtual.getSenha());
        
        if(uAtual.getAprovado().equals("aprovado")){
            return uDao.atualizarUsuario(uAnt, uAtual);
        }else{
            return null;
        }
    
    }

    @Override
    public List<Usuario> listarTodosUsuarios() {
        
        List<Usuario> lista = uDao.listarTodosUsuarios();
        
        return lista;
    }
    
    private String verificar(String texto){
    	
    	String caracteres = "!@#$%¨&*(){}[]/?;:><., ";
    	char[] listaProibida = caracteres.toCharArray();
    	
    	if(texto.length() >= 5 && texto.length() <= 15){
    		
    		for(int i=0; i<listaProibida.length; i++){
    			for(int j=0; j<texto.length(); j++){
    				if(listaProibida[i] == texto.charAt(j)){
    					texto = "invalido";
    				}
    			}
    		}
    	}else{
    		texto = "tamanho invalido";
    	}
    	return texto;
    }

    @Override
    public Usuario inserir(String nomeUsuario, String senha, List<Contato> contatos, String confirmarSenha) {
        Usuario usuario = null;
        String verificarNome = verificar(nomeUsuario);
        String verificarSenha = verificar(senha);
        
        if(verificarNome.equals(nomeUsuario)){
            int variavel = 0;
            
            Usuario userBusca = buscarPorNomeUsuario(new Cripitografia().criptografar(nomeUsuario));
            
            if (userBusca != null){
                variavel = 1;
                usuario = new Usuario(nomeUsuario, senha, contatos, "usuario existe");
            }
            if (variavel == 0){
                if(verificarSenha.equals(senha)){
                    if (senha.equals(confirmarSenha)){
                        String nomeCriptografado = new Cripitografia().criptografar(verificarNome.toLowerCase());
                        String senhaCriptografada = new Cripitografia().criptografar(verificarSenha.toLowerCase());
                        usuario = new Usuario(nomeCriptografado, senhaCriptografada, contatos, "aprovado");
                    }else{
                        usuario = new Usuario(nomeUsuario, senha, contatos, "senhas nao coferem");
                    }
                    
                }else if(verificarSenha.equals("invalido")){
                    usuario = new Usuario(nomeUsuario, senha, contatos, "senha invalido");
                }else if(verificarSenha.equals("tamanho invalido")){
                    usuario = new Usuario(nomeUsuario, senha, contatos, "senha tamanho");
                }
                
            }
        }else if(verificarNome.equals("invalido")){
            usuario = new Usuario(nomeUsuario, senha, contatos, "nome invalido");
        }else if(verificarNome.equals("tamanho invalido")){
            usuario = new Usuario(nomeUsuario, senha, contatos, "nome tamanho");
        }
        return inserir(usuario); }

}
