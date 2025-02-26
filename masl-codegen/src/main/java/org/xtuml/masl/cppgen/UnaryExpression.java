/*
 ----------------------------------------------------------------------------
 (c) 2005-2023 - CROWN OWNED COPYRIGHT. All rights reserved.
 The copyright of this Software is vested in the Crown
 and the Software is the property of the Crown.
 ----------------------------------------------------------------------------
 SPDX-License-Identifier: Apache-2.0
 ----------------------------------------------------------------------------
 */
package org.xtuml.masl.cppgen;

import java.util.Set;

public class UnaryExpression extends Expression {

    private final UnaryOperator operator;
    private final Expression expression;

    public UnaryExpression(final UnaryOperator operator, final Expression expression) {
        this.operator = operator;
        this.expression = expression;
    }

    @Override
    String getCode(final Namespace currentNamespace, final String alignment) {
        return (operator.isPrefix() ? operator.getCode() : "") +
               (getPrecedence() < expression.getPrecedence() ? "(" : "") +
               expression.getCode(currentNamespace, alignment) +
               (getPrecedence() < expression.getPrecedence() ? ")" : "") +
               (operator.isPrefix() ? "" : operator.getCode());
    }

    @Override
    Set<Declaration> getForwardDeclarations() {
        final Set<Declaration> result = super.getForwardDeclarations();
        result.addAll(expression.getForwardDeclarations());
        return result;
    }

    @Override
    Set<CodeFile> getIncludes() {
        final Set<CodeFile> result = super.getIncludes();
        result.addAll(expression.getIncludes());
        return result;
    }

    @Override
    int getPrecedence() {
        return operator.getPrecedence();
    }

    @Override
    boolean isTemplateType() {
        return expression.isTemplateType();
    }

}
