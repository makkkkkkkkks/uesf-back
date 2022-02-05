package org.ua.uesf.service.banner;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.ua.uesf.model.Banner;
import org.ua.uesf.model.News;
import org.ua.uesf.model.dto.GeneralBannerDTO;
import org.ua.uesf.resository.BannerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BannerServiceImpl implements BannerService {
    private final BannerRepository bannerRepository;

    @Override
    public List<GeneralBannerDTO> findBanner(String locale, Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size);
        Page<Banner> news = bannerRepository.findAll(paging);
        List<GeneralBannerDTO> generalBannerDTOS = null;
        if ("UA".equals(locale)) {
          //  generalBannerDTOS = news.getContent().stream().map(newsMapper::dtoUA).collect(Collectors.toList());
        }
        if ("EN".equals(locale)) {
           // generalBannerDTOS = news.getContent().stream().map(newsMapper::dtoUA).collect(Collectors.toList());
        }
        return generalBannerDTOS;
    }

    @Override
    public void saveBanner(Banner banner) {

    }

    @Override
    public void deleteBannerById(Long id) {

    }

    @Override
    public Banner findById(Long id) {
        return null;
    }

    @Override
    public Banner update(Long id, News news) {
        return null;
    }
}
