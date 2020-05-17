package org.github.theawesomenayak.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class NotificationParams {

  private final String receiver;

  private final String subject;

  private final String message;
}
