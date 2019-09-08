package stepdef.reg;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;

public class RegisterationSD {

	
	
	@Given("^Open SCF Application(\\d+) and login$")
	public void open_SCF_Application_and_login(int arg1) throws Throwable {
	     
	}

	@When("^I Click Regesteration shoul open screen to reg$")
	public void i_Click_Regesteration_shoul_open_screen_to_reg() throws Throwable {
		
		System.out.println(" REGISTERATION ");
		//Util.getDriver().quit();
	     
	}
}
