package com.flipkart.service;

import com.flipkart.constant.ModeOfPayment;
import com.flipkart.constant.NotificationType;
import com.flipkart.dao.NotificationDaoInterface;
import com.flipkart.dao.NotificationDaoOperation;

import java.sql.SQLException;
import java.util.UUID;

/**
 * 
 * 
 * This method implements all the method related to the notification system
 */
public class NotificationInterfaceImpl implements NotificationInterface {

	private static volatile NotificationInterfaceImpl instance=null;
	NotificationDaoInterface notificationDaoInterface=NotificationDaoOperation.getInstance();

	private NotificationInterfaceImpl()
	{

	}
	
	/**
	 * Method to make NotificationDaoOperation Singleton
	 * @return
	 */
	public static NotificationInterfaceImpl getInstance()
	{
		if(instance==null)
		{
			// This is a synchronized block, when multiple threads will access this instance
			synchronized(NotificationInterfaceImpl.class){
				instance=new NotificationInterfaceImpl();
			}
		}
		return instance;
	}
	
	/**
	 * Method to send notification
	 * @param type: type of the notification to be sent
	 * @param studentId: student to be notified
	 * @param modeOfPayment: payment mode used
	 * @return notification id for the record added in the database
	 */
	@Override
	public int sendNotification(NotificationType type, String studentId,ModeOfPayment modeOfPayment,double amount) {
		int notificationId=0;
		try {
			notificationId=notificationDaoInterface.sendNotification(type, studentId, modeOfPayment, amount);
			
		} catch(SQLException ex) {
			System.out.println("Error occured "+ex.getMessage());
		}
		return notificationId;
	}

	/**
	 * Method to return UUID for a transaction
	 * @param notificationId: notification id added in the database
	 * @return transaction id of the payment
	 */
	@Override
	public UUID getReferenceId(int notificationId) {
		// TODO Auto-generated method stub
		return null;
	}

}
