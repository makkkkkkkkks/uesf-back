package org.ua.uesf.service.parthner;

import org.springframework.stereotype.Service;
import org.ua.uesf.model.Partner;

import java.util.List;

@Service
public interface PartnerService {

    List <Partner> findAll();

    Partner findById(Long id);

    void savePartner(Partner partner);

    void deletePartnerById(Long id);

    Partner updatePartner(Partner partner);

}
