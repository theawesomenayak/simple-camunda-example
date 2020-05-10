package org.github.theawesomenayak.camunda.workflows;

public interface Workflow {

  void deploy();

  void startProcess(int numberOfProcesses);
}
