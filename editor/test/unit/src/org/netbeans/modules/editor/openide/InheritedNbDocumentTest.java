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

package org.netbeans.modules.editor.openide;

import javax.swing.text.StyledDocument;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.netbeans.junit.NbTestSuite;
import org.netbeans.modules.editor.NbEditorDocument;
import org.netbeans.modules.editor.NbEditorKit;
import org.openide.text.*;

/**
 *
 * @author mmetelka
 */
public class InheritedNbDocumentTest extends NbDocumentTest {

    public static Test suite() {
        TestSuite suite = new NbTestSuite(InheritedNbDocumentTest.class);
        return suite;
    }
    
    /** Creates a new instance of InheritedUndoRedoTest */
    public InheritedNbDocumentTest(String methodName) {
        super(methodName);
    }
    
    protected StyledDocument createStyledDocument() {
        return new NbEditorDocument(NbEditorKit.class);
    }

}
