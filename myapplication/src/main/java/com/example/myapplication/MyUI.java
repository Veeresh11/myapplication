package com.example.myapplication;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.ui.Transport;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Push(transport = Transport.LONG_POLLING)
public class MyUI extends UI {
	
	String my_user_id = "1234";
	int myCount = 1;
	boolean isThreadRunning = false;
	
	Navigator navigator;
	
    @Override
    protected void init(VaadinRequest vaadinRequest) {
    	
    	// Create a navigator to control the views
        navigator = new Navigator(this, this);
        navigator.addView("dashboard", new UserDashBoard());
    	
        
        
        final VerticalLayout layout = new VerticalLayout();
        
        final TextField name = new TextField();
        name.setCaption("Type your name here:");
        
        Button button = new Button("Click Me");
        button.addClickListener( e -> {
            layout.addComponent(new Label("Thanks " + name.getValue() 
                    + ", it works!"));
            
            new FeederThread().start();
            getUI().getCurrent().getNavigator().navigateTo("dashboard");
        });
        
        layout.addComponents(name, button);
        layout.setMargin(true);
        layout.setSpacing(true);
        
        setContent(layout);
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    	
    }
    
    
    class FeederThread extends Thread {
        int count = 0;

        @Override
        public void run() {
        	isThreadRunning = true;
        	User.getInstance().isProcessRunning = true;
            try {
                // Update the data for a while
                while (count < 100) {
                    Thread.sleep(250);

                    access(new Runnable() {
                        @Override
                        public void run() {
                            count++;                            
                        }
                    });
                }

                // Inform that we have stopped running
                access(new Runnable() {
                    @Override
                    public void run() {
                        myCount = count;
                        isThreadRunning = false;
                        User.getInstance().isProcessRunning = false;
                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            isThreadRunning = false;
        }
    }
    
}
