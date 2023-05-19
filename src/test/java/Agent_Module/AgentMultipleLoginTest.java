package Agent_Module;



import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Convox.GenericLibraries.BaseClass;
import Convox.GenericLibraries.XLUtility;
import Convox.ObjectRepository.AgentModule.AgentLoginPage;

@Listeners(Convox.GenericLibraries.ListenersImplementationLibrary.class)
public class AgentMultipleLoginTest extends BaseClass{
	
	@Test(dataProvider = "LoginData")
	public void MultipleLogin(String USERNAME,String PASSWORD,String STATION) throws Throwable
	{
		AgentLoginPage alp = new AgentLoginPage(driver);
		alp.LoginToAgentModule(USERNAME, PASSWORD, STATION);
		Reporter.log("Runnning Thread: "+Thread.currentThread().getId(),true);
		Reporter.log(USERNAME+" --- Logged Into Agent Module Succesfully --- ",true);
	}
	
	@DataProvider(name = "LoginData")
	public String [][] getMultipleusername() throws Throwable
	{
		//get the data from excel
		String path=".\\src\\test\\resources\\Agent Login.xlsx";
		XLUtility xlutil=new XLUtility(path);

		int totalrows=xlutil.getRowCount("Sheet1");
		int totalcols=xlutil.getCellCount("Sheet1",1);	

		String loginData[][]=new String[totalrows][totalcols];


		for(int i=1;i<=totalrows;i++) //1
		{
			for(int j=0;j<totalcols;j++) //0
			{
 				loginData[i-1][j]=xlutil.getCellData("Sheet1", i, j);
			}

		}

		return loginData;
}
}
