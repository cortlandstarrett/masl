/*
 ----------------------------------------------------------------------------
 (c) 2005-2023 - CROWN OWNED COPYRIGHT. All rights reserved.
 The copyright of this Software is vested in the Crown
 and the Software is the property of the Crown.
 ----------------------------------------------------------------------------
 SPDX-License-Identifier: Apache-2.0
 ----------------------------------------------------------------------------
 */
package org.xtuml.masl.javagen.astimpl;

abstract class Scope {

    Scope(final ASTNodeImpl declaringNode) {
        this.declaringNode = declaringNode;
    }

    Scope getParentScope() {
        return declaringNode.getEnclosingScope();
    }

    final boolean requiresQualifier(final ThisImpl thisExpression) {
        return requiresQualifier(this, thisExpression, false, false);
    }

    protected boolean requiresQualifier(final Scope baseScope,
                                        final ThisImpl thisExpression,
                                        final boolean visible,
                                        final boolean shadowed) {
        if (visible) {
            return shadowed;
        } else if (getParentScope() != null) {
            return getParentScope().requiresQualifier(baseScope, thisExpression, visible, shadowed);
        } else {
            throw new IllegalStateException("'this' not visible in this scope");
        }
    }

    final boolean requiresQualifier(final SuperQualifierImpl superQualifier) {
        return requiresQualifier(this, superQualifier, false, false);
    }

    protected boolean requiresQualifier(final Scope baseScope,
                                        final SuperQualifierImpl superQualifier,
                                        final boolean visible,
                                        final boolean shadowed) {
        if (visible) {
            return shadowed;
        } else if (getParentScope() != null) {
            return getParentScope().requiresQualifier(baseScope, superQualifier, visible, shadowed);
        } else {
            throw new IllegalStateException("'super' not visible in this scope");
        }
    }

    final boolean requiresQualifier(final TypeDeclarationImpl typeDeclaration) {
        return requiresQualifier(this, typeDeclaration, false, false);
    }

    protected boolean requiresQualifier(final Scope baseScope,
                                        final TypeDeclarationImpl typeDeclaration,
                                        final boolean visible,
                                        final boolean shadowed) {
        if (visible) {
            return shadowed;
        } else if (getParentScope() != null) {
            return getParentScope().requiresQualifier(baseScope, typeDeclaration, visible, shadowed);
        } else {
            return true;
        }
    }

    private final ASTNodeImpl declaringNode;

    ASTNodeImpl getDeclaringNode() {
        return declaringNode;
    }

    final boolean requiresQualifier(final FieldAccessImpl fieldAccess) {
        return requiresQualifier(this, fieldAccess, false, false);
    }

    protected boolean requiresQualifier(final Scope baseScope,
                                        final FieldAccessImpl fieldAccess,
                                        final boolean visible,
                                        final boolean shadowed) {
        if (visible) {
            return shadowed;
        } else if (getParentScope() != null) {
            return getParentScope().requiresQualifier(baseScope, fieldAccess, visible, shadowed);
        } else {
            if (fieldAccess.getField().getModifiers().isStatic()) {
                // can be accessed via type qualifier
                return true;
            } else {
                throw new IllegalStateException("Field " + fieldAccess.getField() + " not visible in this scope");
            }
        }
    }

    final boolean requiresQualifier(final EnumConstantAccessImpl enumAccess) {
        return requiresQualifier(this, enumAccess, false, false);
    }

    protected boolean requiresQualifier(final Scope baseScope,
                                        final EnumConstantAccessImpl enumAccess,
                                        final boolean visible,
                                        final boolean shadowed) {
        if (visible) {
            return shadowed;
        } else if (getParentScope() != null) {
            return getParentScope().requiresQualifier(baseScope, enumAccess, visible, shadowed);
        } else {
            return true;
        }
    }

    final boolean requiresQualifier(final MethodInvocationImpl methodCall) {
        return requiresQualifier(this, methodCall, false, false);
    }

    protected boolean requiresQualifier(final Scope baseScope,
                                        final MethodInvocationImpl methodCall,
                                        final boolean visible,
                                        final boolean shadowed) {
        if (visible) {
            return shadowed;
        } else if (getParentScope() != null) {
            return getParentScope().requiresQualifier(baseScope, methodCall, visible, shadowed);
        } else {
            if (methodCall.getMethod().getModifiers().isStatic()) {
                // can be accessed via type qualifier
                return true;
            } else {
                throw new IllegalStateException("Method " + methodCall.getMethod() + " not visible in this scope");
            }
        }
    }

}
