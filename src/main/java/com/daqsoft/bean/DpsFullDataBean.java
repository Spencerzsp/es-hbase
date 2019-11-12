package com.daqsoft.bean;

/**
 * @author: daqsoft
 * @date: 2019/11/11
 */

public class DpsFullDataBean {

    private String id;
    private String title;
    private String releasDate;
    private String content;
    private String sourceType;
    private String sourceName;
    private String sourceUrl;
    private String digest;

    public DpsFullDataBean() {
    }

    public DpsFullDataBean(String id, String title, String releasDate, String content, String sourceType, String sourceName, String sourceUrl, String digest) {
        this.id = id;
        this.title = title;
        this.releasDate = releasDate;
        this.content = content;
        this.sourceType = sourceType;
        this.sourceName = sourceName;
        this.sourceUrl = sourceUrl;
        this.digest = digest;
    }

    @Override
    public String toString() {
        return "DpsFullDataBean{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", releasDate='" + releasDate + '\'' +
                ", content='" + content + '\'' +
                ", sourceType='" + sourceType + '\'' +
                ", sourceName='" + sourceName + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                ", digest='" + digest + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleasDate() {
        return releasDate;
    }

    public void setReleasDate(String releasDate) {
        this.releasDate = releasDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }
}
