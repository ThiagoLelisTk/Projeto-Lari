/* Interface que modela usuários da Agenda. */
package model;

import java.util.List;

public class Usuario {
    private String nomeUsuario;
    private String senha;
    List<Contato> contatos;
    String aprovado;
    
    public Usuario(){}
    
    public Usuario(String nome, String senha, List<Contato> contatos, String aprovado){
        this.nomeUsuario = nome;
        this.senha = senha;
        this.contatos = contatos;
        this.aprovado = aprovado;
    }

    public String getAprovado() {
        return aprovado;
    }

    public void setAprovado(String aprovado) {
        this.aprovado = aprovado;
    }
    
    public String getNomeUsuario() {
        return nomeUsuario;
    }

    public void setNomeUsuario(String nomeUsuario) {
        this.nomeUsuario = nomeUsuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Contato> getContatos() {
        return contatos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }
    
        
    /* Implementar a versão correta.*/
    @Override
    public boolean equals(Object o){
        return false;
    }
}
