/* Nivicos - 09/07/2012*/
package com.android.arduinocom;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.example.arduinocom.R;


public class MainActivity extends Activity 
{

	TextView txt;
	private static final int VID = 0x2341; //Vendor ID
	private static final int PID = 0x0043; //Product ID
	
	private static UsbController sUsbController;
	//Remote Control Codes
	private static int POWER    =	      		  0x20DF10EF;
	private static int MUTE 	=	   			  0x20DF906F;
	private static int n1       =                 0x20DF8877;
	private static int n2       =                 0x20DF48B7;
	private static int n3       =                 0x20DFC837;
	private static int n4       =                 0x20DF28D7;
	private static int n5       =                 0x20DFA857;
	private static int n6       =                 0x20DF6897;
	private static int n7       =                 0x20DFE817;
	private static int n8       =                 0x20DF18E7;
	private static int n9       =                 0x20DF9867;
	private static int n0       =                 0x20DF08F7;
	private static int TVVIDEO  =                 0x20DFD02F;
	private static int MENU     =                 0x20DFC23D;
	private static int VOLMINUS =                 0x20DFC03F;
	private static int VOLPLUS  =                 0x20DF40BF;
	private static int CHMINUS  =                 0x20DF807F;
	private static int CHPLUS   =                 0x20DF00FF;
	private static int ENTER    =                 0x20DF22DD;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt = (TextView) findViewById(R.id.textView1);
        txt.setText("STRING");
        L.e("TESTE!!!");
         
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void ButtonClick(View v) 
    {
    	switch (v.getId()) 
    	{
			case (R.id.btConnect):
			{ 
				if(sUsbController == null)
				{
			        sUsbController = new UsbController(this, mConnectionHandler, VID, PID);
			        if (sUsbController != null)
			        {
			        	L.e("Conectou");
			        }
	
				}    
				break;
				
			}
			case (R.id.btPower):			
			{
				if (sUsbController != null) sendByte(POWER);
				break;
			}
			case (R.id.bt0):			
			{
				if (sUsbController != null) sendByte(n0);
				break;
			}
			case (R.id.bt1):			
			{
				if (sUsbController != null) sendByte(n1);
				break;
			}
			case (R.id.bt2):			
			{
				if (sUsbController != null) sendByte(n2);
				break;
			}
			case (R.id.bt3):			
			{
				if (sUsbController != null) sendByte(n3);
				break;
			}
			case (R.id.bt4):			
			{
				if (sUsbController != null) sendByte(n4);
				break;
			}
			case (R.id.bt5):			
			{
				if (sUsbController != null) sendByte(n5);
				break;
			}
			case (R.id.bt6):			
			{
				if (sUsbController != null) sendByte(n6);
				break;
			}
			case (R.id.bt7):			
			{
				if (sUsbController != null) sendByte(n7);
				break;
			}
			case (R.id.bt8):			
			{
				if (sUsbController != null) sendByte(n8);
				break;
			}
			case (R.id.bt9):			
			{
				if (sUsbController != null) sendByte(n9);
				break;
			}
			case (R.id.btCHDown):			
			{
				if (sUsbController != null) sendByte(CHMINUS);
				break;
			}
			case (R.id.btCHUp):			
			{
				if (sUsbController != null) sendByte(CHPLUS);
				break;
			}
			case (R.id.btEnter):			
			{
				if (sUsbController != null) sendByte(ENTER);
				break;
			}
			case (R.id.btMenu):			
			{
				if (sUsbController != null) sendByte(MENU);
				break;
			}
			case (R.id.btMute):			
			{
				if (sUsbController != null) sendByte(MUTE);
				break;
			}
			case (R.id.btVideo):			
			{
				if (sUsbController != null) sendByte(TVVIDEO);
				break;
			}
			case (R.id.btVolDown):			
			{
				if (sUsbController != null) sendByte(VOLMINUS);
				break;
			}
			case (R.id.btVolUp):			
			{
				if (sUsbController != null) sendByte(VOLPLUS);
				break;
			}
			default:
				break;
		}		

	}
    
    
    public void sendByte(int iCode)
    {
    	
    	byte data = 0;
    	data = (byte)(iCode>>24); //shift bits
    	sUsbController.send(data); //send to arduino
    	try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//L.d(data);    	
    	data = 0;
    	data = (byte)(iCode>>16);
    	
    	sUsbController.send(data);
    	try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	L.d(data);    	
    	data = 0;
    	data = (byte)(iCode>>8); 
    	sUsbController.send(data);
    	try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//L.d(data);
    	data = 0;
    	data = (byte)(iCode); 
    	sUsbController.send(data);
    	L.d(data);
    	
    }
    
    	
    	
    	private final IUsbConnectionHandler mConnectionHandler = new IUsbConnectionHandler() {
    		@Override
    		public void onUsbStopped() {
    			L.e("Usb stopped!");
    		}
    		
    		@Override
    		public void onErrorLooperRunningAlready() {
    			L.e("Looper already running!");
    		}
    		
    		@Override
    		public void onDeviceNotFound() {
    			if(sUsbController != null){
    				sUsbController.stop();
    				sUsbController = null;
    			}
    		}
    	};    	
}
