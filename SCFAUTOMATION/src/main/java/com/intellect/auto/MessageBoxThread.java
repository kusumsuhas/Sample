package com.intellect.auto;

public class MessageBoxThread extends Thread{
	public static boolean suspend= false;
	public static boolean completed= false;
	public static int count= 1;
	
	Thread mainThrtead;
	 public Thread getMainThrtead() {
		return mainThrtead;
	}
	public void setMainThrtead(Thread mainThrtead) {
		this.mainThrtead = mainThrtead;
	}
	
	public MessageBoxThread(Thread mainThrtead){
		this.mainThrtead = mainThrtead;
	}
	public void run() 
	    { 
		/*
		while(true){		
			System.out.println(" SUSPEND   :: "+count);
			if(MessageBoxThread.count > 10 && suspend){
				suspend = true;
				getMainThrtead().suspend();
			}
		 if(suspend){
			 new MessageBox(); 
			 try {
				Thread.sleep(20000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 suspend = false;
			 getMainThrtead().resume();
			 break;
		 }
		 if(completed){
			 break;
		 }
		 try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		*/
	    }
}
