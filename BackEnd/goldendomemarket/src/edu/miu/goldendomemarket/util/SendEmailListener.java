package edu.miu.goldendomemarket.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class SendEmailListener
 *
 */
@WebListener
public class SendEmailListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public SendEmailListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    	ServletContext sc = sce.getServletContext();
    	String smtp_host = sc.getInitParameter("smtp_host");
    	String smtp_socketFactory_port = sc.getInitParameter("smtp_socketFactory_port");
    	String smtp_port = sc.getInitParameter("smtp_port");
    	String senderEmail = sc.getInitParameter("senderEmail");
    	String senderEmailPassword = sc.getInitParameter("senderEmailPassword");
        SendEmail semail=new SendEmail(smtp_host, smtp_socketFactory_port, smtp_port, senderEmail, senderEmailPassword);
        sc.setAttribute("emailsend", semail);
    }
	
}
