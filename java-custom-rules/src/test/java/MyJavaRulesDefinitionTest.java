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

        assertThat(repository.name()).isEqualTo("UNIPAMPA - Custom Rules for Projects");
        assertThat(repository.language()).isEqualTo("java");
        assertThat(repository.rules()).hasSize(RulesList.getChecks().size());

        assertRuleProperties(repository);
        //assertParameterProperties(repository);
        assertAllRuleParametersHaveDescription(repository);
    }

    /**
     * Esse mï¿½todo ï¿½ para testar quando sua regra possui parï¿½metros de entrada
     * como por exemplo nomes de mï¿½todos, classe ou atï¿½ mesmo annotations que
     * estejam presentes, como a ï¿½nica regra atï¿½ o momento nï¿½o contï¿½m parï¿½metros
     * o mesmo estï¿½ comentado.
     * @param repository 
     */
    private void assertParameterProperties(Repository repository) {
        // TooManyLinesInFunctionCheck
        //Param max = repository.rule("MyFirstCustomRule").param("name");
        //assertThat(max).isNotNull();
        //assertThat(max.defaultValue()).isEqualTo("Inject");
        //assertThat(max.description()).isEqualTo("Mï¿½todos que contï¿½m um ï¿½nico parï¿½metro nï¿½o podem conter saï¿½da do mesmo tipo do parï¿½metro de entrada.");
        //assertThat(max.type()).isEqualTo(RuleParamType.STRING);
    }

    private void assertRuleProperties(Repository repository) {
        Rule rule = repository.rule("MyFirstCustomRule");
        assertThat(rule).isNotNull();
        assertThat(rule.name()).isEqualTo("Parâmetro de retorno deve ser diferente da saída.");
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
