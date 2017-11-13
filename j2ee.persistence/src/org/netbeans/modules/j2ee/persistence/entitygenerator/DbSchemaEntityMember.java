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
package org.netbeans.modules.j2ee.persistence.entitygenerator;

/**
 * This class represents an instance of an member in an entity bean class which
 * is backed by a ColumnElement representing a relational database.
 * @author Christopher Webster
 */

import org.netbeans.modules.dbschema.ColumnElement;
import org.netbeans.modules.dbschema.util.SQLTypeUtil;

class DbSchemaEntityMember extends EntityMember {

   /**
    * Is this member part of primary key
    */
    private boolean isPrimaryKey;
    
    /**
     * Is its value automatically generated/incremented by the database?
     */
    private boolean isAutoIncrement;

    /**
     * Original mapping to sql type
     */
    private SQLType sqlType;
    
    /**
     * The length (only applies to character types).
     */
    private Integer length;
    
    /**
     * The precision (only applies to numeric types).
     */
    private Integer precision;
    
    /**
     * The precision (only applies to numeric types).
     */
    private Integer scale;

    /**
     * Column Element providing metadata
     */
    private ColumnElement columnElement;

    public DbSchemaEntityMember(ColumnElement element) {
        columnElement = element;
        sqlType = SQLType.getSQLType(element.getType());
        setMemberName(makeFieldName(element.getName().getName()));
	isPrimaryKey = false;
        isAutoIncrement = element.isAutoIncrement();
        setMemberType(sqlType.getMemberType(element));
        if (element.isCharacterType()) {
            length = element.getLength();
        }
        if (element.isNumericType()) {
            precision = element.getPrecision();
            scale = element.getScale();
        }
    }

    public boolean isNullable() {
        return columnElement.isNullable();
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }
    
    public boolean isAutoIncrement() {
        return isAutoIncrement;
    }
    
    public void setPrimaryKey(boolean isPk, boolean isPkField) {
        isPrimaryKey = isPk;

        // this is relevant for CMP 2.1 and earlier where a pk field is not 
        // allowed to be of a primitive type, so the first corresponding class  
        // if extracted from the sqlType
        if (isPkField) {
            setMemberType(getRespectiveNonPrimitiveType());
        }
    }

    /**
     * Tries to get the respective non-primitive type for the type of
     * this member. In other words, gets the respective wrapper class for 
     * the member type if it is a primitive <code>int, long, short, byte, 
     * double, float or char</code>, otherwise 
     * returns <code>sqlType#getFirstNonPrimitiveType</code>.
     */ 
    private String getRespectiveNonPrimitiveType(){
        String type = getMemberType();
        if ("int".equals(type)){//NOI18N
            return Integer.class.getName();
        } else if ("long".equals(type)){//NOI18N
            return Long.class.getName();
        } else if ("short".equals(type)){//NOI18N
            return Short.class.getName();
        } else if ("byte".equals(type)){//NOI18N
            return Byte.class.getName();
        } else if ("double".equals(type)){//NOI18N
            return Double.class.getName();
        } else if ("float".equals(type)){//NOI18N
            return Float.class.getName();
        } else if ("char".equals(type)){//NOI18N
            return Character.class.getName();
        }
        return sqlType.getFirstNonPrimitiveType();
    }
    
    private ColumnElement getColumnElement() {
        return columnElement;
    }
    
    public boolean supportsFinder() {
        return sqlType.supportsFinder();
    }
    
    public String getColumnName() {
        return getColumnElement().getName().getName();
    }
    
    public String getTableName() {
        return getColumnElement().getDeclaringTable().getName().getName();
    }

    public boolean isLobType() {
        return SQLTypeUtil.isLob(getColumnElement().getType());
    }

    /** 
     * Get the length of the column - for character type fields only.
     * 
     * @return the length, <code>null</code> if it is not a character type
     * field or there is no length.
     */
    public Integer getLength() {
        return length;
    }

    /** 
     * Get the precision of the column - for numeric type fields only.
     * 
     * @return the precision, <code>null</code> if it is not a numeric type
     * field or there is no precision.
     */
    public Integer getPrecision() {
        return precision;
    }

    /** 
     * Get the scale of the column - for numeric type fields only.
     * 
     * @return the scale, <code>null</code> if it is not a numeric type
     * field or there is no scale.
     */
    public Integer getScale() {
        return scale;
    }
}
