package com.daqsoft.bean;

/**
 * @author: daqsoft
 * @date: 2019/11/12
 */

public class DpsDataBean {

    private String id;
    private String status;
    private String sourceId;
    private String involvedWord;
    private String indexType;
    private String sensibility;
    private String emotion;
    private String isContent;
    private String isTitle;
    private String title;
    private String releasDate;
    private String content;
    private String sourceType;
    private String sourceName;
    private String sourceUrl;
    private String digest;

    public DpsDataBean() {
    }

    public DpsDataBean(String id, String status, String sourceId, String involvedWord, String indexType, String sensibility, String emotion, String isContent, String isTitle, String title, String releasDate, String content, String sourceType, String sourceName, String sourceUrl, String digest) {
        this.id = id;
        this.status = status;
        this.sourceId = sourceId;
        this.involvedWord = involvedWord;
        this.indexType = indexType;
        this.sensibility = sensibility;
        this.emotion = emotion;
        this.isContent = isContent;
        this.isTitle = isTitle;
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
        return "DpsDataBean{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", sourceId='" + sourceId + '\'' +
                ", involvedWord='" + involvedWord + '\'' +
                ", indexType='" + indexType + '\'' +
                ", sensibility='" + sensibility + '\'' +
                ", emotion='" + emotion + '\'' +
                ", isContent='" + isContent + '\'' +
                ", isTitle='" + isTitle + '\'' +
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getInvolvedWord() {
        return involvedWord;
    }

    public void setInvolvedWord(String involvedWord) {
        this.involvedWord = involvedWord;
    }

    public String getIndexType() {
        return indexType;
    }

    public void setIndexType(String indexType) {
        this.indexType = indexType;
    }

    public String getSensibility() {
        return sensibility;
    }

    public void setSensibility(String sensibility) {
        this.sensibility = sensibility;
    }

    public String getEmotion() {
        return emotion;
    }

    public void setEmotion(String emotion) {
        this.emotion = emotion;
    }

    public String getIsContent() {
        return isContent;
    }

    public void setIsContent(String isContent) {
        this.isContent = isContent;
    }

    public String getIsTitle() {
        return isTitle;
    }

    public void setIsTitle(String isTitle) {
        this.isTitle = isTitle;
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
