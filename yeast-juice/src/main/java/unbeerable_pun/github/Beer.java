package unbeerable_pun.github;

import java.util.Date;

public abstract class Beer {

    int id;
    int categoryId;
    BeerCategory category;
    String name;
    String shortName;
    String description;
    float ibuMin;
    float ibuMax;
    float abvMin;
    float abvMax;
    float srmMin;
    float srmMax;
    float ogMin;
    float fgMin;
    float fgMax;
    Date createDate;
    Date updateDate;
}
