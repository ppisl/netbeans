/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

/*
 * RemoveTagPanel.java
 *
 * Created on Jun 29, 2011, 3:28:53 PM
 */
package org.netbeans.modules.mercurial.ui.tag;

/**
 *
 * @author ondra
 */
public class RemoveTagPanel extends javax.swing.JPanel {

    /** Creates new form RemoveTagPanel */
    public RemoveTagPanel () {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMessage = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();

        org.openide.awt.Mnemonics.setLocalizedText(lblText, org.openide.util.NbBundle.getMessage(RemoveTagPanel.class, "RemoveTagPanel.lblText.text")); // NOI18N

        lblMessage.setLabelFor(txtMessage);
        org.openide.awt.Mnemonics.setLocalizedText(lblMessage, org.openide.util.NbBundle.getMessage(RemoveTagPanel.class, "RemoveTagPanel.lblMessage.text")); // NOI18N
        lblMessage.setToolTipText(org.openide.util.NbBundle.getMessage(RemoveTagPanel.class, "RemoveTagPanel.lblMessage.TTtext")); // NOI18N

        txtMessage.setColumns(1);
        txtMessage.setLineWrap(true);
        txtMessage.setRows(1);
        jScrollPane1.setViewportView(txtMessage);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE)
                    .addComponent(lblText)
                    .addComponent(lblMessage))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblText)
                .addGap(18, 18, 18)
                .addComponent(lblMessage)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblMessage;
    final javax.swing.JLabel lblText = new javax.swing.JLabel();
    final javax.swing.JTextArea txtMessage = new javax.swing.JTextArea();
    // End of variables declaration//GEN-END:variables
}
