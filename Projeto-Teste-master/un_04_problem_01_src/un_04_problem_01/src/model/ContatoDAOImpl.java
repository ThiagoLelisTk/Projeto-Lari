/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContatoDAOImpl implements ContatoDAO{
    
    private String nomeArquivo;
    List<Contato> contatos;
    
    public ContatoDAOImpl(String nome_do_arquivo){
        this.nomeArquivo = nome_do_arquivo;
        contatos = listarTodosContatos();
    }

    @Override
    public Contato inserir(Contato c) {
        System.out.println(contatos.size());
        contatos.add(c);       
        
        salvarModificações();
        
        return c;
    }

    @Override
    public List<Contato> buscarPorParteNome(String parteNome) {
        List<Contato> buscados = new ArrayList();
        
        for (Contato c:contatos){
            if (c.getNome().contains(parteNome)){
                buscados.add(c);
            }
        }
        
        return buscados;
    }

    @Override
    public boolean removerContato(Contato c) {
        boolean removido = false;
        int indice = 0;
        
        for(int index=0; index<contatos.size(); index++){
            Contato cont = contatos.get(index);
            if (cont.getNome().equals(c.getNome())){
                indice = index;
                removido = true;
            }
        }
        contatos.remove(indice);
        
        salvarModificações();
        
        return removido;
    }

    @Override
    public Contato atualizarContato(Contato cAnt, Contato cAtual) {
        System.out.println("Atualizar arquivo");
        int indice = 0;
        for (int index=0; index < contatos.size(); index++){
            Contato c = contatos.get(index);
            if (c.getNome().equals(cAnt.getNome())){
                indice = index;
            }
        }
        
        System.out.println(indice);
        contatos.remove(indice);
        contatos.add(cAtual);
        
        salvarModificações();
        
        return cAtual;
    }

    @Override
    public List<Contato> listarTodosContatos() {
        List<Contato> lista = new ArrayList<>();
        
        try{
            FileReader arquivo = new FileReader(nomeArquivo);
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
    
    
    private void salvarModificações (){
        FileWriter arquivo;
        try{
            arquivo = new FileWriter(nomeArquivo);
            PrintWriter gravar = new PrintWriter(arquivo);
            Collections.sort(contatos, new OrdenadorContato());
            for(Contato cont: contatos){
                String user = cont.getNome()+";"+cont.getTelefone()+";"+cont.getEmail()+";"+cont.getEndereco();
                gravar.println(user);
            }
            arquivo.close();
        }catch (IOException k){
            k.printStackTrace();
        } catch (Exception k) {
            k.printStackTrace();
        }
    }
    public String buscaPorNome(String nome){
        
        for(Contato contato : contatos){
            if(contato.getNome().equals(nome)){
                nome = "existe";
            }
        }
        
        return nome;
    }
    
    @Override
    public List<Contato> busca(Contato c) {
        List<Contato> busca = new ArrayList();
        for (Contato contato : contatos){
            if(contato.equals(c)){
                busca.add(c);
            }
        }
        return busca;
    }
        
    
}

