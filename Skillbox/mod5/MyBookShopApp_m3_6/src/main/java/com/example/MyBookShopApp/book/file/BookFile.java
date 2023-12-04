package com.example.MyBookShopApp.book.file;

import javax.persistence.*;

@Entity
@Table(name="bookFiles")
public class BookFile {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String eshkere) {
        this.hash = eshkere;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getType_id() {
        return type_id;
    }

    public void setType_id(int type_id) {
        this.type_id = type_id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String hash;
    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String path;
    @Column(columnDefinition = "INT NOT NULL")
    private int type_id;
}
