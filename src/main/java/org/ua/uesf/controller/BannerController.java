package org.ua.uesf.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.ua.uesf.exception.NotFoundException;
import org.ua.uesf.model.Banner;
import org.ua.uesf.service.banner.BannerService;

import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
public class BannerController {
    private final BannerService bannerService;

    @GetMapping("/banners")
    public ResponseEntity<List<Banner>> getPartner(@RequestParam(defaultValue = "0") int page,
                                                   @RequestParam(defaultValue = "10") int size) {
        List<Banner> partners = bannerService.findBanner(page, size);
        return new ResponseEntity<>(partners, HttpStatus.OK);
    }

    @GetMapping("/banners/{id}")
    public Banner getBannerById(@PathVariable("id") long id) {
        return bannerService.findById(id);
    }

    @PostMapping("/banner")
    public ResponseEntity<Void> saveBanner(@RequestBody Banner banner) {
        bannerService.saveBanner(banner);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/banners/{id}")
    public void deleteBannerById(@PathVariable String id) {
        bannerService.deleteBannerById(Long.parseLong(id));
    }

    @PutMapping("/banners/{id}")
    public void updateBanner(@RequestBody Banner banner,
                             @PathVariable("id") Long id) {
        if (!Objects.equals(banner.getId(), id))
            throw new NotFoundException("The id's do not match " + banner.getId() + " " + id);
        bannerService.update(id, banner);
    }

}

