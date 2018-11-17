
package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import model.ServicoUsuarioImpl;
import model.Usuario;

public class TelaCadastroUsuario extends JFrame{
     public TelaCadastroUsuario()  {
        initComponents();
    }
    
     ServicoUsuarioImpl sui = new ServicoUsuarioImpl("usures.txt");
     
    
    private javax.swing.JButton jbCadastrar;
    private javax.swing.JLabel jlNome;
    private javax.swing.JLabel jlSenha;
    private javax.swing.JLabel jlConfirmação;
    private javax.swing.JTextField jTxtNome;
    private javax.swing.JTextField jTxtConfirmação;
    private javax.swing.JTextField jTxtSenha;
    
    @SuppressWarnings("unchecked")
    
    private void initComponents() {

        jbCadastrar = new JButton("Cadastrar");
        jlNome = new JLabel("Nome de Usuário:");
        jlSenha = new JLabel("Senha:");
        jlConfirmação = new JLabel("Confirmação de Senha:");
        jTxtNome = new javax.swing.JTextField();
        jTxtConfirmação = new javax.swing.JTextField();
        jTxtSenha = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        jbCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuario u = new Usuario();
                
                u = sui.inserir(jTxtNome.getText(), jTxtSenha.getText(), null, jTxtConfirmação.getText());
                
                
                if(u.getAprovado().equals("aprovado")){
                    JOptionPane.showMessageDialog(null, "Usuario Novo Criado", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                    TelaLogin tl= new TelaLogin();
                    tl.aparecerTela();
                }else if(u.getAprovado().equals("senhas nao coferem")){
                    JOptionPane.showMessageDialog(null, "As Senhas Não Conferem", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    jTxtSenha.setText("");
                    jTxtConfirmação.setText("");
                }else if(u.getAprovado().equals("nome invalido")){
                    JOptionPane.showMessageDialog(null, "Nome com Caractere Invalidos (!@#$%&*(){}/?<>+.,:;)", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    jTxtNome.setText("");
                }else if(u.getAprovado().equals("nome tamanho")){
                    JOptionPane.showMessageDialog(null, "Nome com Tamanho Invalido", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    jTxtNome.setText("");
                }else if(u.getAprovado().equals("senha invalido")){
                    JOptionPane.showMessageDialog(null, "Senha com Caractere Invalidos (!@#$%&*(){}/?<>+.,:;)", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    jTxtSenha.setText("");
                    jTxtConfirmação.setText("");
                }else if(u.getAprovado().equals("senha tamanho")){
                    JOptionPane.showMessageDialog(null, "Senha com Tamanho Invalido", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    jTxtSenha.setText("");
                    jTxtConfirmação.setText("");
                }else if(u.getAprovado().equals("usuario existe")){
                    JOptionPane.showMessageDialog(null, "Usuário já existente no sistema", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    jTxtSenha.setText("");
                    jTxtNome.setText("");
                    jTxtConfirmação.setText("");
                    
                }
                    
                
            }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jlSenha)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTxtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jlNome)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jlConfirmação)
                            .addGap(49, 49, 49)
                            .addComponent(jTxtConfirmação, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jbCadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlNome)
                    .addComponent(jTxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jlSenha)
                    .addComponent(jTxtSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jlConfirmação)
                    .addComponent(jTxtConfirmação, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbCadastrar)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }
        
}
