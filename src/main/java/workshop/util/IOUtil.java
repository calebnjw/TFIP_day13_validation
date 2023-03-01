package workshop.util;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

// import org.slf4j.LoggerFactory;

public class IOUtil {
  // import org.slf4j.Logger;
  // private static final Logger logger = LoggerFactory.getLogger(IOUtil.class);

  // create directory on machine
  public static void createDir(String path) {
    File dir = new File(path);
    Boolean isDirExist = dir.mkdir();
    // logger.info("Directory exists? " + isDirExist);

    if (isDirExist) {
    }

    String osName = System.getProperty("os.name");

    if (!osName.contains("Windows")) {
      String permission = "rwxrwx---";
      Set<PosixFilePermission> permissions = PosixFilePermissions.fromString(permission);
      try {
        Files.setPosixFilePermissions(dir.toPath(), permissions);
      } catch (Exception e) {
        e.printStackTrace();
        // logger.error("Error", e);
      }
    }
  }
}
