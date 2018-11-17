
package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOImpl implements UsuarioDAO{
    
    List<Usuario> usuariosLista;
    
    public UsuarioDAOImpl() {
        usuariosLista = listarTodosUsuarios();
    }
    
    

    @Override
    public Usuario inserir(Usuario u) {
        System.out.println(usuariosLista.size());
        usuariosLista.add(u);       
        
        salvarModificações();
        
        criarArquivo(u.getNomeUsuario());
        
        return u;
    }

    @Override
    public Usuario buscarPorNomeUsuario(String nomeUsuario) {
        
        Usuario usuario = null;
        
        for (Usuario u: usuariosLista){
            if (u.getNomeUsuario().equals(nomeUsuario)){
                usuario = u; 
            }
        }
        return usuario;
    }

    @Override
    public boolean removerUsuario(Usuario u) {
        System.out.println("Removendo usuario");
        boolean removido = false;
        int indice = 0;
        
        for(int index=0; index<usuariosLista.size(); index++){
            Usuario user = usuariosLista.get(index);
            if (user.getNomeUsuario().equals(u.getNomeUsuario())){
                indice = index;
                removido = true;
            }
        }
        usuariosLista.remove(indice);
        
        salvarModificações();
        
        return removido;
    }

    @Override
    public Usuario atualizarUsuario(Usuario uAnt, Usuario uAtual) {
        System.out.println("Atualizar arquivo4");
        int indice = 0;
        for (int index=0; index < usuariosLista.size(); index++){
            Usuario u = usuariosLista.get(index);
            if (u.getNomeUsuario().equals(uAnt.getNomeUsuario())){
                indice = index;
            }
        }
        
        usuariosLista.remove(indice);
        usuariosLista.add(uAtual);
        
        salvarModificações();
        
        return uAtual;
    }

    @Override
    public List<Usuario> listarTodosUsuarios() {
        
        List<Usuario> usuarios = new ArrayList<>();        
        
        try{
            FileReader arquivo = new FileReader("users.txt");
            BufferedReader lerarq = new BufferedReader(arquivo);
            
            String linha = lerarq.readLine();
            
            while(linha != null){
                
		String[] frase  = linha.split(";");
		String usuario = frase[0];
                String senha = frase[1];
                
                
		Usuario u = new Usuario(usuario, senha, listarContatos(usuario), "aprovado");
                
                usuarios.add(u);
                
                linha = lerarq.readLine();
            }
            
            arquivo.close();
        }catch (IOException e){
            System.out.println("ERRO!!!!!!");
            System.out.println(e.getMessage());
	}
        //throw new UnsupportedOperationException("Not supported yet.");//To change body of generated methods, choose Tools | Templates.
        
        return usuarios;
    }
    
    public void salvarModificações(){
        FileWriter arquivo;
        try{
            arquivo = new FileWriter("users.txt");
            PrintWriter gravar = new PrintWriter(arquivo);
            for(Usuario usuario: usuariosLista){
                String user = usuario.getNomeUsuario()+";"+usuario.getSenha();
                gravar.println(user);
            } 
            arquivo.close();
        }catch (IOException k){
            k.printStackTrace();
        }catch (Exception k) {
            k.printStackTrace();
        }
    }
    
    public List<Contato> listarContatos (String nome){
        List<Contato> lista = new ArrayList<>();
        
        try{
            FileReader arquivo = new FileReader("data_u"+nome+".txt");
            BufferedReader lerarq = new BufferedReader(arquivo);
            
            String linha = lerarq.readLine();
            
            while(linha != null){
                
		String[] frase  = linha.split(";");
                
		String contato = frase[0];
                String telefone = frase[1];
                String email = frase[2];
                String endereco = frase[3];
                
                Contato c = new Contato(contato, telefone, email, endereco, "");
                
                lista.add(c);
                
                linha = lerarq.readLine();
            }
            
            arquivo.close();
        }catch (IOException e){
            System.out.println("ERRO!!!!!!");
            System.out.println(e.getMessage());
	}
        
        return lista;
    }
    
    public void criarArquivo(String nome){
        FileWriter arquivo;
        try{
            arquivo = new FileWriter("data_u"+nome+".txt");
            PrintWriter gravar = new PrintWriter(arquivo);
            
            for(Contato c: listarContatos(nome)){
            
                String contato = c.getNome()+";"+c.getTelefone()+";"+c.getEmail()+";"+c.getEndereco();
                gravar.println(contato);
            } 
            arquivo.close();
        }catch (IOException k){
            k.printStackTrace();
        }catch (Exception k) {
            k.printStackTrace();
        }
        
    }
    
}
