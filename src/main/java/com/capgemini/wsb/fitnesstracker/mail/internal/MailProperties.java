package com.capgemini.wsb.fitnesstracker.mail.internal;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "mail")
@Getter
@RequiredArgsConstructor
class MailProperties {

    private final String senderAddress;

}
