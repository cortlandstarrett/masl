/*
 ----------------------------------------------------------------------------
 (c) 2005-2023 - CROWN OWNED COPYRIGHT. All rights reserved.
 The copyright of this Software is vested in the Crown
 and the Software is the property of the Crown.
 ----------------------------------------------------------------------------
 SPDX-License-Identifier: Apache-2.0
 ----------------------------------------------------------------------------
 */
package org.xtuml.masl.translate.main.code;

import org.xtuml.masl.cppgen.Expression;
import org.xtuml.masl.cppgen.ExpressionStatement;
import org.xtuml.masl.translate.main.Architecture;
import org.xtuml.masl.translate.main.Scope;
import org.xtuml.masl.translate.main.expression.ExpressionTranslator;

public class DelayTranslator extends CodeTranslator {

    protected DelayTranslator(final org.xtuml.masl.metamodel.code.DelayStatement delay,
                              final Scope parentScope,
                              final CodeTranslator parentTranslator) {
        super(delay, parentScope, parentTranslator);

        final Expression
                time =
                ExpressionTranslator.createTranslator(delay.getDuration(), getScope()).getReadExpression();

        getCode().appendStatement(new ExpressionStatement(Architecture.delay.asFunctionCall(time)));

    }

}
