package org.ua.uesf.service.banner;

import org.ua.uesf.model.Banner;
import org.ua.uesf.model.News;
import org.ua.uesf.model.dto.GeneralBannerDTO;

import java.util.List;

public interface BannerService {

    List<GeneralBannerDTO> findBanner(String locale, Integer page, Integer size);

    void saveBanner(Banner banner);

    void deleteBannerById(Long id);

    Banner findById(Long id);

    Banner update(Long id, News news);
}
