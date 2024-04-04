package com.lezhin.api.search.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Category {
    private String display_category_id;
    private String display_category_name;
    private String upper_display_category_id;
    private String icon_img_url;
    private String depth_no;
    private String best_disp_yn;
    private String brand_ctrl_yn;
    private String best_icon_img_url;
    private String best_icon_img_url_disabled;
    private String theme_yn;
    private String leaf_yn;
    private Injected injected;

}

