/*
 * SonarQube Java Custom Rules Example
 * Copyright (C) 2016-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

import java.util.List;
import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;
import org.sonar.api.rules.RuleType;
import org.sonar.api.server.debt.DebtRemediationFunction.Type;
import org.sonar.api.server.rule.RuleParamType;
import org.sonar.api.server.rule.RulesDefinition;
import org.sonar.api.server.rule.RulesDefinition.Param;
import org.sonar.api.server.rule.RulesDefinition.Repository;
import org.sonar.api.server.rule.RulesDefinition.Rule;
import org.unipampa.sonar.customrules.java.br.MyJavaRulesDefinition;
import org.unipampa.sonar.customrules.java.br.RulesList;

public class MyJavaRulesDefinitionTest {

    @Test
    public void test() {
        MyJavaRulesDefinition rulesDefinition = new MyJavaRulesDefinition();
        RulesDefinition.Context context = new RulesDefinition.Context();
        rulesDefinition.define(context);
        RulesDefinition.Repository repository = context.repository(MyJavaRulesDefinition.REPOSITORY_KEY);

        assertThat(repository.name()).isEqualTo("MyCompany Custom Repository");
        assertThat(repository.language()).isEqualTo("java");
        assertThat(repository.rules()).hasSize(RulesList.getChecks().size());

        assertRuleProperties(repository);
        assertParameterProperties(repository);
        assertAllRuleParametersHaveDescription(repository);
    }

    private void assertParameterProperties(Repository repository) {
        // TooManyLinesInFunctionCheck
        Param max = repository.rule("MyFirstCustomRule").param("name");
        assertThat(max).isNotNull();
        //assertThat(max.defaultValue()).isEqualTo("Inject");
        assertThat(max.description()).isEqualTo("M�todos que cont�m um �nico par�metro n�o podem conter sa�da do mesmo tipo do par�metro de entrada.");
        assertThat(max.type()).isEqualTo(RuleParamType.STRING);
    }

    private void assertRuleProperties(Repository repository) {
        Rule rule = repository.rule("MyFirstCustomRule");
        assertThat(rule).isNotNull();
        assertThat(rule.name()).isEqualTo("Par�metro de retorno deve ser diferente da sa�da.");
        //assertThat(rule.debtRemediationFunction().type()).isEqualTo(Type.CONSTANT_ISSUE);
        assertThat(rule.type()).isEqualTo(RuleType.BUG);
    }

    private void assertAllRuleParametersHaveDescription(Repository repository) {
        for (Rule rule : repository.rules()) {
            for (Param param : rule.params()) {
                assertThat(param.description()).as("description for " + param.key()).isNotEmpty();
            }
        }
    }

}