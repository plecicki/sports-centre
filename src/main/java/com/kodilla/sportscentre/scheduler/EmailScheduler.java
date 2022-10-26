package com.kodilla.sportscentre.scheduler;

import com.kodilla.sportscentre.domain.Invoice;
import com.kodilla.sportscentre.domain.InvoiceCreateDto;
import com.kodilla.sportscentre.domain.Mail;
import com.kodilla.sportscentre.domain.User;
import com.kodilla.sportscentre.domain.enums.PaymentStatus;
import com.kodilla.sportscentre.repositories.InvoiceRepository;
import com.kodilla.sportscentre.repositories.UserRepository;
import com.kodilla.sportscentre.services.InvoiceService;
import com.kodilla.sportscentre.services.SimpleEmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EmailScheduler {

    private final SimpleEmailService simpleEmailService;
    private final UserRepository userRepository;
    private final InvoiceService invoiceService;

    @Scheduled(cron = "0 0 10 * * *")
    public void sendAlertEmailsAndExtendSubscription() {
        List<User> userList = userRepository.findAll();
        for (User user: userList) {
            if (user.getAutoExtension() == null) {
                user.setAutoExtension(false);
            }
            if (user.getAutoExtension()) {
                if (user.getSubValidity().equals(LocalDate.now())) {
                    simpleEmailService.sendDaily(
                            new Mail(
                                    user.getEmail(),
                                    "Monthly extension of subscription",
                                    "We have extended the validity of your subscription " +
                                            "for another month and we issue an invoice",
                                    null,
                                    user.getName()
                            )
                    );
                    InvoiceCreateDto invoiceCreateDto = new InvoiceCreateDto(
                            PaymentStatus.NOTPAID,
                            LocalDate.now().plus(1, ChronoUnit.WEEKS),
                            user.getStudent() ? new BigDecimal(100.0) : new BigDecimal(200.0),
                            user
                    );
                    invoiceService.createInvoice(invoiceCreateDto);
                }
            } else {
                if (0 < ChronoUnit.DAYS.between(LocalDate.now(), user.getSubValidity()) &&
                        ChronoUnit.DAYS.between(LocalDate.now(), user.getSubValidity()) <= 7) {
                    simpleEmailService.sendDaily(
                            new Mail(
                                    user.getEmail(),
                                    "Subscription reminder",
                                    "The subscription will expire in " +
                                            ChronoUnit.DAYS.between(LocalDate.now(), user.getSubValidity()) + "" +
                                            (ChronoUnit.DAYS.between(LocalDate.now(), user.getSubValidity()) == 1 ? " day" : " days") +
                                            ". We encourage you to renew your subscription to continue using the sports facility",
                                    null,
                                    user.getName()
                            )
                    );
                } else if (ChronoUnit.DAYS.between(LocalDate.now(), user.getSubValidity()) <= 0) {
                    simpleEmailService.sendDaily(
                            new Mail(
                                    user.getEmail(),
                                    "Subscription reminder",
                                    "The subscription expired " +
                                            (ChronoUnit.DAYS.between(LocalDate.now(), user.getSubValidity()) * (-1)) + "" +
                                            (ChronoUnit.DAYS.between(LocalDate.now(), user.getSubValidity()) == -1 ? " day" : " days") +
                                            " ago. We encourage you to renew your subscription to continue using the sports facility",
                                    null,
                                    user.getName()
                            )
                    );
                }
            }
        }
    }
}
