package com.srg.exchange.vo;

/**
 * Created by srg
 *
 * @date 2018/5/31
 */
public class PhotoVo {
    private Integer viewId;

    private String url;

    public PhotoVo(Integer viewId, String url) {
        this.viewId = viewId;
        this.url = url;
    }

    public Integer getViewId() {
        return viewId;
    }

    public void setViewId(Integer viewId) {
        this.viewId = viewId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
