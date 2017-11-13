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

package org.netbeans.modules.templates;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.netbeans.api.templates.CreateDescriptor;
import org.netbeans.api.templates.CreateFromTemplateAttributes;
import org.netbeans.junit.MockServices;
import org.netbeans.junit.NbTestCase;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.CreateFromTemplateHandler;
import org.openide.loaders.DataFolder;
import org.openide.loaders.DataLoader;
import org.openide.loaders.DataLoaderPool;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectExistsException;
import org.openide.loaders.FileEntry;
import org.openide.loaders.MultiDataObject;
import org.openide.loaders.MultiFileLoader;
import org.openide.util.Enumerations;

/**
 *
 * @author Jaroslav Tulach
 */
public class CreateFromTemplateHandlerTest extends NbTestCase {
    
    public CreateFromTemplateHandlerTest(String testName) {
        super(testName);
    }
    
    protected boolean runInEQ() {
        return true;
    }
    
    protected void setUp() throws Exception {
        Hand.acceptObject = new ArrayList<FileObject>();
        Hand.fileObject = new ArrayList<FileObject>();
        Hand.origObject = new ArrayList<FileObject>();
        Hand.name = null;
        Hand.parameters = null;
        
        MockServices.setServices(Hand.class, Attr.class, Pool.class);
    }

    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testCreateFromTemplate() throws Exception {
        FileObject root = FileUtil.createMemoryFileSystem().getRoot();
        FileObject fo = FileUtil.createData(root, "simpleObject.txt");
        
        DataObject obj = DataObject.find(fo);
        
        DataFolder folder = DataFolder.findFolder(FileUtil.createFolder(root, "target"));
        
        Map<String,String> parameters = Collections.singletonMap("type", "empty");
        DataObject n = obj.createFromTemplate(folder, "complex", parameters);
        
        assertEquals("Created in right place", folder, n.getFolder());
        assertEquals("Created with right name", "complex.txt", n.getName());
        
        assertEquals("The right source", fo, Hand.origObject.get(0));
        assertEquals("The right source in query", fo, Hand.acceptObject.get(0));
        assertEquals("The right destiny folder", folder.getPrimaryFile(), Hand.fileObject.get(0));
        assertEquals("The right name", "complex", Hand.name);
        if (Hand.parameters.size() < 2) {
            fail("As least two: " + Hand.parameters + " but was " + Hand.parameters.size());
        }
        assertEquals("empty", Hand.parameters.get("type"));
        assertEquals("complex", Hand.parameters.get("name"));
        try {
            Hand.parameters.put("kuk", "buk");
        } catch (UnsupportedOperationException ex) {
            // ok
            return;
        }
        fail("Modifications shall be unsupported");
    }
    
    public static final class Hand extends CreateFromTemplateHandler {
        public static List<FileObject>  fileObject, origObject, acceptObject;
        public static String name;
        public static Map<String, Object> parameters;
    
        public boolean accept(FileObject fo) {
            acceptObject.add(fo);
            return true;
        }

        public FileObject createFromTemplate(
            FileObject orig, FileObject f, String n,
            Map<String, Object> p
        ) throws IOException {
            origObject.add(orig);
            fileObject.add(f);
            name = n;
            parameters = p;

            return FileUtil.copyFile(orig, f, name);
        }
    }
    
    public static final class Attr implements CreateFromTemplateAttributes {
        @Override
        public Map<String, ?> attributesFor(CreateDescriptor desc) {
            return Collections.singletonMap("name", desc.getProposedName());
        }
    }
    
    public static final class Pool extends DataLoaderPool {
        protected Enumeration<DataLoader> loaders() {
            return Enumerations.<DataLoader>array(new DataLoader[] { 
                SimpleLoader.getLoader(SimpleLoader.class),
                TwoPartLoader.getLoader(TwoPartLoader.class),
            });
        }
    }
    
    public static final class SimpleLoader extends MultiFileLoader {
        public SimpleLoader() {
            super(SimpleObject.class.getName());
        }
        protected String displayName() {
            return "SimpleLoader";
        }
        protected FileObject findPrimaryFile(FileObject fo) {
            if (fo.hasExt("prima")) {
                return fo;
            }
            return null;
        }
        protected MultiDataObject createMultiObject(FileObject primaryFile) throws DataObjectExistsException, IOException {
            return new SimpleObject(this, primaryFile);
        }
        protected MultiDataObject.Entry createPrimaryEntry(MultiDataObject obj, FileObject primaryFile) {
            return new FE(obj, primaryFile);
        }
        protected MultiDataObject.Entry createSecondaryEntry(MultiDataObject obj, FileObject secondaryFile) {
            return new FileEntry(obj, secondaryFile);
        }
    }
    
    private static final class FE extends FileEntry {
        public FE(MultiDataObject mo, FileObject fo) {
            super(mo, fo);
        }

        @Override
        public FileObject createFromTemplate(FileObject f, String name) throws IOException {
            fail("I do not want to be called");
            return null;
        }
    }
    
    public static final class SimpleObject extends MultiDataObject {
        public SimpleObject(SimpleLoader l, FileObject fo) throws DataObjectExistsException {
            super(fo, l);
        }
        
        public String getName() {
            return getPrimaryFile().getNameExt();
        }
    }
    
    

    public static final class TwoPartLoader extends MultiFileLoader {
        public TwoPartLoader() {
            super(TwoPartObject.class.getName ());
        }
        protected String displayName() {
            return "TwoPart";
        }
        protected FileObject findPrimaryFile(FileObject fo) {
            if (fo.hasExt("java") || fo.hasExt("form")) {
                return org.openide.filesystems.FileUtil.findBrother(fo, "java");
            } else {
                return null;
            }
        }
        protected MultiDataObject createMultiObject(FileObject primaryFile) throws DataObjectExistsException, IOException {
            return new TwoPartObject(this, primaryFile);
        }
        protected MultiDataObject.Entry createPrimaryEntry(MultiDataObject obj, FileObject primaryFile) {
            return new FE(obj, primaryFile);
        }
        protected MultiDataObject.Entry createSecondaryEntry(MultiDataObject obj, FileObject secondaryFile) {
            return new FE(obj, secondaryFile);
        }
    }
    public static final class TwoPartObject extends MultiDataObject {
        public TwoPartObject(TwoPartLoader l, FileObject folder) throws DataObjectExistsException {
            super(folder, l);
        }
    }
    
}
