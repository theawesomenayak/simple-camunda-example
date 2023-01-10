package org.github.theawesomenayak.camunda.service.security.core;

import java.io.IOException;

import org.camunda.bpm.webapp.impl.security.SecurityActions;
import org.camunda.bpm.webapp.impl.security.auth.Authentications;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

public abstract class AuthenticationFilter implements Filter {

  @Override
  public final void doFilter(final ServletRequest request, final ServletResponse response,
                             final FilterChain chain) throws IOException, ServletException {

    final HttpServletRequest req = (HttpServletRequest) request;

    final Authentications authentications = Authentications.getFromSession(req.getSession());

    setup(req, authentications);

    Authentications.setCurrent(authentications);

    try {

      SecurityActions.runWithAuthentications(() -> {

        try {
          chain.doFilter(request, response);
          return null;
        } catch (final Exception e) {
          throw new AuthenticationException(e);
        }
      }, authentications);
    } finally {
      Authentications.clearCurrent();
      Authentications.updateSession(req.getSession(false), authentications);
    }
  }

  protected abstract void setup(HttpServletRequest request, Authentications authentications);
}
