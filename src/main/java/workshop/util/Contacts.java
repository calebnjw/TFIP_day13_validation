package workshop.util;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import workshop.model.Contact;

@Component("contacts")
public class Contacts {

  public void saveContact(Contact contact, Model model, ApplicationArguments appArgs, String defaultDataDir) {
    String fileName = contact.getId();
    PrintWriter pw = null;

    try {
      FileWriter fw = new FileWriter(getDataDir(appArgs, defaultDataDir) + "/" + fileName);
      pw = new PrintWriter(fw);
      pw.println(contact.getName());
      pw.println(contact.getEmail());
      pw.println(contact.getPhoneNumber());
      pw.println(contact.getDateOfBirth());
      pw.println(contact.getAge());
      pw.close();
    } catch (Exception e) {
      e.printStackTrace();
    }

    model.addAttribute("contact", new Contact(
        contact.getId(),
        contact.getName(),
        contact.getEmail(),
        contact.getPhoneNumber(),
        contact.getDateOfBirth()));
  }

  private String getDataDir(ApplicationArguments appArgs, String defaultDataDir) {
    String dataDirResult = "";
    List<String> optVals = null;
    String[] optValsArray = null;
    Set<String> optNames = appArgs.getOptionNames();
    String[] optNamesArray = optNames.toArray(new String[optNames.size()]);

    if (optNamesArray.length > 0) {
      optVals = appArgs.getOptionValues(optNamesArray[0]);
      optValsArray = optVals.toArray(new String[optVals.size()]);
      dataDirResult = optValsArray[0];
    } else {
      dataDirResult = defaultDataDir;
    }
    return dataDirResult;
  }
}
