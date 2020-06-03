package org.github.theawesomenayak.camunda.service.security;

import javax.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.authorization.Authorization;
import org.camunda.bpm.engine.authorization.Permissions;
import org.camunda.bpm.engine.authorization.Resource;
import org.camunda.bpm.engine.authorization.Resources;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class MockIdentityProvider {

  private final AuthorizationService authorizationService;

  @PostConstruct
  void createUsers() {

    authorizeUser("demo", Resources.APPLICATION, Resources.TASK);
    authorizeUser("siben", Resources.APPLICATION);
  }

  private void authorizeUser(final String name, final Resource... resources) {

    for (final Resource resource : resources) {
      final Authorization auth = authorizationService
          .createNewAuthorization(Authorization.AUTH_TYPE_GRANT);
      auth.setUserId(name);
      auth.setResourceId("*");
      auth.setResourceType(resource.resourceType());
      auth.addPermission(Permissions.ALL);
      authorizationService.saveAuthorization(auth);
    }
  }
}
