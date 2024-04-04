package com.lezhin.api.search.model;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class CategoryResponse {
    private String display_category_id;
    private String display_category_name;
    private String upper_category_id;
    private String icon_img_url;
}
