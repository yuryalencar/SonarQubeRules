package org.unipampa.sonar.customrules.java.br;

import com.google.common.collect.ImmutableList;
import java.util.List;

import org.sonar.plugins.java.api.JavaCheck;

import org.unipampa.sonar.customrules.java.checks.br.LackOfEncapsulation;
import org.unipampa.sonar.customrules.java.checks.br.MyFirstCustomCheck;

public final class RulesList {

  private RulesList() {
  }

  public static List<Class> getChecks() {
    return ImmutableList.<Class>builder().addAll(getJavaChecks()).addAll(getJavaTestChecks()).build();
  }

  public static List<Class<? extends JavaCheck>> getJavaChecks() {
    return ImmutableList.<Class<? extends JavaCheck>>builder()
      .add(MyFirstCustomCheck.class)
      .add(LackOfEncapsulation.class)
      .build();
  }

  public static List<Class<? extends JavaCheck>> getJavaTestChecks() {
    return ImmutableList.<Class<? extends JavaCheck>>builder()
      .build();
  }
}
