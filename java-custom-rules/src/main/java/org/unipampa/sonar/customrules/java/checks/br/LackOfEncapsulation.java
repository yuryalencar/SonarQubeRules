package org.unipampa.sonar.customrules.java.checks.br;

import java.util.List;
import com.google.common.collect.ImmutableList;
import org.sonar.check.Priority;
import org.sonar.check.Rule;

import org.sonar.plugins.java.api.IssuableSubscriptionVisitor;
import org.sonar.plugins.java.api.tree.Tree;

import org.sonar.plugins.java.api.tree.VariableTree;
import org.sonar.plugins.java.api.semantic.Symbol.VariableSymbol;

/**
 * LackOfEncapsulation
 *
 * @author Yury Alencar Lima
 */
@Rule(        
        key = "LackOfEncapsulation",
        name = "Falta de Encapsulamento",
        description = "As vari�veis da classe s�o p�blicas diminuindo a seguran�a da aplica��o a ser desenvolvida, assim podendo gerar problemas e altera��es n�o desejadas.",
        priority = Priority.CRITICAL,
        tags = {"bug"})
public class LackOfEncapsulation extends IssuableSubscriptionVisitor {

    @Override
    public List<Tree.Kind> nodesToVisit() {
        return ImmutableList.of(Tree.Kind.VARIABLE);
    }

    @Override
    public void visitNode(Tree tree) {
        VariableTree variable = (VariableTree) tree;
        if (!variable.modifiers().isEmpty()) {
            VariableSymbol variableSymbol = (VariableSymbol) variable.symbol();
            if (variableSymbol.isPublic()) {
                reportIssue(variable.simpleName(), "Erro encontrado: Falta de encapsulamento da vari�vel!");
            }
        }
    }
}
