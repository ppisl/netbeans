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

package org.netbeans.modules.db.explorer.sql.editor;

import org.netbeans.api.db.explorer.DatabaseConnection;
import org.netbeans.modules.db.spi.sql.editor.SQLEditorProvider;
import org.openide.util.Lookup;

/**
 *
 * @author Andrei Badea
 */
public class SQLEditorSupport {

    public static void openSQLEditor(DatabaseConnection dbconn, String sql, boolean execute) {
        SQLEditorProvider provider = (SQLEditorProvider)Lookup.getDefault().lookup(SQLEditorProvider.class);
        if (provider != null) {
            provider.openSQLEditor(dbconn, sql, execute);
        }
    }
}
