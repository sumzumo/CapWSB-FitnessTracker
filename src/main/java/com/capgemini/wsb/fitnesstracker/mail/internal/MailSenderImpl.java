package com.capgemini.wsb.fitnesstracker.mail.internal;

import com.capgemini.wsb.fitnesstracker.mail.api.MailDto;
import com.capgemini.wsb.fitnesstracker.mail.api.MailSender;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MailSenderImpl implements MailSender {

    private final JavaMailSender javaMailSender;

    @Override
    public void send(MailDto email) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email.recipient());
        message.setSubject(email.subject());
        message.setText(email.content());
        javaMailSender.send(message);
    }
}
