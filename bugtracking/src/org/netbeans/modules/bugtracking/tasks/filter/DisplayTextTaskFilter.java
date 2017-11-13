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
package org.netbeans.modules.bugtracking.tasks.filter;

import org.netbeans.modules.bugtracking.tasks.dashboard.TaskNode;

/**
 *
 * @author jpeska
 */
public class DisplayTextTaskFilter implements DashboardFilter<TaskNode> {

    private final String summaryParam;

    public DisplayTextTaskFilter(String summaryParam) {
        this.summaryParam = summaryParam;
    }

    @Override
    public boolean isInFilter(TaskNode taskNode) {
        return taskNode.getTask().getDisplayName().toLowerCase().contains(summaryParam.toLowerCase().trim());
    }

    @Override
    public boolean expandNodes() {
        return true;
    }

    @Override
    public boolean showHitCount() {
        return true;
    }
}
