package org.ua.uesf.service.parthner.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.ua.uesf.exception.messages.messages.NotFoundException;
import org.ua.uesf.model.Partner;
import org.ua.uesf.resository.PartnerRepository;
import org.ua.uesf.service.parthner.PartnerService;

import java.util.List;

@Service
@AllArgsConstructor
public class PartnerServiceImpl implements PartnerService {

    private final PartnerRepository partnerRepository;

    @Override
    public List<Partner> findAll() {
        return partnerRepository.findAll();
    }

    @Override
    public Partner findById(Long id) {
        if (!partnerRepository.findById(id).isPresent())
            throw new NotFoundException("Can't find partner with id: " + id);
        return partnerRepository.findById(id).get();
    }

    @Override
    public void savePartner(Partner partner) {
        partnerRepository.save(partner);
    }

    @Override
    public void deletePartnerById(Long id) {
        if (!partnerRepository.findById(id).isPresent())
            throw new NotFoundException("Can't find partner with id: " + id);
        partnerRepository.deleteById(id);
    }

    @Override
    public Partner updatePartner(Partner partner) {
        if (!partnerRepository.findById(partner.getId()).isPresent())
            throw new NotFoundException("Can't find partner with id: " + partner.getId());

        return partnerRepository.save(partner);
    }
}
