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
 * BranchPanel.java
 *
 * Created on Mar 9, 2011, 10:36:35 AM
 */
package org.netbeans.modules.git.ui.selectors;

import javax.swing.DefaultListModel;

/**
 *
 * @author Tomas Stupka
 */
public class ItemsPanel extends javax.swing.JPanel {

    /** Creates new form BranchPanel */
    public ItemsPanel() {
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

        jScrollPane1 = new javax.swing.JScrollPane();
        btnSelectNone = new javax.swing.JButton();
        btnSelectAll = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(titleLabel, org.openide.util.NbBundle.getMessage(ItemsPanel.class, "ItemsPanel.titleLabel.text")); // NOI18N
        titleLabel.setToolTipText(org.openide.util.NbBundle.getMessage(ItemsPanel.class, "ItemsPanel.titleLabel.toolTipText")); // NOI18N

        list.setModel(new DefaultListModel());
        list.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(list);

        org.openide.awt.Mnemonics.setLocalizedText(btnSelectNone, org.openide.util.NbBundle.getMessage(ItemsPanel.class, "ItemsPanel.btnSelectNone.text")); // NOI18N
        btnSelectNone.setToolTipText(org.openide.util.NbBundle.getMessage(ItemsPanel.class, "ItemsPanel.btnSelectNone.TTtext")); // NOI18N
        btnSelectNone.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(btnSelectAll, org.openide.util.NbBundle.getMessage(ItemsPanel.class, "ItemsPanel.btnSelectAll.text")); // NOI18N
        btnSelectAll.setToolTipText(org.openide.util.NbBundle.getMessage(ItemsPanel.class, "ItemsPanel.btnSelectAll.TTtext")); // NOI18N
        btnSelectAll.setEnabled(false);

        org.openide.awt.Mnemonics.setLocalizedText(btnAllowDeletes, org.openide.util.NbBundle.getMessage(ItemsPanel.class, "ItemsPanel.btnAllowDeletes.text")); // NOI18N
        btnAllowDeletes.setToolTipText(org.openide.util.NbBundle.getMessage(ItemsPanel.class, "ItemsPanel.btnAllowDeletes.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(titleLabel)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSelectAll)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnSelectNone)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAllowDeletes)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnSelectNone)
                        .addComponent(btnAllowDeletes))
                    .addComponent(btnSelectAll))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    final javax.swing.JButton btnAllowDeletes = new javax.swing.JButton();
    javax.swing.JButton btnSelectAll;
    javax.swing.JButton btnSelectNone;
    private javax.swing.JScrollPane jScrollPane1;
    final javax.swing.JList list = new javax.swing.JList();
    final javax.swing.JLabel titleLabel = new javax.swing.JLabel();
    // End of variables declaration//GEN-END:variables
}
