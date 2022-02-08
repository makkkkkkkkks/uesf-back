package org.ua.uesf.resository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.ua.uesf.model.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
