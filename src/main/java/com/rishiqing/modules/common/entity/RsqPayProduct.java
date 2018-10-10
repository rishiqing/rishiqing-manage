package com.rishiqing.modules.common.entity;

/**
 * 产品信息
 1	0	13	1	149	日事清专业版
 2	0	14	0	1299	日事清高级专业版
 3	0	15	1	0	日事清试用专业版
 4	0	16	1	249	日事清企业版
 5	0	17	0	2999	日事清高级企业版
 6	0	18	1	0	日事清试用企业版
 */
public class RsqPayProduct {

    //id
    private Integer id;
    // 价格 : 149    249    1299     2999      0         0
    private Double price;
    // 说明 : 专业版，企业版，专业高级版，企业高级版，试用专业版，试用企业版
    private String description;
    // 此产品是否可以被购买
    private Boolean canBuy;
    // 公司会员类型
    private Integer teamVersionId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCanBuy() {
        return canBuy;
    }

    public void setCanBuy(Boolean canBuy) {
        this.canBuy = canBuy;
    }

    public Integer getTeamVersionId() {
        return teamVersionId;
    }

    public void setTeamVersionId(Integer teamVersionId) {
        this.teamVersionId = teamVersionId;
    }
}
