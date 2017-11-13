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

package org.netbeans.modules.versioning.spi.testvcs;

import java.io.File;
import org.netbeans.modules.versioning.spi.VCSVisibilityQuery;

/**
 *
 * @author Tomas Stupka
 */
public class TestVCSVisibilityQuery extends VCSVisibilityQuery {

    public static final String INVISIBLE_FILE_SUFFIX = "invisible";

    @Override
    public boolean isVisible(File file) {
        return !file.getName().endsWith(INVISIBLE_FILE_SUFFIX);
    }
    
    public void visibilityChanged() {
        fireVisibilityChanged();
    }
    
    public void visibilityChanged(File... files) {
        fireVisibilityChanged(files);
    }

}
