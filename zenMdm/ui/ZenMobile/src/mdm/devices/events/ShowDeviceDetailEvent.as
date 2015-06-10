package mdm.devices.events
	
{
	
	import flash.events.Event;         
	
	import mdm.devices.vo.DeviceVo;
	
	
	
	public class ShowDeviceDetailEvent extends Event
		
	{
		
		public static var EVENT_ACTION : String = "ShowDeviceDetailEvent";                     
		
		public var deviceVO:DeviceVo;
		
		
		
		public function ShowDeviceDetailEvent()
			
		{
			
			super(EVENT_ACTION);
			
		}
		
		
		
	}
	
	
	
}
