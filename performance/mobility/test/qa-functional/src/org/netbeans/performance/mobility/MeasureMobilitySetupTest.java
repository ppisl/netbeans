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

package org.netbeans.performance.mobility;

import org.netbeans.junit.NbModuleSuite;
import org.netbeans.junit.NbTestSuite;
import org.netbeans.performance.mobility.setup.MobilitySetup;

/**
 * Test suite that actually does not perform any test but sets up user directory
 * for UI responsiveness tests
 *
 * @author  rkubacki@netbeans.org, mmirilovic@netbeans.org, mrkam@netbeans.org
 */
public class MeasureMobilitySetupTest extends NbTestSuite {

    public MeasureMobilitySetupTest (java.lang.String testName) {
        super(testName);
    }

    public static NbTestSuite suite() {
        NbTestSuite suite = new NbTestSuite("UI Responsiveness Setup suite for Mobility");
        System.setProperty("suitename", MeasureMobilitySetupTest.class.getCanonicalName());

        suite.addTest(NbModuleSuite.create(NbModuleSuite.createConfiguration(MobilitySetup.class)
            .enableModules(".*")
            .clusters(".*")
        ));

        return suite;
    }
    
}
