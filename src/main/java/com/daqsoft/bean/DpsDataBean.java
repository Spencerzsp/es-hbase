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
    private String sourceType;
    private String indexType;
    private String sourceName;
    private String sensibility;
    private String emotion;
    private String isContent;
    private String isTitle;
    private String title;
    private String releasDate;
    private String content;
    private String sourceAccounts;
    private String author;
    private String getDate;
    private String digest;
    private String schemeVersion;
    private String schemeId;


    public DpsDataBean() {
    }

    public DpsDataBean(String id, String status, String sourceId, String involvedWord, String sourceType, String indexType, String sourceName, String sensibility, String emotion, String isContent, String isTitle, String title, String releasDate, String content, String sourceAccounts, String author, String getDate, String digest, String schemeVersion, String schemeId) {

        this.id = id;
        this.status = status;
        this.sourceId = sourceId;
        this.involvedWord = involvedWord;
        this.sourceType = sourceType;
        this.indexType = indexType;
        this.sourceName = sourceName;
        this.sensibility = sensibility;
        this.emotion = emotion;
        this.isContent = isContent;
        this.isTitle = isTitle;
        this.title = title;
        this.releasDate = releasDate;
        this.content = content;
        this.sourceAccounts = sourceAccounts;
        this.author = author;
        this.getDate = getDate;
        this.digest = digest;
        this.schemeVersion = schemeVersion;
        this.schemeId = schemeId;
    }

    @Override
    public String toString() {
        return "DpsDataBean{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                ", sourceId='" + sourceId + '\'' +
                ", involvedWord='" + involvedWord + '\'' +
                ", sourceType='" + sourceType + '\'' +
                ", indexType='" + indexType + '\'' +
                ", sourceName='" + sourceName + '\'' +
                ", sensibility='" + sensibility + '\'' +
                ", emotion='" + emotion + '\'' +
                ", isContent='" + isContent + '\'' +
                ", isTitle='" + isTitle + '\'' +
                ", title='" + title + '\'' +
                ", releasDate='" + releasDate + '\'' +
                ", content='" + content + '\'' +
                ", sourceAccounts='" + sourceAccounts + '\'' +
                ", author='" + author + '\'' +
                ", getDate='" + getDate + '\'' +
                ", digest='" + digest + '\'' +
                ", schemeVersion='" + schemeVersion + '\'' +
                ", schemeId='" + schemeId + '\'' +
                '}';
    }

    public String getSourceAccounts() {
        return sourceAccounts;
    }

    public void setSourceAccounts(String sourceAccounts) {
        this.sourceAccounts = sourceAccounts;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGetDate() {
        return getDate;
    }

    public void setGetDate(String getDate) {
        this.getDate = getDate;
    }

    public String getSchemeVersion() {
        return schemeVersion;
    }

    public void setSchemeVersion(String schemeVersion) {
        this.schemeVersion = schemeVersion;
    }

    public String getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
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

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest;
    }
}
