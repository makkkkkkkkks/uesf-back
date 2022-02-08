package org.ua.uesf.service.contact;

import org.ua.uesf.model.Contact;

import java.util.List;

public interface ContactService {

    List<Contact> getAllContacts(Integer page, Integer size);

    void saveContact(Contact contact);

    void deleteContactById(Long id);

    Contact findContactById(Long id);

    void updateContact(Long id, Contact contact);
}
