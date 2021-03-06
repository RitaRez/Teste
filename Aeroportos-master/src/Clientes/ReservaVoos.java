/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clientes;

import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import tp.FaltaObjetosException;
import tp.ReservaViagem;
import tp.Voo;

/**
 *
 * @author Dener
 */
public class ReservaVoos extends javax.swing.JInternalFrame {

    /**
     * Creates new form ReservaVoo
     * @param rv
     */
    public ReservaVoos(ReservaViagem rv) {
        initComponents();
        this.rv = rv;
        this.dlm1 = new DefaultListModel();
        this.dlm2 = new DefaultListModel();
        this.listaVoos = new LinkedList<>();
        
        this.jList1.setModel(dlm1);
        this.jList2.setModel(dlm2);
        
        this.adiciona.setEnabled(false);
        this.remove.setEnabled(false);
        
        this.notFound.setVisible(false);
        
        atualizaLista();
        atualizaVoosDisponiveis();
        atualizaVoosCliente();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        adiciona = new javax.swing.JButton();
        remove = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        origemTxt = new javax.swing.JTextField();
        destinoTxt = new javax.swing.JTextField();
        buscar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        notFound = new javax.swing.JLabel();
        dia = new javax.swing.JTextField();
        mes = new javax.swing.JTextField();
        ano = new javax.swing.JTextField();

        setClosable(true);
        setTitle("Reservas de voo");

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jList1);

        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jList2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jList2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jList2);

        adiciona.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cart_put.png"))); // NOI18N
        adiciona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionaActionPerformed(evt);
            }
        });

        remove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cart_remove.png"))); // NOI18N
        remove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeActionPerformed(evt);
            }
        });

        jLabel1.setText("Voos disponíveis:");

        jLabel2.setText("Seus voos:");

        buscar.setText("Buscar");
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });

        jLabel3.setText("Origem:");

        jLabel4.setText("Destino:");

        jLabel5.setText("Dia:");

        jLabel6.setText("Mês:");

        jLabel7.setText("Ano:");

        notFound.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/icons 1/error.png"))); // NOI18N
        notFound.setText("Nenhum voo encontrado");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(destinoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(327, 327, 327))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(origemTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(dia, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(mes, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(ano, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(27, 27, 27)
                                        .addComponent(buscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(notFound)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(adiciona, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(remove, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 16, Short.MAX_VALUE)))
                        .addGap(47, 47, 47))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addGap(5, 5, 5)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(origemTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ano, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(destinoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(notFound)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(adiciona)
                                .addGap(44, 44, 44)
                                .addComponent(remove)
                                .addGap(19, 19, 19)))))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void adicionaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionaActionPerformed
        
        try {
            rv.incluiReservaVoo(listaVoos.get( jList1.getSelectedIndex() ).getId() );
        } catch (IOException ex) {
            Logger.getLogger(ReservaVoos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FaltaObjetosException ex) {
            Logger.getLogger(ReservaVoos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        atualizaLista();
        atualizaVoosDisponiveis();
        atualizaVoosCliente();
        
    }//GEN-LAST:event_adicionaActionPerformed

    private void removeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeActionPerformed
        try {
            rv.removeReservaVoo( rv.getRv().get( jList2.getSelectedIndex() ).getId() );
        } catch (FaltaObjetosException ex) {
            Logger.getLogger(ReservaVoos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ReservaVoos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        atualizaLista();
        atualizaVoosDisponiveis();
        atualizaVoosCliente();
        
    }//GEN-LAST:event_removeActionPerformed

    private void jList1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList1MouseClicked
        if(jList1.getSelectedIndex() == -1 || listaVoos.isEmpty()) {
            adiciona.setEnabled(false);
        } else {
            adiciona.setEnabled(true);
        }
        
        this.notFound.setVisible(false);
        
    }//GEN-LAST:event_jList1MouseClicked

    private void jList2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jList2MouseClicked
    if(jList2.getSelectedIndex() == -1 || rv.getRv().isEmpty()) {
            remove.setEnabled(false);
        } else {
            remove.setEnabled(true);
        }   
    }//GEN-LAST:event_jList2MouseClicked

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed

        String data = dia.getText() + "/" + mes.getText() + "/" + ano.getText();
        
        if(origemTxt.getText().equals("") || destinoTxt.getText().equals("") || dia.getText().equals("") || mes.getText().equals("") || ano.getText().equals("")) {
            atualizaLista();
            atualizaVoosCliente();
            atualizaVoosDisponiveis();
            return;
        }
        
        try {
            listaVoos = Voo.obterVoo(origemTxt.getText(), destinoTxt.getText(), data);
        } catch (FaltaObjetosException ex) {
            Logger.getLogger(ReservaVoos.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if(listaVoos.isEmpty()) {
            this.notFound.setVisible(true);
        } else {
            this.notFound.setVisible(false);
        }
        
        atualizaVoosDisponiveis();
        atualizaVoosCliente();
        
    }//GEN-LAST:event_buscarActionPerformed
    
    private void atualizaLista() {
        listaVoos.removeAll(listaVoos);
        listaVoos.addAll(Voo.lista_voo.values());
    }
    
    private void atualizaVoosDisponiveis() {
        
        dlm1.removeAllElements();
        listaVoos.forEach((v) -> { 
            if(v.getVagas() != 0)
                dlm1.addElement( String.valueOf( v.getId() ) + " - " + v.getDataPartida() );
        });
    }
    
    private void atualizaVoosCliente() {
        
        dlm2.removeAllElements();
        rv.getRv().forEach((r) -> {
            dlm2.addElement( String.valueOf( r.getV().getId() ) + " - " + r.getV().getDataPartida() );
        });
    }
    
       

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adiciona;
    private javax.swing.JTextField ano;
    private javax.swing.JButton buscar;
    private javax.swing.JTextField destinoTxt;
    private javax.swing.JTextField dia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField mes;
    private javax.swing.JLabel notFound;
    private javax.swing.JTextField origemTxt;
    private javax.swing.JButton remove;
    // End of variables declaration//GEN-END:variables
    private ReservaViagem rv;
    private final DefaultListModel dlm1;
    private final DefaultListModel dlm2;
    private LinkedList<Voo> listaVoos;
}
