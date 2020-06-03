package org.github.theawesomenayak.camunda.service.security;

import static org.camunda.bpm.engine.authorization.Permissions.ACCESS;
import static org.camunda.bpm.engine.authorization.Resources.APPLICATION;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.camunda.bpm.engine.AuthorizationService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.webapp.impl.security.SecurityActions;
import org.camunda.bpm.webapp.impl.security.auth.Authentication;
import org.camunda.bpm.webapp.impl.security.auth.Authentications;
import org.camunda.bpm.webapp.impl.security.auth.UserAuthentication;
import org.springframework.stereotype.Component;

/**
 * GoTo - http://localhost:8080/?auto-login-username=admin
 */
@Component
@AllArgsConstructor
public final class AutoLoginAuthenticationFilter implements Filter {

  private static final String[] APPS = new String[]{"welcome", "admin", "cockpit", "tasklist"};

  private final IdentityManager identityManager;

  private final ProcessEngine processEngine;

  @Override
  public void doFilter(final ServletRequest request, final ServletResponse response,
      final FilterChain chain) throws IOException, ServletException {

    final HttpServletRequest req = (HttpServletRequest) request;

    // get authentication from session
    final Authentications authentications = Authentications.getFromSession(req.getSession());

    // This function is added to the normal AuthenticationFilter
    setAutoLoginAuthentication(req, authentications);

    // set current authentication to the one restored from session (maybe auto login was added)
    Authentications.setCurrent(authentications);

    try {

      SecurityActions.runWithAuthentications(() -> {

        try {
          chain.doFilter(request, response);
        } catch (final Exception e) {
          throw new AuthenticationException(e);
        }
        return null;
      }, authentications);
    } finally {
      Authentications.clearCurrent();
      // store updated authentication object in session for next request
      Authentications.updateSession(req.getSession(), authentications);
    }
  }

  private void setAutoLoginAuthentication(final HttpServletRequest req,
      final Authentications authentications) {

    // Get the username from the user in SSO
    final String username = identityManager.retrieveUsername(req);

    // if not set - no auto login
    if (username == null) {
      return;
    }

    // if already in the list of logged in users - nothing to do
    final Authentication authentication = authentications
        .getAuthenticationForProcessEngine(processEngine.getName());
    if (authentication != null && Objects.equals(authentication.getName(), username)) {
      return;
    }

    final AuthorizationService authorizationService = processEngine.getAuthorizationService();

    // query group information
    final List<String> groupIds = identityManager.getGroupsOfUser(username, processEngine);
    final List<String> tenantIds = identityManager.getTenantsOfUser(username, processEngine);

    // check user's app authorizations by iterating of list of apps and ask if permitted
    final HashSet<String> authorizedApps = new HashSet<>();
    if (processEngine.getProcessEngineConfiguration().isAuthorizationEnabled()) {
      for (final String application : APPS) {
        if (authorizationService
            .isUserAuthorized(username, groupIds, ACCESS, APPLICATION, application)) {
          authorizedApps.add(application);
        }
      }
    } else {
      Collections.addAll(authorizedApps, APPS);
    }

    // create new authentication object to store authentication
    final UserAuthentication newAuthentication = new UserAuthentication(username,
        processEngine.getName());
    newAuthentication.setGroupIds(groupIds);
    newAuthentication.setTenantIds(tenantIds);
    newAuthentication.setAuthorizedApps(authorizedApps);

    // and add the new logged in user
    authentications.addAuthentication(newAuthentication);
  }
}