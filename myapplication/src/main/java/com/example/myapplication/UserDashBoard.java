package com.example.myapplication;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.FormLayout;

public class UserDashBoard extends FormLayout implements View {
	
	private User userDetails;
	
	@Override
	public void enter(ViewChangeEvent event) {
		
		if(userDetails.isProcessRunning == true){
			
		}else if(userDetails.isProcessRunning == false){
			
		}
		
	}
	
	public UserDashBoard(){
		
				
	}
		
}	
