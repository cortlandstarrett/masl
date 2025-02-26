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

public enum MultiplicityType {
    ONE("one", org.xtuml.masl.metamodel.relationship.MultiplicityType.ONE), MANY("many",
                                                                                 org.xtuml.masl.metamodel.relationship.MultiplicityType.MANY);

    MultiplicityType(final String text, final org.xtuml.masl.metamodel.relationship.MultiplicityType type) {
        this.text = text;
        this.type = type;
    }

    @Override
    public String toString() {
        return text;
    }

    public org.xtuml.masl.metamodel.relationship.MultiplicityType getType() {
        return type;
    }

    private final String text;
    private final org.xtuml.masl.metamodel.relationship.MultiplicityType type;
}
