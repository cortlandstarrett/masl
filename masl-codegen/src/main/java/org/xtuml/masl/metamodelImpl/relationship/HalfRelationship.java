/*
 ----------------------------------------------------------------------------
 (c) 2005-2023 - CROWN OWNED COPYRIGHT. All rights reserved.
 The copyright of this Software is vested in the Crown
 and the Software is the property of the Crown.
 ----------------------------------------------------------------------------
 SPDX-License-Identifier: Apache-2.0
 ----------------------------------------------------------------------------
 */
package org.xtuml.masl.metamodelImpl.relationship;

import org.xtuml.masl.metamodelImpl.expression.ObjectNameExpression;

public class HalfRelationship {

    public static HalfRelationship create(final ObjectNameExpression fromObject,
                                          final boolean conditional,
                                          final String role,
                                          final MultiplicityType mult,
                                          final ObjectNameExpression toObject) {
        if (fromObject == null || toObject == null) {
            return null;
        }

        return new HalfRelationship(fromObject, conditional, role, mult, toObject);
    }

    private HalfRelationship(final ObjectNameExpression fromObject,
                             final boolean conditional,
                             final String role,
                             final MultiplicityType mult,
                             final ObjectNameExpression toObject) {
        this.fromObject = fromObject;
        this.conditional = conditional;
        this.role = role;
        this.mult = mult;
        this.toObject = toObject;
    }

    private final ObjectNameExpression fromObject;
    private final boolean conditional;
    private final String role;
    private final MultiplicityType mult;

    private final ObjectNameExpression toObject;

    public boolean isConditional() {
        return conditional;
    }

    public ObjectNameExpression getFromObject() {
        return fromObject;
    }

    public MultiplicityType getMult() {
        return mult;
    }

    public String getRole() {
        return role;
    }

    public ObjectNameExpression getToObject() {
        return toObject;
    }

    @Override
    public String toString() {
        return fromObject.getObject().getName() +
               (conditional ? " conditionally " : " unconditionally ") +
               role +
               " " +
               mult +
               " " +
               toObject.getObject().getName();
    }

}
