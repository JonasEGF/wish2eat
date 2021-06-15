package com.aramat.wish2eat.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tb_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<Favorite> favoriteList;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<FavStore> favStoreList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Favorite> getFavoriteList() {
        return favoriteList;
    }

    public void setFavoriteList(Set<Favorite> favoriteList) {
        this.favoriteList = favoriteList;
    }

    public Set<FavStore> getFavStoreList() {
        return favStoreList;
    }

    public void setFavStoreList(Set<FavStore> favStoreList) {
        this.favStoreList = favStoreList;
    }
}
