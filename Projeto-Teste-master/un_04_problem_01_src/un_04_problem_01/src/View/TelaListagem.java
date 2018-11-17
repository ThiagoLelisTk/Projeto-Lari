package View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import model.Contato;
import model.ServicoContatoImpl;

public class TelaListagem extends JFrame{
    static boolean aprovado = true;
    static String nomeUsuario;
    static List<Contato> contatos;
    
    private java.awt.List listaContatos = new java.awt.List();

    public TelaListagem(String nomeUsuario, List<Contato> contatos) {
        this.nomeUsuario = nomeUsuario;
        this.contatos = contatos;
        
        initComponents();
    }
    
    private javax.swing.JButton jbEditar;
    private javax.swing.JButton jbEcluir;
    private javax.swing.JLabel jlTitulo;
    
    ServicoContatoImpl sci;
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        sci = new ServicoContatoImpl(nomeUsuario);

        JLabel jlTitulo = new javax.swing.JLabel();
        jbEditar = new JButton("Editar");
        jbEcluir = new JButton("Excluir");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        
        listaContatos.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
            
            }
            
        });
        
        listaProcura();

    	addWindowListener(new WindowAdapter() {
    		public void windowClosing(WindowEvent arg0) {
    			TelaPrincipal tp = new  TelaPrincipal(nomeUsuario);
    			tp.aparecerTela();
    		}
    	});
        
        jbEcluir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] contato = listaContatos.getSelectedItem().split(";");
                
                Contato c = new Contato(contato[0], contato[1], contato[2], contato[3], "");
                
                boolean removido = sci.removerContato(c);
                
                if(removido == true){
                    listaContatos.remove(listaContatos.getSelectedIndex());
                }
    
            }
        });
        
        jbEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] contato = listaContatos.getSelectedItem().split(";");
                
                Contato c = new Contato(contato[0], contato[1], contato[2], contato[3], "aprovado");
                
                setVisible(false);
                TelaCadastro tc = new TelaCadastro(c);
                tc.setVisible(true);
            }
        });

        jlTitulo.setText("Contatos existentes na agenda:");
        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jbEcluir))
                    .addComponent(jlTitulo)
                    .addComponent(listaContatos, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jlTitulo)
                .addGap(22, 22, 22)
                .addComponent(listaContatos, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbEditar)
                    .addComponent(jbEcluir))
                .addContainerGap(32, Short.MAX_VALUE))
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
            java.util.logging.Logger.getLogger(TelaListagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TelaListagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TelaListagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TelaListagem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TelaListagem(nomeUsuario, contatos).setVisible(true);
            }
        });
    }
    
    public void listaProcura(){
        this.aprovado = false;
        this.listaContatos.removeAll();
        for(Contato c: contatos){
            String item = c.getNome()+";"+c.getTelefone()+";"+c.getEmail()+";"+c.getEndereco();
            this.listaContatos.add(item);
        }
    }
    
    
    
    
}
