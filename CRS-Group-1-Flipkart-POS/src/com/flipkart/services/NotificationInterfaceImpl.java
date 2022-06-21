package com.flipkart.services;

import com.flipkart.constant.*;

import java.util.UUID;

public class NotificationInterfaceImpl implements NotificationInterface{
    @Override
    public int sendNotification(NotificationType type, int studentId, ModeOfPayment modeOfPayment, double amount) {
        if(type.equals("payment")){
            system.println(studentId + " has paid " + amount + " by" + modeOfPayment);
        }
        else {
            system.println("Error Occured");
        }
        return 0;
    }

    @Override
    public UUID getReferenceId(int notificationId) {
        return null;
    }
}
