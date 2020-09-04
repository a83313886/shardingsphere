/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.shardingsphere.sql.parser.sql.common.segment.dml.expr;

import lombok.Getter;
import lombok.Setter;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Binary operation expression.
 */
@Setter
@Getter
public class BinaryOperationExpression implements ExpressionSegment {
    
    private int startIndex;
    
    private int stopIndex;
    
    private ExpressionSegment left;
    
    private ExpressionSegment right;
    
    private String operator;
    
    /**
     * Get expression list.
     *
     * @return ExpressionSegment list.
     */
    public Collection<ExpressionSegment> getExpressionList() {
        Collection<ExpressionSegment> result = new LinkedList<>();
        boolean logical = "and".equalsIgnoreCase(operator) || "&&".equalsIgnoreCase(operator) || "OR".equalsIgnoreCase(operator) || "||".equalsIgnoreCase(operator);
        if (logical) {
            if (!(left instanceof BinaryOperationExpression)) {
                result.add(left);
            } else {
                result.addAll(((BinaryOperationExpression) left).getExpressionList());
            }
            if (!(right instanceof BinaryOperationExpression)) {
                result.add(right);
            } else {
                result.addAll(((BinaryOperationExpression) right).getExpressionList());
            }
        } else {
            result.add(this);
        }
        return result;
    }
    
    /**
     * Get and expression list.
     *
     * @return ExpressionSegment list.
     */
    public Collection<Collection<ExpressionSegment>> getAndExpressionList() {
        Collection<ExpressionSegment> result = new LinkedList<>();
        boolean logical = "and".equalsIgnoreCase(operator) || "&&".equalsIgnoreCase(operator) || "OR".equalsIgnoreCase(operator) || "||".equalsIgnoreCase(operator);
        if (logical) {
            if (!(left instanceof BinaryOperationExpression)) {
                result.add(left);
            } else {
                result.addAll(((BinaryOperationExpression) left).getExpressionList());
            }
            if (!(right instanceof BinaryOperationExpression)) {
                result.add(right);
            } else {
                result.addAll(((BinaryOperationExpression) right).getExpressionList());
            }
        } else {
            result.add(this);
        }
        return result;
    }
}
