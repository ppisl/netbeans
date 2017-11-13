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

package org.netbeans.modules.maven.repository;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import org.netbeans.modules.maven.indexer.api.QueryField;
import org.openide.DialogDescriptor;

/**
 *
 * @author  mkleint
 */
public class FindInRepoPanel extends javax.swing.JPanel implements DocumentListener {

    private DialogDescriptor dd;

    /** Creates new form FindInRepoPanel */
    public FindInRepoPanel() {
        initComponents();
        txtFind.getDocument().addDocumentListener(this);
    }
    
    List<QueryField> getQuery() {
        List<QueryField> fq = new ArrayList<QueryField>();
        String q = txtFind.getText().trim();
        String[]  splits = q.split(" "); //NOI118N
        List<String> fields = new ArrayList<String>();
        fields.add(QueryField.FIELD_GROUPID);
        fields.add(QueryField.FIELD_ARTIFACTID);
        fields.add(QueryField.FIELD_VERSION);
        if (cbName.isSelected()) {
            fields.add(QueryField.FIELD_NAME);
        }
        if (cbDescription.isSelected()) {
            fields.add(QueryField.FIELD_DESCRIPTION);
        }
        if (cbClasses.isSelected()) {
            fields.add(QueryField.FIELD_CLASSES);
        }
        for (String one : splits) {
            for (String fld : fields) {
                QueryField f = new QueryField();
                f.setField(fld);
                f.setValue(one);
                fq.add(f);
            }
        }
        return fq;
    }

    void attachDesc(DialogDescriptor dd) {
        this.dd = dd;
        checkValid();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblFind = new javax.swing.JLabel();
        txtFind = new javax.swing.JTextField();
        pnlIncludes = new javax.swing.JPanel();
        cbName = new javax.swing.JCheckBox();
        cbDescription = new javax.swing.JCheckBox();
        cbClasses = new javax.swing.JCheckBox();

        lblFind.setLabelFor(txtFind);
        org.openide.awt.Mnemonics.setLocalizedText(lblFind, org.openide.util.NbBundle.getMessage(FindInRepoPanel.class, "FindInRepoPanel.lblFind.text")); // NOI18N

        pnlIncludes.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(FindInRepoPanel.class, "FindInRepoPanel.pnlIncludes.border.title"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(cbName, org.openide.util.NbBundle.getMessage(FindInRepoPanel.class, "FindInRepoPanel.cbName.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(cbDescription, org.openide.util.NbBundle.getMessage(FindInRepoPanel.class, "FindInRepoPanel.cbDescription.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(cbClasses, org.openide.util.NbBundle.getMessage(FindInRepoPanel.class, "FindInRepoPanel.cbClasses.text")); // NOI18N

        javax.swing.GroupLayout pnlIncludesLayout = new javax.swing.GroupLayout(pnlIncludes);
        pnlIncludes.setLayout(pnlIncludesLayout);
        pnlIncludesLayout.setHorizontalGroup(
            pnlIncludesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIncludesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlIncludesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbName)
                    .addComponent(cbDescription)
                    .addComponent(cbClasses))
                .addContainerGap(359, Short.MAX_VALUE))
        );
        pnlIncludesLayout.setVerticalGroup(
            pnlIncludesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlIncludesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbDescription)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbClasses)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        cbName.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(FindInRepoPanel.class, "FindInRepoPanel.cbName.AccessibleContext.accessibleDescription")); // NOI18N
        cbDescription.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(FindInRepoPanel.class, "FindInRepoPanel.cbDescription.AccessibleContext.accessibleDescription")); // NOI18N
        cbClasses.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(FindInRepoPanel.class, "FindInRepoPanel.cbClasses.AccessibleContext.accessibleDescription")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlIncludes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblFind)
                        .addGap(18, 18, 18)
                        .addComponent(txtFind, javax.swing.GroupLayout.DEFAULT_SIZE, 429, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFind)
                    .addComponent(txtFind, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(pnlIncludes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        txtFind.getAccessibleContext().setAccessibleDescription(org.openide.util.NbBundle.getMessage(FindInRepoPanel.class, "FindInRepoPanel.txtFind.AccessibleContext.accessibleDescription")); // NOI18N
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox cbClasses;
    private javax.swing.JCheckBox cbDescription;
    private javax.swing.JCheckBox cbName;
    private javax.swing.JLabel lblFind;
    private javax.swing.JPanel pnlIncludes;
    private javax.swing.JTextField txtFind;
    // End of variables declaration//GEN-END:variables

    @Override
    public void insertUpdate(DocumentEvent arg0) {
        checkValid();
    }

    @Override
    public void removeUpdate(DocumentEvent arg0) {
        checkValid();
    }

    @Override
    public void changedUpdate(DocumentEvent arg0) {
        checkValid();
    }

    private void checkValid() {
        if (dd != null) {
            dd.setValid(txtFind.getText().trim().length() != 0);
        }
    }

}

