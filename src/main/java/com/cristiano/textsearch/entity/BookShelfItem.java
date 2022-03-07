package com.cristiano.textsearch.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class BookShelfItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public BookShelfItem(){

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}