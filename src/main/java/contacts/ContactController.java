package contacts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;

/**
 * Created by lusai on 2016/7/8.
 */
@Controller
@RequestMapping("/")
public class ContactController {

    private ContactRepository contactRepository;

    @Autowired
    public ContactController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String home(Map<String, Object> model) {
        List<Contact> contacts = contactRepository.findAll();
        model.put("contacts", contacts);
        return "home";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String Submit(Contact contact) {
        contactRepository.save(contact);
        return "redirect:/";
    }

}
