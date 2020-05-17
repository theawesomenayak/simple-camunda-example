package org.github.theawesomenayak.camunda.workflow;

public interface Workflow {

  void deploy();

  void startProcess(int numberOfProcesses);
}
