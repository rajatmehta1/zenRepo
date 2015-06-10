package mdm.singleuser.command
	
{
	
	import mdm.singleuser.event.LoadDeviceDetailsEvent;   
	
	import mx.rpc.AsyncToken;
	
	
	
	//Load single device details
	
	public class LoadDeviceDetailsCommand
		
	{
		
		public function LoadDeviceDetailsCommand()
			
		{
			
		}
		
		
		
		public function execute( event_ : LoadDeviceDetailsEvent ) : AsyncToken             
			
		{
			
			_loginEvent = event_;
			
			return loginService.isValidUser(event_.userName,event_.password);
			
		}           
		
		
		
		public function result(rst : Object) : void                 
			
		{
			
			var resultStr:String = rst.toString();
			
			if("true" == resultStr) {
				
				_loginEvent.zenMobileObj.loginContainer.includeInLayout = false;
				
				_loginEvent.zenMobileObj.loginContainer.visible = false;
				
				_loginEvent.zenMobileObj.mainContentContainer.includeInLayout = true;
				
				_loginEvent.zenMobileObj.mainContentContainer.visible = true;
				
			}
				
			else {
				
				Alert.show("Please try to login again - Login Failed");
				
				_loginEvent.zenMobileObj.loginContainer.includeInLayout = true;
				
				_loginEvent.zenMobileObj.loginContainer.visible = true;
				
				_loginEvent.zenMobileObj.mainContentContainer.includeInLayout = false;
				
				_loginEvent.zenMobileObj.mainContentContainer.visible = false;                            
				
			}
			
		}
		
		
		
		public function fault(event : Object) : void                
			
		{                 
			
			Alert.show('Unable to login at this time.');                
			
		}           
		
		
		
	}
	
}

