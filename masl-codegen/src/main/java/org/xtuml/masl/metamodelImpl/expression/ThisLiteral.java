/*
 ----------------------------------------------------------------------------
 (c) 2005-2023 - CROWN OWNED COPYRIGHT. All rights reserved.
 The copyright of this Software is vested in the Crown
 and the Software is the property of the Crown.
 ----------------------------------------------------------------------------
 SPDX-License-Identifier: Apache-2.0
 ----------------------------------------------------------------------------
 */
package org.xtuml.masl.metamodelImpl.expression;

import org.xtuml.masl.metamodel.ASTNodeVisitor;
import org.xtuml.masl.metamodelImpl.common.Position;
import org.xtuml.masl.metamodelImpl.common.Service;
import org.xtuml.masl.metamodelImpl.error.SemanticError;
import org.xtuml.masl.metamodelImpl.error.SemanticErrorCode;
import org.xtuml.masl.metamodelImpl.object.ObjectDeclaration;
import org.xtuml.masl.metamodelImpl.object.ObjectService;
import org.xtuml.masl.metamodelImpl.statemodel.State;
import org.xtuml.masl.metamodelImpl.type.BasicType;
import org.xtuml.masl.metamodelImpl.type.InstanceType;

public class ThisLiteral extends LiteralExpression implements org.xtuml.masl.metamodel.expression.ThisLiteral {

    public static ThisLiteral create(final Position position, final Service service, final State state) {
        try {
            return new ThisLiteral(position, service, state);
        } catch (final SemanticError e) {
            e.report();
            return null;
        }
    }

    private ThisLiteral(final Position position, final Service service, final State state) throws SemanticError {
        super(position);

        if (service != null && service instanceof ObjectService && ((ObjectService) service).isInstance()) {
            this.object = ((ObjectService) service).getParentObject();
        } else if (state != null && state.isInstance()) {
            this.object = state.getParentObject();
        } else {
            throw new SemanticError(SemanticErrorCode.ThisNotValid, position);
        }
    }

    public ThisLiteral(final Position position, final ObjectDeclaration object) {
        super(position);
        this.object = object;
    }

    private final ObjectDeclaration object;

    @Override
    public String toString() {
        return "this";
    }

    @Override
    public ObjectDeclaration getObject() {
        return object;
    }

    @Override
    public BasicType getType() {
        return InstanceType.createAnonymous(object);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        return obj instanceof ThisLiteral;
    }

    @Override
    public int hashCode() {

        return object.hashCode();
    }

    @Override
    public void accept(final ASTNodeVisitor v) {
        v.visitThisLiteral(this);
    }

}
