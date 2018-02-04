package org.unipampa.sonar.customrules.java.checks.br;

import org.junit.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

/**
 * LackOfEncapsulationTest
 * @author Yury Alencar Lima
 */
public class LackOfEncapsulationTest {

    @Test
    public void testEncapsulation(){
        JavaCheckVerifier.verify("src/test/files/LackOfEncapsulationCheck.java", new LackOfEncapsulation());
    }
}