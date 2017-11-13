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
package org.netbeans.jellytools.actions;

import java.awt.event.KeyEvent;
import javax.swing.KeyStroke;
import org.netbeans.jellytools.Bundle;

/**
 * Used to call "Edit|Replace" main menu item,
 * "org.openide.actions.ReplaceAction" or Ctrl+H shortcut.
 *
 * @see Action
 * @author Roman Strobl
 */
public class ReplaceAction extends Action {
    // "Edit|Replace..."

    private static final String replaceMenu =
            Bundle.getStringTrimmed("org.netbeans.core.ui.resources.Bundle", "Menu/Edit")
            + "|"
            + Bundle.getStringTrimmed("org.openide.actions.Bundle", "Replace");
    private static final KeyStroke keystroke = System.getProperty("os.name").toLowerCase().indexOf("mac") > -1
            ? KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.META_MASK)
            : KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_MASK);

    /** creates new ReplaceAction instance */
    public ReplaceAction() {
        super(replaceMenu, null, "org.openide.actions.ReplaceAction", keystroke);
    }
}
