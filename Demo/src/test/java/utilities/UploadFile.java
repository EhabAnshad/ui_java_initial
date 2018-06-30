package utilities;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;


/**
 * This Class to handle Upload operation out side of the DOM with Robot Framework in Selenium.
 */
public class UploadFile 
{
	Robot robot;
	String campaignImge = "";
	String operatingSystemName;
	StringSelection stringSelect;

	public void Upload(String file) 
	{
		campaignImge = System.getProperty("user.dir") + File.separator + "resources" + File.separator + file;
		stringSelect = new StringSelection(campaignImge);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelect, null);
		try {
			robot = new Robot();
			robot.delay(1000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_A);
			robot.keyPress(KeyEvent.VK_DELETE);
			robot.delay(1000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.delay(2000);
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.keyRelease(KeyEvent.VK_ENTER);
			robot.delay(2000);			
		} catch (AWTException rebotException) {
			rebotException.printStackTrace();
		}
	}
}
