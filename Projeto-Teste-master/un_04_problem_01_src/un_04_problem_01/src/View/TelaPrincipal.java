package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.Contato;
import model.ContatoDAOImpl;

public class TelaPrincipal extends JFrame{
    static String nomeUsuario;
    
    public TelaPrincipal(String nomeUsuario){
        this.nomeUsuario = nomeUsuario;
        initComponents();
    }
    
    private javax.swing.JButton jbBuscarContatos;
    private javax.swing.JButton jbListarContatos;
    private javax.swing.JButton jbCriarContato;
    private javax.swing.JLabel jlTitulo;
    private JPanel conteiner;
    
    @SuppressWarnings("unchecked")
    private void initComponents() {
    	
        jbBuscarContatos = new JButton("Buscar Contato");
        jlTitulo = new JLabel("Opções básicas da agenda de contatos.");
        jbListarContatos= new JButton("Listar Contatos");
        jbCriarContato = new JButton("Criar Contatos");
        conteiner = new  JPanel();
      
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        jbBuscarContatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                TelaBusca tb = new TelaBusca(nomeUsuario);
                tb.aparecerTela();

            }
        });
        
        jbListarContatos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               ContatoDAOImpl cdi = new ContatoDAOImpl("data_u"+nomeUsuario+".txt");
               List<Contato> contatos = cdi.listarTodosContatos();
                System.out.println(contatos.size());
                
                setVisible(false);
                TelaListagem tl = new TelaListagem(nomeUsuario, contatos);
                tl.aparecerTela();
                tl.aprovado = true;
            }
        });
        
        jbCriarContato.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                TelaCadastro tc = new TelaCadastro(nomeUsuario);
                tc.setVisible(true);
           }
        });
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jbListarContatos, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbBuscarContatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jbCriarContato, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addComponent(jlTitulo)
                .addGap(58, 58, 58))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlTitulo)
                .addGap(38, 38, 38)
                .addComponent(jbBuscarContatos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jbListarContatos)
                .addGap(18, 18, 18)
                .addComponent(jbCriarContato)
                .addContainerGap(107, Short.MAX_VALUE))
        );
        
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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaPrincipal(nomeUsuario).setVisible(true);
            }
        });
    }
}
