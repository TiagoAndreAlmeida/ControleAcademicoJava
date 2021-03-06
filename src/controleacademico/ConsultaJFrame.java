/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controleacademico;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author tiago
 */
public class ConsultaJFrame extends javax.swing.JFrame {

    /**
     * Creates new form ConsultaJFrame
     */
    public ConsultaJFrame() {
        initComponents();
        
        loadTable("Alunos");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Controle Academico");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Alunos", "Professores", "Cursos" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(576, 584, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        String select = jComboBox1.getItemAt(jComboBox1.getSelectedIndex());
        loadTable(select);
    }//GEN-LAST:event_jComboBox1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ConsultaJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultaJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultaJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultaJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultaJFrame().setVisible(true);
            }
        });
    }
    
    private void loadTable(String select){
        switch(select){
            case "Alunos":
            try{
                String sql = "SELECT  a.mat_alu, c.nom_curso, a.dat_nasc,"
                        + " a.tot_cred, a.mgp, a.nom_alu  "
                        + "FROM alunos a, cursos c "
                        + "WHERE c.cod_curso = a.cod_curso";
                Connection con = ConnectionPostgres.getInstance().getConnection();
                ResultSet res = con.createStatement().executeQuery(sql);
                
                DefaultTableModel model = (DefaultTableModel) jTable.getModel();
                //reseta colunas e linhas da tabela se ja houver
                model.setColumnCount(0);
                model.setRowCount(0);
                //add colunas
                model.addColumn("Matricula");
                model.addColumn("Nome");
                model.addColumn("Nascimento");
                model.addColumn("Curso");
                model.addColumn("Creditos");
                model.addColumn("MGP");
                //preencher os campos da tabela com os dados vindo do DB
                while(res.next()){
                    model.addRow(new Object []{
                        res.getInt("mat_alu"),
                        res.getString("nom_alu"),
                        res.getString("dat_nasc"),
                        res.getString("nom_curso"),
                        res.getInt("tot_cred"),
                        res.getFloat("mgp")
                    });
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            break;
            
            case "Professores":
            try{
                String sql = "SELECT p.cod_prof, c.nom_curso, p.nom_prof "
                        + "FROM professores p, cursos c "
                        + "Where c.cod_curso = p.cod_curso";
                Connection con = ConnectionPostgres.getInstance().getConnection();
                ResultSet res = con.createStatement().executeQuery(sql);
                
                DefaultTableModel model = (DefaultTableModel) jTable.getModel();
                //reseta colunas e linhas da tabela se ja houver
                model.setColumnCount(0);
                model.setRowCount(0);
                //add colunas
                model.addColumn("Matricula");
                model.addColumn("Nome");
                model.addColumn("Curso");
                //preencher os campos da tabela com os dados vindo do DB
                while(res.next()){
                    model.addRow(new Object []{
                        res.getInt("cod_prof"),
                        res.getString("nom_prof"),
                        res.getString("nom_curso")
                    });
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            break;
            
            case "Cursos":
            try{
                String sql = "SELECT c.nom_curso, c.tot_cred, p.nom_prof "
                        + "FROM cursos c, professores p "
                        + "WHERE p.cod_prof = c.cod_coord";
                Connection con = ConnectionPostgres.getInstance().getConnection();
                ResultSet res = con.createStatement().executeQuery(sql);
                
                DefaultTableModel model = (DefaultTableModel) jTable.getModel();
                //reseta colunas e linhas da tabela se ja houver
                model.setColumnCount(0);
                model.setRowCount(0);
                //add colunas
                model.addColumn("Curso");
                model.addColumn("Coordenador");
                model.addColumn("Créditos");
                //preencher os campos da tabela com os dados vindo do DB
                while(res.next()){
                    model.addRow(new Object []{
                        res.getString("nom_curso"),
                        res.getString("nom_prof"),
                        res.getInt("tot_cred")
                    });
                }
            }catch(SQLException e){
                e.printStackTrace();
            }
            break;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable;
    // End of variables declaration//GEN-END:variables
}
