package org.ua.uesf.service.banner;

import org.ua.uesf.model.Banner;

import java.util.List;

public interface BannerService {

    List<Banner> findBanner(Integer page, Integer size);

    void saveBanner(Banner banner);

    void deleteBannerById(Long id);

    Banner findById(Long id);

    void update(Long id, Banner banner);
}
