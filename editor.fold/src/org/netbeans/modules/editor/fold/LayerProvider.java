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

package org.netbeans.modules.editor.fold;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import javax.swing.text.EditorKit;
import javax.swing.text.JTextComponent;
import org.netbeans.api.editor.fold.FoldHierarchy;
import org.netbeans.api.editor.mimelookup.MimeLookup;
import org.netbeans.api.editor.mimelookup.MimePath;
import org.netbeans.spi.editor.fold.FoldHierarchyMonitor;
import org.netbeans.spi.editor.fold.FoldManagerFactory;

/**
 * Fold manager factory provider that obtains the factories
 * by reading the xml layer.
 * <br>
 * The registration are read from the following folder in the system FS:
 * <pre>
 *     Editors/&lt;mime-type&gt;/FoldManager
 * </pre>
 * For example java fold manager factories should be registered in
 * <pre>
 *     Editors/text/x-java/FoldManager
 * </pre>
 *

 *
 * @author Miloslav Metelka, Martin Roskanin
 */
class LayerProvider extends FoldManagerFactoryProvider {

    private static final String FOLDER_NAME = "FoldManager"; //NOI18N
    
    private Map kit2factoryList;
    
    LayerProvider() {
        kit2factoryList = new WeakHashMap();
    }

    public List getFactoryList(FoldHierarchy hierarchy) {
        List factoryList = null; // result
        JTextComponent editorComponent = hierarchy.getComponent();
        EditorKit kit = editorComponent.getUI().getEditorKit(editorComponent);
        if (kit != null) {
            factoryList = (List)kit2factoryList.get(kit);
            if (factoryList == null) { // not cached yet
                String mimeType = kit.getContentType();
                if (mimeType != null) {
                    MimePath mimePath = MimePath.parse(mimeType);
                    factoryList = new ArrayList(MimeLookup.getLookup(mimePath).lookupAll(FoldManagerFactory.class));
                }
            } // not yet cached
        }
        
        if (factoryList == null) {
            return Collections.EMPTY_LIST;
        }
        return factoryList;
    }
    
}
