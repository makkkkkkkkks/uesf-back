package org.ua.uesf.service.contact.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.ua.uesf.exception.messages.AlreadyExistException;
import org.ua.uesf.exception.messages.NotFoundException;
import org.ua.uesf.model.Contact;
import org.ua.uesf.resository.ContactRepository;
import org.ua.uesf.service.contact.ContactService;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    @Override
    public List<Contact> getAllContacts(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Contact> contacts = contactRepository.findAll(paging);
        return contacts.getContent();
    }

    @Override
    public void saveContact(Contact contact) {
        if (contact.getId() != null) {
            throw new AlreadyExistException("Contact with id: " + contact.getId() + "already exist");
        }
        contactRepository.save(contact);
    }

    @Override
    public void deleteContactById(Long id) {
        if (!contactRepository.findById(id).isPresent()) {
            throw new NotFoundException("Can't find contact with id:" + id);
        }
        contactRepository.deleteById(id);
    }

    @Override
    public Contact findContactById(Long id) {
        return contactRepository.findById(id).orElseThrow(() -> new NotFoundException("Can't find contact with id:" + id));
    }

    @Override
    public void updateContact(Long id, Contact contact) {
        if (!contactRepository.findById(id).isPresent()) {
            throw new NotFoundException("Can't find contact with id:" + id);
        }
        Contact contactFromDb = contactRepository.findById(id).get();
        if (Objects.nonNull(contact.getPicture()) && !"".equalsIgnoreCase(contact.getPicture())) {
            contactFromDb.setBusinessPosition(contact.getPicture());
        }
        if (Objects.nonNull(contact.getName()) && !"".equalsIgnoreCase(contact.getName())) {
            contactFromDb.setBusinessPosition(contact.getName());
        }
        if (Objects.nonNull(contact.getBusinessPosition()) && !"".equalsIgnoreCase(contact.getBusinessPosition())) {
            contactFromDb.setBusinessPosition(contact.getBusinessPosition());
        }
        if (Objects.nonNull(contact.getFacebookUrl()) && !"".equalsIgnoreCase(contact.getFacebookUrl())) {
            contactFromDb.setBusinessPosition(contact.getFacebookUrl());
        }
        if (Objects.nonNull(contact.getTwitterUrl()) && !"".equalsIgnoreCase(contact.getTwitterUrl())) {
            contactFromDb.setBusinessPosition(contact.getTwitterUrl());
        }
        if (Objects.nonNull(contact.getPersonalInformation()) && !"".equalsIgnoreCase(contact.getPersonalInformation())) {
            contactFromDb.setBusinessPosition(contact.getPersonalInformation());
        }
        contactRepository.save(contact);
    }
}
