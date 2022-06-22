package com.flipkart.services;

import com.flipkart.constant.*;

import java.util.UUID;

public class NotificationInterfaceImpl implements NotificationInterface{
    @Override
    public void sendNotification(NotificationType type, int studentId, ModeOfPayment modeOfPayment, double amount) {
        if(type.equals("payment")){
            System.out.println(studentId + " has paid " + amount + " by" + modeOfPayment);
        }
        else {
            System.out.println("Error Occurred");
        }
    }

    @Override
    public UUID getReferenceId(int notificationId) {
        return null;
    }
}
