package mdm.devices.vo
	
{
	
	import mx.collections.ArrayCollection;
	
	
	
	public class DeviceVo
		
	{
		
		public var name:String;
		
		public var deviceType:String;
		
		public var deviceId:int;
		
		public var os:String;
		
		public var osVersion:String;
		
		public var userId:String;
		
		public var apps:ArrayCollection; //installed Apps
		
		public var deviceModel:String;
		
		public var phoneNumber:String;
		
		public var enrollDate:Date;
		
		public var status:String;
		
		public var imgUrl:String;
		
		public function DeviceVo()
			
		{
			
		}












	}
	
}
