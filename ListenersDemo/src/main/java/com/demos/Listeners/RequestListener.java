package com.demos.Listeners;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class RequestListener implements ServletRequestListener {

    public RequestListener() {
        // TODO Auto-generated constructor stub
    }

    public void requestDestroyed(ServletRequestEvent sre)  { 
         System.out.println("Request destroying...");
    }

    public void requestInitialized(ServletRequestEvent sre)  { 
         System.out.println("Request received...");
    }
	
}
