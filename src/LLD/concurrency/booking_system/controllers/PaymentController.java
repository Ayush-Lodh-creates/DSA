package LLD.concurrency.booking_system.controllers;

import LLD.concurrency.booking_system.entity.User;
import LLD.concurrency.booking_system.service.PaymentService;

public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public void processPayment(final String bookingId, final User user) throws Exception {
        paymentService.processPayment(bookingId, user);
    }
}
