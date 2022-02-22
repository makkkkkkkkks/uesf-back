package org.ua.uesf.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ua.uesf.exception.messages.NotFoundException;
import org.ua.uesf.model.Contact;
import org.ua.uesf.service.contact.ContactService;

import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
public class ContactController {
    private final ContactService contactService;


    @GetMapping("/contacts")
    public ResponseEntity<List<Contact>> getContacts(@RequestParam(defaultValue = "0") int page,
                                                     @RequestParam(defaultValue = "10") int size) {
        List<Contact> contacts = contactService.getAllContacts(page, size);
        return new ResponseEntity<>(contacts, HttpStatus.OK);
    }

    @GetMapping("/contacts/{id}")
    public Contact getContactById(@PathVariable("id") long id) {
        return contactService.findContactById(id);
    }

    @PostMapping("/contact")
    public ResponseEntity<Void> saveContact(@RequestBody Contact contact) {
        contactService.saveContact(contact);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/contact/{id}")
    public void deleteContactyId(@PathVariable String id) {
        contactService.deleteContactById(Long.parseLong(id));
    }

    @PutMapping("/contact/{id}")
    public void updateBContact(@RequestBody Contact contact,
                               @PathVariable("id") Long id) {
        if (!Objects.equals(contact.getId(), id))
            throw new NotFoundException("The id's do not match " + contact.getId() + " " + id);
        contactService.updateContact(id, contact);
    }
}
