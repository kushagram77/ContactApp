package com.yash.capp.controller;

import com.yash.capp.domain.Contact;
import com.yash.capp.service.ContactService;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Kushagra Mishra
 */
@Controller
public class ContactController {

    @Autowired
    private ContactService contactService;
                //prepare Contact save form

    /*
    *
    *   Purpose: Displays the contact form for creating a new contact.
        Model: A new Contact object is created and added to the model with the attribute name "command".
        Return: Returns the view name "contact_form",
    *
    *
    * */
    @RequestMapping(value = "/user/contact_form")
    public String contactForm(Model m) {
        Contact contact = new Contact();
        m.addAttribute("command", contact);
        return "contact_form";//JSP form view
    }
                //prepare contact EditForm:

    /*
   *
   *   Purpose: Prepares the form for editing an existing contact.
        Parameters: Takes in contactId as a request parameter and retrieves the corresponding Contact object using the contactService.
        Session: The contactId is stored in the session for later use.
        Returns: returns a contact form view which is pre-populated
   *
   *
   * */
    @RequestMapping(value = "/user/edit_contact")
    public String prepareEditForm(Model m, HttpSession session, @RequestParam("cid") Integer contactId) {
        session.setAttribute("aContactId", contactId);
        Contact c = contactService.findById(contactId);
        m.addAttribute("command", c);
        return "contact_form";//JSP form view
    }

    /*
    *
    *

    Purpose: Handles both saving a new contact and updating an existing one.
    Parameters: Uses @ModelAttribute to bind the incoming form data to a Contact object.
    Logic:
        Checks if contactId is present in the session:
             If not, it saves a new contact. The user ID is also set as a foreign key.
             If present, it updates the existing contact.
    Error Handling: Catches exceptions and adds error messages to the model if saving or updating fails.
    Return: Redirects to the contact list page after a successful save or update.

    *
    * */

    @RequestMapping(value = "/user/save_contact")
    public String saveOrUpdateContact(@ModelAttribute("command") Contact c, Model m, HttpSession session) {
        Integer contactId = (Integer) session.getAttribute("aContactId");
        if (contactId == null) {
            //save task
            try {
                Integer userId = (Integer) session.getAttribute("userId");
                c.setUserId(userId);//FK ; logged in userId
                contactService.save(c);
                return "redirect:clist?act=sv";//redirect to contact list
            } catch (Exception e) {
                e.printStackTrace();
                m.addAttribute("err", "Failed to save contact");
                return "contact_form";
            }
        } else {
            //update task
            try {
                c.setContactId(contactId); //PK
                contactService.update(c);
                session.removeAttribute("aContactId");
                return "redirect:clist?act=ed";//redirect user to contact list url
            } catch (Exception e) {
                e.printStackTrace();
                m.addAttribute("err", "Failed to Edit contact");
                return "contact_form";
            }
        }
    }

    /*
    *
    *   Purpose: Displays a list of contacts for the logged-in user.
        Parameters: Retrieves the userId from the session and fetches the user's contacts.
        Return: Returns the view name "clist".

    *
    *
    * */

    @RequestMapping(value = "/user/clist")
    public String contactList(Model m, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        m.addAttribute("contactList", contactService.findUserContact(userId));
        return "clist"; //JSP
    }


    /*
    *
      Purpose: Searches for contacts based on a free text search.
      Parameters: Takes freeText as a request parameter and retrieves matching contacts.
      Return: Returns the view name "clist".

    * */
    @RequestMapping(value = "/user/contact_search")
    public String contactSearch(Model m, HttpSession session, @RequestParam("freeText") String freeText) {
        Integer userId = (Integer) session.getAttribute("userId");
        m.addAttribute("contactList", contactService.findUserContact(userId, freeText));
        return "clist"; //JSP
    }

    @RequestMapping(value = "/user/del_contact")
    public String deleteContact(@RequestParam("cid") Integer contactId) {
        contactService.delete(contactId);
        return "redirect:clist?act=del";
    }

    @RequestMapping(value = "/user/bulk_cdelete")
    public String deleteBulkContact(@RequestParam("cid") Integer[] contactIds) {
        contactService.delete(contactIds);
        return "redirect:clist?act=del";
    }
}
