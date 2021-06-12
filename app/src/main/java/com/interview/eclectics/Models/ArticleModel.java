package com.interview.eclectics.Models;

public class ArticleModel {
    String id, name, nodeId, fullName, dateCreated, forks, watchers;

    public ArticleModel() {
    }


    public ArticleModel(String id, String name, String nodeId, String fullName, String dateCreated, String forks, String watchers) {
        this.id = id;
        this.name = name;
        this.nodeId = nodeId;
        this.fullName = fullName;
        this.dateCreated = dateCreated;
        this.forks = forks;
        this.watchers = watchers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getForks() {
        return forks;
    }

    public void setForks(String forks) {
        this.forks = forks;
    }

    public String getWatchers() {
        return watchers;
    }

    public void setWatchers(String watchers) {
        this.watchers = watchers;
    }
}
