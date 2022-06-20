package com.flipkart.services;

import com.flipkart.constant.*;

import java.util.UUID;

public class NotificationInterfaceImpl implements NotificationInterface{
    @Override
    public int sendNotification(NotificationType type, int studentId, ModeOfPayment modeOfPayment, double amount) {
        return 0;
    }

    @Override
    public UUID getReferenceId(int notificationId) {
        return null;
    }
}
