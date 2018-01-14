/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.unipampa.sonar.customrules.java.checks.br;

/**
 *
 * @author YURY
 */
import com.google.common.collect.ImmutableList;

import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.Tree.Kind;

import java.util.List;
import org.sonar.check.Priority;
import org.sonar.check.Rule;
import org.sonar.plugins.java.api.semantic.Symbol.MethodSymbol;
import org.sonar.plugins.java.api.semantic.Type;
import org.sonar.plugins.java.api.tree.MethodTree;
import org.sonar.plugins.java.api.tree.Tree;

@Rule(
        key = "MyFirstCustomRule",
        name = "Par�metro de retorno deve ser diferente da sa�da.",
        description = "M�todos que cont�m um �nico par�metro n�o podem conter sa�da do mesmo tipo do par�metro de entrada.",
        priority = Priority.CRITICAL,
        tags = {"bug"})
public class MyFirstCustomCheck extends IssuableSubscriptionVisitor {

    @Override
    public List<Kind> nodesToVisit() {
        return ImmutableList.of(Kind.METHOD);
    }

    @Override
    public void visitNode(Tree tree) {
        MethodTree method = (MethodTree) tree;
        if (method.parameters().size() == 1) {
            MethodSymbol symbol = method.symbol();
            Type parameterType = symbol.parameterTypes().get(0);
            Type returnType = symbol.returnType().type();
            if (returnType.is(parameterType.fullyQualifiedName())) {
                reportIssue(method.simpleName(), "Erro encontrado: Par�metro de entrada igual ao retorno!");
            }
        }
    }
}
