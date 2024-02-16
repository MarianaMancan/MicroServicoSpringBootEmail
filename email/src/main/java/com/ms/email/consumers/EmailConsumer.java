package com.ms.email.consumers;

import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.ms.email.dto.EmailRecordDto;
import com.ms.email.models.EmailModel;
import com.ms.email.services.EmailService;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
@Component
public class EmailConsumer {

	
	final EmailService emailService;

    public EmailConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${broker.queue.email.name}" )
    public void listenEmailQueue(@Payload EmailRecordDto emailRecordDto) {
        var emailModel = new EmailModel();
        BeanUtils.copyProperties(emailRecordDto, emailModel);
        emailService.sendEmail(emailModel);
    }
}
