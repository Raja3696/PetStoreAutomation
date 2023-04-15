package api.utilities;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FileDelete {

	public Logger logger;
	
	public void fileDelete(String path) {
		logger = LogManager.getLogger(this.getClass());
		File file = new File(path);
			file.delete();
			logger.info("Log file deleted successfully");
	}
//	public static void main(String[] args) {
//		fileDelete(System.getProperty("user.dir")+"//logs//pop.log");
//	}
}
