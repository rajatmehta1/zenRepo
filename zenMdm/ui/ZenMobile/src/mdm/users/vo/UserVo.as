package mdm.users.vo
	
{
	
	import mx.collections.ArrayCollection;
	
	
	
	public class UserVo
		
	{
		
		public var name:String;
		
		public var firstName:String;
		
		public var lastName:String;
		
		public var email:String;
		
		public var phone:String;
		
		public var deviceList:ArrayCollection; // Strings containing device Ids and Type , LightDeviceVo
		
		public var fullDeviceList:ArrayCollection; // DeviceVO
		
		public var userId:String; // unique id of the user
		
		public var department:String;
		
		
		
		public function UserVo()
			
		{
			
		}
		
	}
	
}