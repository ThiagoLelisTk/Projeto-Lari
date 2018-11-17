package View;

import static View.TelaListagem.aprovado;
import static View.TelaListagem.nomeUsuario;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Contato;
import model.ContatoDAO;
import model.ContatoDAOImpl;
import model.ServicoContatoImpl;

public class TelaCadastro extends JFrame{
    
    static String nomeUsuario;
    //ServicoContatoImpl sci;       //Foi lá pra baixo em ambos os initComponents1 e initComponents2 -> jbCadastrar
    Contato contao;
    
    public TelaCadastro(String nomeUsuario){
        this.nomeUsuario = nomeUsuario;
        
        initComponents();
        
    }
    
    public TelaCadastro(Contato c){
        this.contao = c;
        initComponents2();
    }
        
    private javax.swing.JButton jbCadastrar;
    private javax.swing.JLabel jlNome;
    private javax.swing.JLabel jlTelefone;
    private javax.swing.JLabel jlEmail;
    private javax.swing.JLabel jlEndereço;
    private javax.swing.JTextField jTxtNome;
    private javax.swing.JTextField jTxtEndereço;
    private javax.swing.JTextField jTxtTelefone;
    private javax.swing.JTextField jTxtEmail;
    
    @SuppressWarnings("unchecked")
    
    private void initComponents() {
        
        addWindowListener(new WindowAdapter() {
    		public void windowClosing(WindowEvent arg0) {
    			TelaPrincipal tp = new  TelaPrincipal(nomeUsuario);
    			tp.aparecerTela();
    		}
    	});
        
        jlNome = new JLabel("Nome");
        jlTelefone = new JLabel("Telefone");
        jlEmail = new JLabel("Email");
        jlEndereço = new JLabel("Endereço");
        jTxtNome = new JTextField();
        jTxtNome.setText("aaaaa");
        jTxtEndereço = new JTextField();
        jTxtEndereço.setText("bbbbbb");
        jTxtTelefone = new JTextField();
        jTxtTelefone.setText("12345689");
        jTxtEmail = new JTextField();
        jTxtEmail.setText("aaaaaaaaaaaaa");
        jbCadastrar = new JButton("Cadastrar");
        
        jbCadastrar.addActionListener(new ActionListener() {
            ServicoContatoImpl sci = new ServicoContatoImpl(nomeUsuario);
            
                @Override
                public void actionPerformed(ActionEvent e) {
                    Contato c2 = new Contato();
                    
                    System.out.println("Vou chamar o Inserir!");
                    c2 = sci.inserir(jTxtNome.getText(), jTxtTelefone.getText(), jTxtEmail.getText(), jTxtEndereço.getText());
                    System.out.println("Passei do Inserir!");
                    
                    if(c2.getAprovado().equals("aprovado")){
                        JOptionPane.showMessageDialog(null, "Arquivo Atualizado com Sucesso!!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        setVisible(false);
                        TelaPrincipal tp = new TelaPrincipal(nomeUsuario);
                        tp.setVisible(true);
                    }else if(c2.getAprovado().equals("endereco invalido")){
                        JOptionPane.showMessageDialog(null, "Endereço Invalido", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        jTxtEndereço.setText("");  
                    }else if(c2.getAprovado().equals("email invalido")){
                        JOptionPane.showMessageDialog(null, "Email Invalido", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        jTxtEmail.setText("");  
                    }else if(c2.getAprovado().equals("somente numero")){
                        JOptionPane.showMessageDialog(null, "Somente Numeros no Telefone", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        jTxtTelefone.setText("");  
                    }else if(c2.getAprovado().equals("tamanho invalido")){
                        JOptionPane.showMessageDialog(null, "Tamanho do Telefone Invalido", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        jTxtTelefone.setText("");  
                    }else if(c2.getAprovado().equals("invalido")){
                        JOptionPane.showMessageDialog(null, "Nome com Caractere Invalidos (!@#$%&*(){}/?<>+.,:;)", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        jTxtNome.setText("");  
                    }else if(c2.getAprovado().equals("nome existente")){
                        JOptionPane.showMessageDialog(null, "Nome Já Existente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        jTxtNome.setText("");  
                    }else if(c2.getAprovado().equals("nome vazio")){
                        JOptionPane.showMessageDialog(null, "Campo Nome Vazio", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        jTxtNome.setText("");  
                    }
                }
            });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlNome)
                            .addComponent(jlEmail)
                            .addComponent(jlTelefone))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTxtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlEndereço)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbCadastrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTxtEndereço, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlNome))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlTelefone)
                            .addComponent(jTxtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlEmail)
                            .addComponent(jTxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jlEndereço))
                    .addComponent(jTxtEndereço, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jbCadastrar)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        pack();
    }
    
    @SuppressWarnings("unchecked")
    
    private void initComponents2() {
        
        addWindowListener(new WindowAdapter() {
    		public void windowClosing(WindowEvent arg0) {
    			TelaPrincipal tp = new  TelaPrincipal(nomeUsuario);
    			tp.aparecerTela();
    		}
    	});

        jTxtNome = new JTextField();
        jlNome = new JLabel("Nome");
        jlTelefone = new JLabel("Telefone");
        jlEmail = new JLabel("Email");
        jlEndereço = new JLabel("Endereço");
        jTxtEndereço = new javax.swing.JTextField(contao.getEndereco());
        jTxtTelefone = new javax.swing.JTextField(contao.getTelefone());
        jTxtEmail = new javax.swing.JTextField(contao.getEmail());
        jbCadastrar = new JButton("Cadastrar");
        
        jbCadastrar.addActionListener(new ActionListener() {
            ServicoContatoImpl sci = new ServicoContatoImpl(nomeUsuario);
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Nome digitado:"+jTxtNome.getName());
                    
                    Contato c = new Contato();
                    c = sci.atualizarContato(contao, c);
                    
                    System.out.println(c.getNome());
                    
                    if(c.getAprovado().equals("aprovado")){
                        System.out.println(contao.getNome());
                        JOptionPane.showMessageDialog(null, "Arquivo Atualizado com Sucesso!!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        setVisible(false);
                        TelaPrincipal tp = new TelaPrincipal(nomeUsuario);
                        tp.setVisible(true);
                    }else if(c.getAprovado().equals("endereco invalido")){
                        JOptionPane.showMessageDialog(null, "Endereço Invalido", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        jTxtEndereço.setText("");  
                    }else if(c.getAprovado().equals("email invalido")){
                        JOptionPane.showMessageDialog(null, "Email Invalido", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        jTxtEmail.setText("");  
                    }else if(c.getAprovado().equals("somente numero")){
                        JOptionPane.showMessageDialog(null, "Somente Numeros no Telefone", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        jTxtTelefone.setText("");  
                    }else if(c.getAprovado().equals("tamanho invalido")){
                        JOptionPane.showMessageDialog(null, "Tamanho do Telefone Invalido", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        jTxtTelefone.setText("");  
                    }else if(c.getAprovado().equals("invalido")){
                        JOptionPane.showMessageDialog(null, "Nome com Caractere Invalidos (!@#$%&*(){}/?<>+.,:;)", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        jTxtNome.setText("");  
                    }else if(c.getAprovado().equals("nome existente")){
                        JOptionPane.showMessageDialog(null, "Nome Já Existente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        jTxtNome.setText("");  
                    }else if(c.getAprovado().equals("nome vazio")){
                        JOptionPane.showMessageDialog(null, "Campo Nome Vazio", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        jTxtNome.setText("");  
                    }
                }
            });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jlNome)
                            .addComponent(jlEmail)
                            .addComponent(jlTelefone))
                        .addGap(21, 21, 21)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTxtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jlEndereço)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jbCadastrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTxtEndereço, javax.swing.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE))))
                .addContainerGap(55, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jlNome))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlTelefone)
                            .addComponent(jTxtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jlEmail)
                            .addComponent(jTxtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jlEndereço))
                    .addComponent(jTxtEndereço, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jbCadastrar)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        pack();
    }
    
   
}
