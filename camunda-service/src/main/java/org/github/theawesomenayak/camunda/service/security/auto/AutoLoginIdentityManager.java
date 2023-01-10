package org.github.theawesomenayak.camunda.service.security.auto;

import org.github.theawesomenayak.camunda.service.security.core.IdentityManager;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public final class AutoLoginIdentityManager extends IdentityManager {

  @Override
  public String getUserName(final HttpServletRequest req) {

    return req.getParameter("auto-login-username");
  }
}
