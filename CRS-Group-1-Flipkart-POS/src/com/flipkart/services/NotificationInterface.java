package com.flipkart.services;

import com.flipkart.constant.*;

import java.util.*;

public interface NotificationInterface {
    public int sendNotification(NotificationType type, int studentId, ModeOfPayment modeOfPayment, double amount);
    public UUID getReferenceId(int notificationId);
}
