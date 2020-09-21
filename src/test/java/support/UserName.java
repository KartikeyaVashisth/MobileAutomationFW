package support;



public class UserName {
	
	public String stage_ios ;
	public String stage_android ;
	public String prod_ios ;
	public String prod_android ;
	
	public String getUserName () throws Exception{
		
		Helper h = new Helper();
		
		return (String)UserName.class.getField(h.getEnv()+"_"+h.getEngine()).get(this);
		
	}

}
