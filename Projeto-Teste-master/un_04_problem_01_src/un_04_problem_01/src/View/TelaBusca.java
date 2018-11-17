package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import model.Contato;
import model.ContatoDAOImpl;


public class TelaBusca extends JFrame{
    JTextField jtxtNome;
    static String nomeUsuario;
    List<Contato> buscados = new ArrayList<>();
    
    public TelaBusca(String nomeUsuario) {
        
        this.nomeUsuario = nomeUsuario;
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    private void initComponents() {
        
    	addWindowListener(new WindowAdapter() {
    		public void windowClosing(WindowEvent arg0) {
    			TelaPrincipal tp = new TelaPrincipal(nomeUsuario);
    			tp.aparecerTela();
    		}
    	});
        ContatoDAOImpl cdi = new ContatoDAOImpl("data_u"+nomeUsuario+".txt");
        
        JButton jbBusca = new JButton("Busca de Contatos");
        JLabel jlBusca = new JLabel("Busca de Contatos");
        JLabel jlNome = new JLabel("Nome ou parte do nome");
        jtxtNome = new JTextField();
        
        jbBusca.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buscados = cdi.buscarPorParteNome(jtxtNome.getText());
                    
                    if (buscados.size() == 0){
                        JOptionPane.showMessageDialog(null, "Contato Não Encotrado, realize a busca novemente", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        jtxtNome.setText("");
                    }else{
                        setVisible(false);
                        TelaListagem tl = new TelaListagem(nomeUsuario, buscados);
                        tl.setVisible(true);
                    }
                }
            });
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(jlBusca)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(132, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jlNome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jtxtNome)
                    .addComponent(jbBusca, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(124, 124, 124))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jlBusca)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jlNome)
                .addGap(18, 18, 18)
                .addComponent(jtxtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jbBusca)
                .addContainerGap(49, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(TelaBusca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaBusca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaBusca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaBusca.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaBusca(nomeUsuario).setVisible(true);
            }
        });
    }
        
}
