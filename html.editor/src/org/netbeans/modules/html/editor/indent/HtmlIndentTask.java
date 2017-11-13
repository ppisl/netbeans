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

package org.netbeans.modules.html.editor.indent;

import javax.swing.text.BadLocationException;
import org.netbeans.modules.editor.indent.spi.Context;
import org.netbeans.modules.editor.indent.spi.ExtraLock;
import org.netbeans.modules.editor.indent.spi.IndentTask;
import org.openide.util.Lookup;
import org.openide.util.lookup.Lookups;

/**
 * Implementation of IndentTask for text/html mimetype.
 *
 * @author Marek Fukala
 */
public class HtmlIndentTask implements IndentTask, Lookup.Provider {

    private HtmlIndenter indenter;
    private Lookup lookup;

    HtmlIndentTask(Context context) {
        indenter = new HtmlIndenter(context);
        lookup = Lookups.singleton(indenter.createFormattingContext());
    }

    public void reindent() throws BadLocationException {
//        long st = System.currentTimeMillis();
//        getFormatter().process(context);
//        Logger.getLogger("TIMER").log(Level.FINE, "HTML Reindent",
//                    new Object[] {fo, System.currentTimeMillis() - st});
        indenter.reindent();
    }

    public ExtraLock indentLock() {
        return null;
    }

    public Lookup getLookup() {
        return lookup;
    }
    
}
