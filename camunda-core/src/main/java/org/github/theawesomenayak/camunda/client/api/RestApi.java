package org.github.theawesomenayak.camunda.client.api;

import org.github.theawesomenayak.camunda.client.request.ApiRequest;

public interface RestApi<T extends ApiRequest> {

  void execute(final T request);
}
