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

package org.openide.awt;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.Lookup.Provider;

final class InjectorExactlyOne extends ContextAction.Performer<Object> {
    public InjectorExactlyOne(Map fo) {
        super(fo);
    }

    @Override
    public void actionPerformed(ActionEvent ev, List<? extends Object> data, Provider everything) {
        if (data.size() != 1) {
            return;
        }
        String clazz = (String) delegate.get("injectable"); // NOI18N
        String type = (String) delegate.get("type"); // NOI18N
        ClassLoader l = Lookup.getDefault().lookup(ClassLoader.class);
        if (l == null) {
            l = Thread.currentThread().getContextClassLoader();
        }
        if (l == null) {
            l = Actions.class.getClassLoader();
        }
        try {
            Class<?> typeC = Class.forName(type, true, l);
            Class<?> clazzC = Class.forName(clazz, true, l);
            Constructor c = clazzC.getConstructor(typeC);
            ActionListener action = (ActionListener) c.newInstance(data.get(0));
            action.actionPerformed(ev);
        } catch (Exception ex) {
                Exceptions.printStackTrace(ex);
        }
    }
}
