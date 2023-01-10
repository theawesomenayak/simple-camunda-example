package org.github.theawesomenayak.camunda.service.security.core;

import java.util.ArrayList;
import java.util.List;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.identity.Tenant;

import javax.servlet.http.HttpServletRequest;

public abstract class IdentityManager {

  public abstract String getUserName(final HttpServletRequest req);

  public final List<String> getGroupsOfUser(final String userId,
      final ProcessEngine processEngine) {

    final List<Group> groups = processEngine.getIdentityService().createGroupQuery()
        .groupMember(userId)
        .list();

    final List<String> groupIds = new ArrayList<>();
    for (final Group group : groups) {
      groupIds.add(group.getId());
    }
    return groupIds;
  }

  public final List<String> getTenantsOfUser(final String userId,
      final ProcessEngine processEngine) {

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
