package mdm.utils
{
	public class Utility
	{
		public function Utility()
		{
		}
		
		public static function imgUrl(deviceType:String):String {                                                                               
			if(deviceType.toLowerCase().indexOf("iphone") >= 0)                                                                  
				return "http://files.softicons.com/download/object-icons/iphone-4-icons-by-weboso/png/512/iphone4_blue.png";
			else if(deviceType.toLowerCase().indexOf("ipad") >= 0)                                                               
				return "http://rmmr.us/wp-content/uploads/2012/05/ipad3icon.jpg";		                
			else if(deviceType.toLowerCase().indexOf("android") >= 0)                                                            
				return "http://cdn-static.cnet.co.uk/i/product_media/40002360/image2/440x330-samsung-galaxy-s3-front.jpg";  
			else return "http://cdn.itproportal.com/photos/imac-21in-2012_original.jpg";                                    
		}                                                                                                                   
		
	}
}