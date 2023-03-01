package workshop;

import java.util.List;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import workshop.util.IOUtil;

@SpringBootApplication
public class WorkshopApplication {
	// private static final Logger logger = LoggerFactory.getLogger(IOUtil.class);

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(WorkshopApplication.class);
		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);
		List<String> opsVal = appArgs.getOptionValues("dataDir");
		System.out.println("Before create directory");
		if (opsVal != null) {
			System.out.println("Inside create directory");
			// logger.info("" + (String) opsVal.get(0));
			IOUtil.createDir((String) opsVal.get(0));
		} else {
			System.out.println("Exit program");
			System.exit(1);
		}

		app.run(args);
	}

}
