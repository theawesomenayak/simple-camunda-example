package org.github.theawesomenayak.camunda.engine;

import com.google.common.base.Stopwatch;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import org.camunda.bpm.dmn.engine.DmnDecision;
import org.camunda.bpm.dmn.engine.DmnDecisionTableResult;
import org.camunda.bpm.dmn.engine.DmnEngine;
import org.camunda.bpm.dmn.engine.DmnEngineConfiguration;
import org.camunda.bpm.engine.variable.VariableMap;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.model.dmn.Dmn;
import org.camunda.bpm.model.dmn.DmnModelInstance;
import org.camunda.bpm.model.dmn.HitPolicy;
import org.camunda.bpm.model.dmn.instance.Decision;
import org.camunda.bpm.model.dmn.instance.DecisionTable;
import org.camunda.bpm.model.dmn.instance.Definitions;
import org.camunda.bpm.model.dmn.instance.Input;
import org.camunda.bpm.model.dmn.instance.InputEntry;
import org.camunda.bpm.model.dmn.instance.InputExpression;
import org.camunda.bpm.model.dmn.instance.Output;
import org.camunda.bpm.model.dmn.instance.OutputEntry;
import org.camunda.bpm.model.dmn.instance.Rule;
import org.camunda.bpm.model.dmn.instance.Text;

public class SampleDmnEvaluator {

  public static void main(final String[] args) {

    final String[] items = {"Laptop", "Phone", "Charger"};
    final DmnModelInstance modelInstance = Dmn.createEmptyModel();
    for (int i = 0; i < 10; i++) {
      final List<Long> times = new ArrayList<>();
      final Stopwatch stopwatch = Stopwatch.createStarted();
      final DecisionTable decisionTable = getDecisionTable(modelInstance);
      times.add(stopwatch.elapsed().toMillis());
      addRules(decisionTable, modelInstance);
      times.add(stopwatch.elapsed().toMillis());
      final boolean result = evaluate(modelInstance,
          items[ThreadLocalRandom.current().nextInt(0, 3)]);
      times.add(stopwatch.stop().elapsed().toMillis());
      System.out.println("Result=" + result + " Times=" + times);
    }
  }

  private static DecisionTable getDecisionTable(final DmnModelInstance modelInstance) {

    final Definitions definitions = buildDefinitions(modelInstance);

    final Decision decision = buildDecision(modelInstance);
    definitions.addChildElement(decision);

    final DecisionTable decisionTable = buildDecisionTable(modelInstance);
    decision.addChildElement(decisionTable);

    final Input input = buildInput(modelInstance);
    decisionTable.addChildElement(input);

    final Output output = buildOutput(modelInstance);
    decisionTable.addChildElement(output);

    return decisionTable;
  }

  private static Definitions buildDefinitions(final DmnModelInstance modelInstance) {

    final Definitions definitions = modelInstance.newInstance(Definitions.class);
    definitions.setNamespace("http://camunda.org/schema/1.0/dmn");
    definitions.setName("definitions");
    modelInstance.setDefinitions(definitions);
    return definitions;
  }

  private static Decision buildDecision(final DmnModelInstance modelInstance) {

    final Decision decision = modelInstance.newInstance(Decision.class);
    decision.setId("approve-payment");
    decision.setName("Approve Payment");
    return decision;
  }

  private static DecisionTable buildDecisionTable(final DmnModelInstance modelInstance) {

    final DecisionTable decisionTable = modelInstance.newInstance(DecisionTable.class);
    decisionTable.setId("decisionTable");
    decisionTable.setHitPolicy(HitPolicy.UNIQUE);
    return decisionTable;
  }

  private static Input buildInput(final DmnModelInstance modelInstance) {

    final Text text = modelInstance.newInstance(Text.class);
    text.setTextContent("item");

    final InputExpression inputExpression = modelInstance.newInstance(InputExpression.class);
    inputExpression.setTypeRef("string");
    inputExpression.addChildElement(text);

    final Input input = modelInstance.newInstance(Input.class);
    input.setLabel("Item");
    input.addChildElement(inputExpression);

    return input;
  }

  private static Output buildOutput(final DmnModelInstance modelInstance) {

    final Output output = modelInstance.newInstance(Output.class);
    output.setLabel("Approved");
    output.setName("approved");
    output.setTypeRef("boolean");
    return output;
  }

  private static void addRules(final DecisionTable decisionTable,
      final DmnModelInstance modelInstance) {

    decisionTable.addChildElement(buildRule(modelInstance, "Phone", true));
    decisionTable.addChildElement(buildRule(modelInstance, "Laptop", false));
    decisionTable.addChildElement(buildRule(modelInstance, "Charger", true));
  }

  private static Rule buildRule(final DmnModelInstance modelInstance, final String item,
      final boolean approved) {

    final Text inputText = modelInstance.newInstance(Text.class);
    inputText.setTextContent(String.format("\"%s\"", item));
    final InputEntry inputEntry = modelInstance.newInstance(InputEntry.class);
    inputEntry.addChildElement(inputText);

    final Text outputText = modelInstance.newInstance(Text.class);
    outputText.setTextContent(String.format("%s", approved));
    final OutputEntry outputEntry = modelInstance.newInstance(OutputEntry.class);
    outputEntry.addChildElement(outputText);

    final Rule rule = modelInstance.newInstance(Rule.class);
    rule.addChildElement(inputEntry);
    rule.addChildElement(outputEntry);
    return rule;
  }

  private static boolean evaluate(final DmnModelInstance modelInstance, final String item) {

    Dmn.validateModel(modelInstance);
    final DmnEngine dmnEngine = DmnEngineConfiguration.createDefaultDmnEngineConfiguration()
        .buildEngine();
    final DmnDecision decision = dmnEngine.parseDecision("approve-payment", modelInstance);
    final VariableMap variables = Variables
        .createVariables()
        .putValue("item", item);
    final DmnDecisionTableResult result = dmnEngine.evaluateDecisionTable(decision, variables);
    if (result.isEmpty()) {
      return false;
    }
    return result.getSingleResult().getEntry("approved");
  }
}
