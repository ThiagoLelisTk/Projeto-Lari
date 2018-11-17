
package model;

import java.util.List;

/**
 *
 * @author fabriciogmc
 */
public class ServicoContatoImpl implements ServicoContato{
    ContatoDAOImpl cdi;
    UsuarioDAOImpl udi = new UsuarioDAOImpl();
    List<Contato> contatosLista;
    List<Usuario> usuarioLista;
    int atualizar = 0;
    
    public ServicoContatoImpl(String nome_arq_dados_c){
        
        String nome_do_arquivo = "data_u"+ nome_arq_dados_c+".txt";
        
        cdi = new ContatoDAOImpl(nome_do_arquivo);
        
        contatosLista = listarTodosContatos();
        
    }
    
    @Override
    public Contato inserir(Contato c) {
        System.out.println(atualizar);
        if(atualizar != 1){
            atualizar = 0;
            if(c.getAprovado().equals("aprovado")){
                return cdi.inserir(c);
            }else{
                return c;
            }
        }else{
            return c;
        }
    }

    @Override
    public Contato inserir(String nome, String tel, String email, String end) {
        Contato c = null;
        
        String verificarNome = verificarNome(nome, atualizar);
        String verificarTel = verificarTelefone(tel);
        String verificarEmail = verificarEmail(email);
        String verificarEnd = verificaEnd(end);
        
        if (verificarNome.equals(nome)){
            if (verificarTel.equals(tel)){
                if(verificarEmail.equals(email)){
                    if(verificarEnd.equals(end)){
                        c = new Contato(nome, tel, email, end, "aprovado");
                        
                    }else{
                        c = new Contato(nome, tel, email, end, "endereco invalido");
                    }
                } else{
                    c = new Contato(nome, tel, email, end, "email invalido");
                }
            }else if (verificarTel.equals("somente numero")){
                c = new Contato(nome, tel, email, end, "somente numero");
            }else if (verificarTel.equals("tamanho invalido")){
                c = new Contato(nome, tel, email, end, "tamanho invalido");
            }
            
        }else if (verificarNome.equals("caractere inadequado")){
            c = new Contato(nome, tel, email, end, "invalido");
        }else if (verificarNome.equals("nome existente")){
            c = new Contato(nome, tel, email, end, "nome existente");
        }else if (verificarNome.equals("nome vazio")){
            c = new Contato(nome, tel, email, end, "vazio");
        }
        return inserir(c);
    }
    
    @Override
    public List<Contato> buscarPorParteNome(String parteNome) {
        return buscarPorParteNome(parteNome);
    }

    @Override
    public boolean removerContato(Contato c) {
        return cdi.removerContato(c);
    }

    @Override
    public Contato atualizarContato(Contato cAnt, Contato cAtual) {;
        atualizar = 1;
        cAtual = inserir(cAtual.getNome(), cAtual.getTelefone(), cAtual.getEmail(), cAtual.getEndereco());
        
        return cdi.atualizarContato(cAnt, cAtual);
    
    }

    @Override
    public List<Contato> listarTodosContatos() {
        return cdi.listarTodosContatos();
    }
    
    public String verificarNome(String nome, int passeiaqui){
        int brack = 0;
        String listaCaractere = "!@#$%&*(){}/?<>+.,:;";
        char[] caracteres = listaCaractere.toCharArray();
        
        /*if(verificarEspaços(nome)){
            nome = "";
        }*/
        if(nome.trim().length() <= 0){
            nome = "";
        }
		
        if(nome.length() > 0){
            for(int i=0; i< nome.length(); i++){
                for(int c=0; c<caracteres.length; c++){
                    if(caracteres[c] == (nome.charAt(i))){
                        brack = 1;
                        nome =  "caractere inadequado";
                    }
                }
                if(brack == 1){
                    break;
                }else{
                    if (atualizar != 1){
                        String busca = cdi.buscaPorNome(nome);
                        if(nome != busca){
                            nome = "nome existente";
                        }
                    }
                }
            }
        } else{
            nome = "nome vazio";
        }
        return nome;
    }
    
    public String verificarTelefone(String tel){
        /*if(verificarEspaços(tel)){
            tel = "";
        }*/
        if(tel.trim().length() < 8 || tel.trim().length() > 20){
            tel="";
        }
        int numero = tel.length();
        if(numero >= 8 && numero <= 20){
            boolean aprovado = false;
            aprovado = tel.matches("\\d{"+numero+"}");
            if(aprovado != true){
                tel = "somente numero";
            }
        }else{
            tel = "tamanho invalido";
        }
        return tel;
    }
    
    public String verificarEmail(String email){
        /*if(verificarEspaços(email)){
            email = "";
        }*/
        if(email.trim().length() <= 0){
            email="";
        }
        /*String[] particao1 = email.split("@");
        if (particao1.length == 2){
            boolean numeros = particao1[0].matches("\\d{"+particao1[0].length()+"}");
            String[] particao2 = particao1[1].split("\\.");
            
            if (particao1[0].trim().length() < particao1[0].length()){
                email = "email invalido";
            }
            
            if(numeros != true){
                if(particao2.length >= 2){	
                    boolean numeros2 = particao2[0].matches("\\d{"+particao2[0].length()+"}");
                    if(numeros2 != false){
                        email = "email invalido";
                    }
                    if(verificarEspaços(particao2[0])){
                        email = "email invalido";
                    }else if (verificarEspaços(particao2[1])){
                        email = "email invalido";
                    }
                }else{
                    email = "email invalido";
                }
                if (particao1[0].matches("\\s")){
                    email = "email invalido";
                }
            }else{
                email = "email invalido";
            }
        }else{
            email = "email invalido";
        }
        */
        return email;
    }
    
    public String verificaEnd(String end){
        /*if(verificarEspaços(end)){
            end = "";
        }*/
        if(end.trim().length() < 3 || end.trim().length() > 255){
            end = "";
        }
        String[] listaProibida ={"lugar nenhum", "judas perdeu as botas", "casa da mãe joana", "num sei"};
        if (end.length() > 3 && end.length() < 255){
            for (int i=0; i<listaProibida.length; i++){
                if(end.toLowerCase().contains(listaProibida[i])){
                    end = "endereco invalido";
                }
            }
        }else{
            end = "endereco invalido";
        }
        return end;
    }
	
	public boolean verificarEspaços(String frase){
		boolean aprovado = frase.trim().length() > 0;
		return aprovado;
	}


    
}
