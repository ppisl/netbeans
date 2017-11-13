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

package org.netbeans.modules.profiler.heapwalk.memorylint;

import java.util.ArrayDeque;
import org.netbeans.lib.profiler.heap.ArrayItemValue;
import org.netbeans.lib.profiler.heap.Field;
import org.netbeans.lib.profiler.heap.FieldValue;
import org.netbeans.lib.profiler.heap.GCRoot;
import org.netbeans.lib.profiler.heap.Heap;
import org.netbeans.lib.profiler.heap.Instance;
import org.netbeans.lib.profiler.heap.JavaClass;
import org.netbeans.lib.profiler.heap.ObjectArrayInstance;
import org.netbeans.lib.profiler.heap.ObjectFieldValue;
import org.netbeans.lib.profiler.heap.Value;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openide.util.NbBundle;


/**
 *
 * @author nenik
 */
public class Utils {
    //~ Methods ------------------------------------------------------------------------------------------------------------------

    /** Performs a check whether target object is strongly referenced from source.
     * @param source object to search path from
     * @return true is target is held by source
     */
    public static boolean isReachableFrom(Instance source, Instance target) {
        if ((source == null) || (target == null)) {
            return false;
        }

        Logger.getLogger(Utils.class.getName()).log(Level.FINE, "Utils.isReachableFrom {0}, {1}", new Object[] { source, target });

        Set<Instance> processed = new HashSet<Instance>();
        Deque<Instance> fifo = new ArrayDeque<Instance>();
        fifo.add(source);

        while (!fifo.isEmpty()) {
            if (fifo.size() > 200) {
                Logger.getLogger(Utils.class.getName()).log(Level.FINE, "overflow in isReachableFrom {0}, {1}", new Object[] { source, target });

                break;
            }

            Instance act = fifo.removeFirst();

            if (act.equals(target)) {
                return true;
            }

            //System.err.println("  processing iof " + act.getJavaClass().getName() ); 
            @SuppressWarnings("unchecked")
            List<FieldValue> outgoing = act.getFieldValues();

            for (FieldValue v : outgoing) {
                Instance neu = null;

                if (v instanceof ObjectFieldValue) {
                    Field fld = ((ObjectFieldValue) v).getField();

                    if ("referent".equals(fld.getName()) && "java.lang.ref.Reference".equals(fld.getDeclaringClass().getName())) { // NOI18N
                        continue;
                    }

                    neu = ((ObjectFieldValue) v).getInstance();
                }

                if (v instanceof ArrayItemValue) {
                    neu = ((ArrayItemValue) v).getInstance();
                }

                if (neu == null) {
                    continue;
                }

                if (processed.add(neu)) {
                    fifo.add(neu);
                }
            }
        }

        return false;
    }

    /*
       private static void printObject(Instance in, Heap heap) {
           System.err.println(in.getJavaClass().getName() + "@" + Long.toHexString(in.getInstanceId()));
           List<FieldValue> lfv = in.getFieldValues();
           for (FieldValue fv : lfv) {
               if ("object".equals(fv.getField().getType().getName()) &&
                       "char[]".equals(((ObjectFieldValue)fv).getInstance().getJavaClass().getName())) { // char[], special printout
                   ObjectFieldValue ofv = (ObjectFieldValue)fv;
                   PrimitiveArrayInstance carr = (PrimitiveArrayInstance)ofv.getInstance();
                   List<String> vals = carr.getValues();
                       StringBuilder val = new StringBuilder("'");
                   for (String v : vals) val.append(v);
                   val.append("'");
                   System.err.println("  " + fv.getField().getName() + ":" + val.toString());
               } else {
                   System.err.println("  " + fv.getField().getName() + "(" + fv.getField().getType().getName() + "):" + fv.getValue());
               }
           }
           printPath(in, heap);
           System.err.println("");
       }
    
       private static void printPath(Instance in, Heap heap) {
           String prefix = " ";
           while (in != null) {
               if (in.isGCRoot()) {
                   GCRoot root = heap.getGCRoot(in);
                   System.err.println(prefix + "<-" + in.getJavaClass().getName() + "@" + Long.toHexString(in.getInstanceId()) + " is ROOT: " + root.getKind());
                   break;
               }
    
               System.err.println(prefix + "<-" + in.getJavaClass().getName() + "@" + Long.toHexString(in.getInstanceId()));
               prefix += " ";
               in = in.getNearestGCRootPointer();
           }
       }
     */

    /** Computes object set retained by some objects.
     */
    public static Set<Instance> getRetainedSet(Collection<Instance> objSet, Heap heap) {
        Field ref = null;
        JavaClass reference = heap.getJavaClassByName("java.lang.ref.Reference"); // NOI18N

        for (Object /*Field*/ fld : reference.getFields()) {
            Field f = (Field) fld;

            if ("referent".equals(f.getName())) { // NOI18N
                ref = f;

                break;
            }
        }

        Set<Instance> results = new HashSet<Instance>();
        @SuppressWarnings("unchecked")
        Collection<GCRoot> roots = heap.getGCRoots();
        Set<Instance> marked = new HashSet<Instance>();
        Deque<Instance> fifo = new ArrayDeque<Instance>();

        for (GCRoot r : roots) {
            Instance curr = r.getInstance();

            if (!objSet.contains(curr)) {
                fifo.add(curr);
            }
        }

        while (!fifo.isEmpty()) {
            Instance curr = fifo.removeFirst();

            if (!marked.add(curr)) {
                continue;
            }

            for (Object /*FieldValue*/ val : curr.getFieldValues()) {
                FieldValue fv = (FieldValue) val;

                // skip weak references
                if (fv.getField().equals(ref)) {
                    continue;
                }

                // 
                if (val instanceof ObjectFieldValue) {
                    Instance neu = ((ObjectFieldValue) val).getInstance();

                    if ((neu != null) && !objSet.contains(neu)) {
                        fifo.add(neu);
                    }
                }
            }

            if (curr instanceof ObjectArrayInstance) {
                for (Object /*Instance*/ val : ((ObjectArrayInstance) curr).getValues()) {
                    Instance neu = (Instance) val;

                    if ((neu != null) && !objSet.contains(neu)) {
                        fifo.add(neu);
                    }
                }
            }
        }

        // now find what we can reach from 'in'
        fifo.addAll(objSet);
        results.addAll(objSet);

        while (!fifo.isEmpty()) {
            Instance curr = fifo.removeFirst();

            for (Object /*FieldValue*/ val : curr.getFieldValues()) {
                FieldValue fv = (FieldValue) val;

                // skip weak references
                if (fv.getField().equals(ref)) {
                    continue;
                }

                // 
                if (val instanceof ObjectFieldValue) {
                    Instance neu = ((ObjectFieldValue) val).getInstance();

                    if ((neu != null) && !marked.contains(neu)) {
                        if (results.add(neu)) {
                            fifo.add(neu);
                        }
                    }
                }
            }
        }

        return results;
    }

    /** Computes object set retained by some object.
     */
    public static Set<Instance> getRetainedSet(Instance in, Heap heap) {
        return getRetainedSet(Collections.singleton(in), heap);
    }

    /** Perform BFS of incomming references and find shortest one not from SDK
     */
    public static String getRootIncommingString(Instance in) {
        String temp = null;

        for (;;) {
            in = in.getNearestGCRootPointer();

            if (in == null) {
                break;
            }

            String rName = in.getJavaClass().getName();

            if (temp == null) {
                temp = "<< " + rName; // there is at least some incoming ref
            }

            if (!rName.startsWith("java.") && !rName.startsWith("javax.")) {
                return rName;
            }

            if (in.isGCRoot()) {
                break;
            }
        }

        return (temp == null) ? "unknown" : temp;
    }

    // Perform BFS of incomming references and find shortest one not from SDK
    public static String getSignificantIncommingString(Instance in) {
        Set<Instance> processed = new HashSet<Instance>();
        String temp = null;
        Deque<Instance> fifo = new ArrayDeque<Instance>();
        fifo.add(in);

        while (!fifo.isEmpty()) {
            if (fifo.size() > 10) {
                Logger.getLogger(Utils.class.getName()).log(Level.FINE, "overflow in getSignificantIncommingString({0})", new Object[] { in });

                break;
            }

            Instance act = fifo.removeFirst();
            @SuppressWarnings("unchecked")
            List<Value> incoming = act.getReferences();

            for (Value v : incoming) {
                String rName = v.getDefiningInstance().getJavaClass().getName();

                if (temp == null) {
                    temp = "<< " + rName; // there is at least some incoming ref
                }

                if (rName.startsWith("java.") || rName.startsWith("javax.")) { // NOI18N
                    Instance i = v.getDefiningInstance();

                    if (processed.add(i)) {
                        fifo.add(i);
                    }
                } else { // Bingo!

                    return rName;
                }
            }
        }

        return (temp == null) ? "unknown" : temp; // NOI18N
    }

    public static String printClass(MemoryLint context, String cls) {
        if (cls.startsWith("<< ")) { // NOI18N
            cls = cls.substring("<< ".length()); // NOI18N
        }

        if ("unknown".equals(cls)) { // NOI18N
            return NbBundle.getMessage(Utils.class, "LBL_UnknownClass");
        }

        String fullName = cls;
        String dispName = cls;
        String field = ""; // NOI18N

        // now you can wrap it with a/href to given class
        int dotIdx = cls.lastIndexOf('.');
        int colonIdx = cls.lastIndexOf(':');

        if (colonIdx == -1) {
            colonIdx = cls.lastIndexOf(';');
        }

        if (colonIdx > 0) {
            fullName = cls.substring(0, colonIdx);
            field = "." + cls.substring(colonIdx + 1);
        }

        dispName = fullName.substring(dotIdx + 1);

        return "<a href='file://class/" + fullName + "'>" + dispName + "</a>" + field; // NOI18N
    }

    public static String printInstance(Instance in) {
        String className = in.getJavaClass().getName();
        return "<a href='file://instance/" + className + "/" + in.getInstanceNumber() + "'>" + className + '#' + in.getInstanceNumber() + "</a>"; // NOI18N
//        return in.getJavaClass().getName() + '@' + Long.toHexString(in.getInstanceId()) + '#' + in.getInstanceNumber();
    }
}
