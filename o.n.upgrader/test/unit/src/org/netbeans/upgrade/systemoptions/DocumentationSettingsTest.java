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

package org.netbeans.upgrade.systemoptions;

/**
 *
 * @author Jan Pokorsky
 */
public class DocumentationSettingsTest extends BasicTestForImport {

    public DocumentationSettingsTest(String testName) {
        super(testName, "org-netbeans-modules-javadoc-settings-DocumentationSettings.settings");
    }

    @Override
    public void testPreferencesNodePath() throws Exception {
        assertPreferencesNodePath("/org/netbeans/modules/javadoc");
    }

    @Override
    public void testPropertyNames() throws Exception {
        assertPropertyNames(new String[]{
            "idxSearchSort",
            "idxSearchNoHtml",
            "idxSearchSplit",
            // following are not imported as they are obsolete, see DocumentationSettingsProcessor
            "autocommentSplit",
            "autocommentModifierMask",
            "autocommentPackage",
            "autocommentErrorMask",
            "searchEngine",
            "fileSystemSettings",
        });
    }
    
    public void testIdxSearchSort() throws Exception {
        assertPropertyType("idxSearchSort", "java.lang.String");
        assertProperty("idxSearchSort", "A");
    }
    
    public void testIdxSearchNoHtml() throws Exception {
        assertPropertyType("idxSearchNoHtml", "java.lang.Boolean");
        assertProperty("idxSearchNoHtml", "false");
    }
    
    public void testIdxSearchSplit() throws Exception {
        assertPropertyType("idxSearchSplit", "java.lang.Integer");
        assertProperty("idxSearchSplit", "50");
    }
    
}
