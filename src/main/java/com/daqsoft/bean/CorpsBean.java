package com.daqsoft.bean;

/**
 * @author: daqsoft
 * @date: 2019/11/11
 */

public class CorpsBean {

    private String id;
    private String label;
    private String content;

    public CorpsBean() {
    }

    public CorpsBean(String id, String label, String content) {
        this.id = id;
        this.label = label;
        this.content = content;
    }

    @Override
    public String toString() {
        return "CorpsBean{" +
                "id='" + id + '\'' +
                ", label='" + label + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
