package org.ua.uesf.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ua.uesf.exception.NotFoundException;
import org.ua.uesf.model.News;
import org.ua.uesf.model.Partner;
import org.ua.uesf.model.dto.GeneralNewsDTO;
import org.ua.uesf.model.dto.NewsDTO;
import org.ua.uesf.service.parthner.PartnerService;

import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
public class PartnerController {
    private final PartnerService partnerService;

    @GetMapping("/partners")
    public ResponseEntity<List<Partner>> getPartner() {
        List<Partner> partners = partnerService.findAll();
        return new ResponseEntity<>(partners, HttpStatus.OK);
    }

    @GetMapping("/partner/{id}")
    public Partner getPartnerById(@PathVariable("id") long id) {
        return partnerService.findById(id);
    }

    @PostMapping("/partners")
    public ResponseEntity<Void> savePartner(@RequestBody Partner partner) {
        partnerService.savePartner(partner);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/partner/{id}")
    public void deletePartnerById(@PathVariable String id) {
        partnerService.deletePartnerById(Long.parseLong(id));
    }

    @PutMapping("/partner/{id}")
    public Partner updatePartner(@RequestBody Partner partner,
                                 @PathVariable("id") Long id) {
        if (!Objects.equals(partner.getId(), id))
            throw new NotFoundException("The id's do not match " + partner.getId() + " " + id);
        return partnerService.updatePartner(partner);
    }

}
