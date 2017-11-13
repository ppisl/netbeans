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

package org.netbeans.modules.properties;

import java.util.Comparator;

/**
 * Comparator for comparing property keys (Strings).
 *
 * @author Petr Jiricka
 */
public final class KeyComparator implements Comparator<String> {

    public int compare(String o1, String o2) {
        int res1 = String.CASE_INSENSITIVE_ORDER.compare(o1, o2);
        if (res1 != 0) {
            return res1;
        }
        return o1.compareTo(o2);
    }

}
/*
* <<Log>>
*  2    Gandalf   1.1         10/23/99 Ian Formanek    NO SEMANTIC CHANGE - Sun 
*       Microsystems Copyright in File Comment
*  1    Gandalf   1.0         9/10/99  Petr Jiricka    
* $
*/
