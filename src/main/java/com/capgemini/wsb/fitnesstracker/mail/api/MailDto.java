package com.capgemini.wsb.fitnesstracker.mail.api;

public record MailDto(String recipient, String subject, String content) {

}