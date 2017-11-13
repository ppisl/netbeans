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

package org.netbeans.modules.db.explorer.dlg;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class ConnectionNamePanel extends javax.swing.JPanel {
    public final static String PROP_CONNECTION_NAME = "ConnectionName";

    /**
     * Creates a new form SchemaPanel
     * 
     * @param dbcon instance of DatabaseConnection object
     */
    public ConnectionNamePanel(ConnectionDialogMediator mediator, String connectionName) {
        initComponents();
        this.inputConnectionNameTextField.setText(connectionName);
        this.inputConnectionNameTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                firePropertyChange(PROP_CONNECTION_NAME, null, getConntionName());
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                firePropertyChange(PROP_CONNECTION_NAME, null, getConntionName());
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                firePropertyChange(PROP_CONNECTION_NAME, null, getConntionName());
            }
        });
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        commentTextArea = new javax.swing.JTextArea();
        inputConnectionNameLabel = new javax.swing.JLabel();
        inputConnectionNameTextField = new javax.swing.JTextField();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));

        setMinimumSize(new java.awt.Dimension(174, 163));
        setPreferredSize(new java.awt.Dimension(174, 163));
        setLayout(new java.awt.GridBagLayout());

        commentTextArea.setEditable(false);
        commentTextArea.setFont(javax.swing.UIManager.getFont("Label.font"));
        commentTextArea.setLineWrap(true);
        commentTextArea.setText(org.openide.util.NbBundle.getMessage(ConnectionNamePanel.class, "MSG_ConnectionNamePanelComment")); // NOI18N
        commentTextArea.setWrapStyleWord(true);
        commentTextArea.setDisabledTextColor(javax.swing.UIManager.getColor("Label.foreground"));
        commentTextArea.setEnabled(false);
        commentTextArea.setOpaque(false);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(12, 12, 0, 11);
        add(commentTextArea, gridBagConstraints);

        inputConnectionNameLabel.setLabelFor(inputConnectionNameTextField);
        org.openide.awt.Mnemonics.setLocalizedText(inputConnectionNameLabel, org.openide.util.NbBundle.getMessage(ConnectionNamePanel.class, "ConnectionNameDialogText")); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 12, 5, 12);
        add(inputConnectionNameLabel, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 12, 5, 12);
        add(inputConnectionNameTextField, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.weighty = 1.0;
        add(filler1, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weighty = 1.0;
        add(filler2, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea commentTextArea;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler2;
    private javax.swing.JLabel inputConnectionNameLabel;
    private javax.swing.JTextField inputConnectionNameTextField;
    // End of variables declaration//GEN-END:variables

    public String getConntionName() {
        return this.inputConnectionNameTextField.getText();
    }
    
    public void setConnectionName(String connectionName) {
        this.inputConnectionNameTextField.setText(connectionName);
    }
}
