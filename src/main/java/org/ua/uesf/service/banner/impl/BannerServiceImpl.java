package org.ua.uesf.service.banner.impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.ua.uesf.exception.messages.messages.NotFoundException;
import org.ua.uesf.model.Banner;
import org.ua.uesf.resository.BannerRepository;
import org.ua.uesf.service.banner.BannerService;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepository;

    @Override
    public List<Banner> findBanner(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Banner> banners = bannerRepository.findAll(paging);
        return banners.getContent();
    }

    @Override
    public void saveBanner(Banner banner) {
        bannerRepository.save(banner);
    }

    @Override
    public void deleteBannerById(Long id) {
        if (!bannerRepository.findById(id).isPresent()) {
            throw new NotFoundException("Can't find banner with id:" + id);
        }
        bannerRepository.deleteById(id);
    }

    @Override
    public Banner findById(Long id) {
        if (!bannerRepository.findById(id).isPresent()) {
            throw new NotFoundException("Can't find banner with id:" + id);
        }
        return bannerRepository.findById(id).get();
    }

    @Override
    public void update(Long id, Banner banner) {

        if (!bannerRepository.findById(id).isPresent()) {
            throw new NotFoundException("Can't find banner with id:" + id);
        }

        Banner bannerFromDB = bannerRepository.findById(id).get();

        if (Objects.nonNull(banner.getLink()) && !"".equalsIgnoreCase(banner.getLink())) {
            bannerFromDB.setLink(banner.getLink());
        }
        if (Objects.nonNull(banner.getImage()) && !"".equalsIgnoreCase(banner.getImage())) {
            bannerFromDB.setLink(banner.getImage());
        }
        if (Objects.nonNull(banner.getOrderNumber()) && !"".equalsIgnoreCase(banner.getOrderNumber())) {
            bannerFromDB.setLink(banner.getOrderNumber());
        }
        if (Objects.nonNull(banner.getBannerPosition()) && !"".equalsIgnoreCase(String.valueOf(banner.getBannerPosition()))) {
            bannerFromDB.setBannerPosition(banner.getBannerPosition());
        }

        bannerRepository.save(banner);
    }
}
