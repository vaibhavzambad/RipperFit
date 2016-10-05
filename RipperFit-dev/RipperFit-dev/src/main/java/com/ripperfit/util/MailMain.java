package com.ripperfit.util;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import com.ripperfit.model.ReceiverVO;
import com.ripperfit.model.SenderVO;

/**
 * mail class to show the signatures of the object used in mail utility class
 * this class shows the way to send parameters in the mail api
 */
public class MailMain {

public static void main(String[] args) {
		
		SenderVO from = new SenderVO();
		from.setSender("amit.kumar@metacube.com");				//sender's email address
		from.setPassword("");									//sender's password

		ReceiverVO to = new ReceiverVO();
		
		/*WAY 1*/
			//using list
			List<String> list = new ArrayList<String>();
			list.add("vaibhav.zambad@metacube.com");
			list.add("riya.nuwal@metacube.com");
			list.add("amita.sharma@metacube.com");
			list.add("shobhit.agarwal@metacube.com");
			to.setReceiver(list);

		ReceiverVO cc = new ReceiverVO();
		/*WAY 2*/
			//not using list
			cc.setReceiver("amit.kumar@metacube.com");
			cc.setReceiver("riya.nuwal@metacube.com");
			cc.setReceiver("amita.sharma@metacube.com");
			cc.setReceiver("shobhit.agarwal@metacube.com");
		
		String subject = "hello";
		String body = "<h1>Mail using java</h1>";
		String result = "";
		try {
			result = MailUtility.mail(subject, body, from, to, cc);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		System.out.println(result);;
	}
}