package org.github.theawesomenayak.camunda.service.security;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.Tenant;
import org.springframework.stereotype.Component;

@Component
public final class IdentityManager {

  /**
   * Get the username - this is different based on the SSO technology used, see e.g.
   * https://github.com/camunda/camunda-sso-jboss/blob/master/camunda-bpm-
   * build-webapp/src/main/java/de/novatec/bpm/webapp/impl/security/auth/
   * AuthenticationFilter.java#L57 for an implementation using Kerberos on JBoss AS 7
   */
  public String retrieveUsername(final HttpServletRequest req) {
    // Simply read it from a URL parameter in this case
    return req.getParameter("auto-login-username");
  }

  public List<String> getGroupsOfUser(final String userId, final ProcessEngine processEngine) {

    final List<Group> groups = processEngine.getIdentityService().createGroupQuery()
        .groupMember(userId)
        .list();

    final List<String> groupIds = new ArrayList<>();
    for (final Group group : groups) {
      groupIds.add(group.getId());
    }
    return groupIds;
  }

  public List<String> getTenantsOfUser(final String userId, final ProcessEngine processEngine) {

    final List<Tenant> tenants = processEngine.getIdentityService().createTenantQuery()
        .userMember(userId)
        .includingGroupsOfUser(true)
        .list();

    final List<String> tenantIds = new ArrayList<>();
    for (final Tenant tenant : tenants) {
      tenantIds.add(tenant.getId());
    }
    return tenantIds;
  }
}
