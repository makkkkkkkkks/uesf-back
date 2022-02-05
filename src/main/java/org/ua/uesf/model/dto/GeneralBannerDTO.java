package org.ua.uesf.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GeneralBannerDTO {

    Long id;
    String image;
    String link;
    String orderNumber;
    String title;
}
