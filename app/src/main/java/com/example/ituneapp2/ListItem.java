package com.example.ituneapp2;

public class ListItem {

    private String url;
    private long id;
    private String idUser;

    public ListItem(String url, String userId) {
        this.url = url;
        this.idUser = userId;
    }

    public ListItem(String url, long id, String idUser) {
        this.url = url;
        this.id = id;
        this.idUser = idUser;
    }

    public ListItem(String url, String description, long id, String dueDate) {
        this.url = url;
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ListItem{" +
                "url='" + url + '\'' +
                ", id=" + id +
                ", idUser='" + idUser + '\'' +
                '}';
    }
}