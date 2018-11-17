
package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import model.Cripitografia;
import model.ServicoUsuarioImpl;
import model.Usuario;
import model.UsuarioDAOImpl;

public class TelaLogin extends JFrame{
    
    File file = new File("C:\\Users\\Tk\\Desktop\\Projeto-Teste-master\\un_04_problem_01_src\\un_04_problem_01\\data_uwkldjr.txt");
    private javax.swing.JLabel jlNome;
    private javax.swing.JLabel jlsenha;
    private javax.swing.JPasswordField jTxtSenha;
    private javax.swing.JTextField jTxtNome;
    private javax.swing.JToggleButton jtbOk;
    private JButton jbNovo, jbExclui;
    
    
    UsuarioDAOImpl udi = new UsuarioDAOImpl();
    ServicoUsuarioImpl sui = new ServicoUsuarioImpl("user.txt");
    
     public TelaLogin() {
         initComponents();
    }
    
  @SuppressWarnings("unchecked")
    
    private void initComponents() {
        
        jTxtNome = new javax.swing.JTextField("thiago");
        jTxtSenha = new javax.swing.JPasswordField("12345");
        jlNome = new JLabel("Nome de Usuário");
        jlsenha = new JLabel("Senha");
        jtbOk = new JToggleButton("OK");
        jbNovo = new JButton("Novo Usuário");
        jbExclui = new JButton("Excluir Usuario");
        
        jtbOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeCrip = new Cripitografia().criptografar(jTxtNome.getText());
                Usuario u = udi.buscarPorNomeUsuario(nomeCrip);
                if (u != null){
                    if (u.getSenha().equals(new Cripitografia().criptografar(jTxtSenha.getText()))){
                        TelaPrincipal tp = new TelaPrincipal(u.getNomeUsuario());
                        tp.aparecerTela();
                    }else{
                        JOptionPane.showMessageDialog(null, "Senha Incoreta", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        jTxtSenha.setText("");
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Ususario Não Encotrado, realize o Login novemente ou faça o Cadastro", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    jTxtNome.setText("");
                    jTxtSenha.setText("");
                }
            }
        });
        
        jbNovo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                TelaCadastroUsuario tcu = new TelaCadastroUsuario();
                tcu.setVisible(true);
            }
        });
        
        jbExclui.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                file.delete();
                System.out.println("Deletei arquivo;");
            }
        });
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jlNome.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        
        jlsenha.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jtbOk.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jbNovo.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18Ne
        
        jbNovo.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        jbExclui.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18Ne
        
        jbExclui.setFont(new Font("Tahoma", Font.PLAIN, 14));
        
        

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(37)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jlNome)
        				.addComponent(jlsenha))
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addComponent(jbNovo, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jbExclui, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        				.addGroup(Alignment.LEADING, layout.createParallelGroup(Alignment.TRAILING, false)
        					.addComponent(jTxtSenha, Alignment.LEADING)
        					.addComponent(jTxtNome, Alignment.LEADING)
        					.addComponent(jtbOk, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 104, Short.MAX_VALUE)))
        			.addContainerGap(105, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(70)
        			.addGroup(layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(jTxtNome, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jlNome))
        			.addPreferredGap(ComponentPlacement.UNRELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jTxtSenha, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jlsenha))
        			.addGap(18)
        			.addComponent(jtbOk)
        			.addGap(18)
        			.addComponent(jbNovo)
                                .addGap(18)
                                .addComponent(jbExclui)
        			.addContainerGap(81, Short.MAX_VALUE))
        );
        getContentPane().setLayout(layout);

        pack();
    }
    
    public void aparecerTela() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }
    
}
