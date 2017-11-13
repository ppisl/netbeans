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

package org.netbeans.modules.xml.axi.datatype;

import java.util.ArrayList;
import java.util.List;
import org.netbeans.modules.xml.schema.model.Whitespace;

/**
 * This class represents NotationType. This is one of those atomic types that can
 * be used to type an Attribute or leaf Elements in AXI Model
 *
 *
 *
 * @author Ayub Khan
 */
public class NotationType extends StringBase {
    
    protected boolean hasFacets;
    
    private List<Integer> lengths;
    
    private List<Integer> minLengths;
    
    private List<Integer> maxLengths;
    
    private List<String> patterns;
    
    private List<String> enumerations;
    
    private List<Whitespace.Treatment> whitespaces;
    
    /**
     * Creates a new instance of NotationType
     */
    public NotationType() {
        super(Datatype.Kind.NOTATION);
    }
    
        /*
         * returns length (this corresponds to the value of length facet in schema)
         *
         * @return length
         */
    public List<Integer> getLengths() {
        return lengths;
    }
    
        /*
         * returns minimum length value (this corresponds to the value of minlength facet in schema)
         *
         * @return minLength
         */
    public List<Integer> getMinLengths() {
        return minLengths;
    }
    
        /*
         * returns maximum length value (this corresponds to the value of maxlength facet in schema)
         *
         * @return maxLength
         */
    public List<Integer> getMaxLengths() {
        return maxLengths;
    }
    
        /*
         * returns pattern value (this corresponds to the value of pattern facet in schema)
         *
         * @return pattern
         */
    public List<String> getPatterns() {
        return patterns;
    }
    
        /*
         * returns enumeration values (this corresponds to the values of enumeration facets in schema)
         *
         * @return enumeration
         */
    public List<String> getEnumerations() {
        return enumerations;
    }
    
        /*
         * returns whitespace value (this corresponds to the value of whitespace facet in schema)
         *
         * @return whitespaces
         */
    public List<Whitespace.Treatment> getWhiteSpaces() {
        return whitespaces;
    }
    
        /*
         * set length (this corresponds to the value of length facet in schema)
         *
         * @param length
         */
    public void addLength(int length) {
        if(lengths == null) {
            lengths = new ArrayList<Integer>(1);
            hasFacets = true;
        }
        this.lengths.add(new Integer(length));
    }
    
        /*
         * set minimum length value (this corresponds to the value of minlength facet in schema)
         *
         * @param minLength
         */
    public void addMinLength(int minLength) {
        if(minLengths == null) {
            minLengths = new ArrayList<Integer>(1);
            hasFacets = true;
        }
        this.minLengths.add(new Integer(minLength));
    }
    
        /*
         * set maximum length value (this corresponds to the value of maxlength facet in schema)
         *
         * @param maxLength
         */
    public void addMaxLength(int maxLength) {
        if(maxLengths == null) {
            maxLengths = new ArrayList<Integer>(1);
            hasFacets = true;
        }
        this.maxLengths.add(new Integer(maxLength));
    }
    
        /*
         * set pattern value (this corresponds to the value of pattern facet in schema)
         *
         * @param pattern
         */
    public void addPattern(String pattern) {
        if(patterns == null) {
            patterns = new ArrayList<String>(1);
            hasFacets = true;
        }
        this.patterns.add(pattern);
    }
    
        /*
         * returns enumeration values (this corresponds to the values of enumeration facets in schema)
         *
         * @param enumeration
         */
    public void addEnumeration(String enumeration) {
        if(enumerations == null) {
            enumerations = new ArrayList<String>(1);
            hasFacets = true;
        }
        this.enumerations.add(enumeration);
    }
    
        /*
         * set whitespace value (this corresponds to the value of whitespace facet in schema)
         *
         * @param whitespace
         */
    public void addWhitespace(Whitespace.Treatment whitespace) {
        if(whitespaces == null) {
            whitespaces = new ArrayList<Whitespace.Treatment>(1);
            hasFacets = true;
        }
        this.whitespaces.add(whitespace);
    }
    
        /*
         * set length (this corresponds to the value of length facet in schema)
         *
         * @param length
         */
    public void removeLength(int length) {
        if(lengths != null)
            lengths.remove(new Integer(length));
    }
    
        /*
         * set minimum length value (this corresponds to the value of minlength facet in schema)
         *
         * @param minLength
         */
    public void removeMinLength(int minLength) {
        if(minLengths != null)
            minLengths.remove(new Integer(minLength));
    }
    
        /*
         * set maximum length value (this corresponds to the value of maxlength facet in schema)
         *
         * @param maxLength
         */
    public void removeMaxLength(int maxLength) {
        if(maxLengths != null)
            maxLengths.remove(new Integer(maxLength));
    }
    
        /*
         * set pattern value (this corresponds to the value of pattern facet in schema)
         *
         * @param pattern
         */
    public void removePattern(String pattern) {
        if(patterns != null)
            patterns.remove(pattern);
    }
    
        /*
         * returns enumeration values (this corresponds to the values of enumeration facets in schema)
         *
         * @param enumeration
         */
    public void removeEnumeration(String enumeration) {
        if(enumerations != null)
            enumerations.remove(enumeration);
    }
    
        /*
         * set whitespace value (this corresponds to the value of whitespace facet in schema)
         *
         * @param whitespace
         */
    public void removeWhitespace(Whitespace.Treatment whitespace) {
        if(whitespaces != null)
            whitespaces.remove(whitespace);
    }
}
