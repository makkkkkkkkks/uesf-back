package org.ua.uesf.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String titleUA;
    private String titleEN;
    private String contentUA;
    private String contentEN;
    private String shortDescriptionUA;
    private String shortDescriptionEN;
    private String imgUA;
    private String imgEN;

    @Enumerated(EnumType.STRING)
    private NewsStatus newsStatus;
    private Instant creationDate;
    private Instant updateDate;

    @ManyToMany
    @JoinTable(name = "news_game", joinColumns = @JoinColumn(name = "news_id"), inverseJoinColumns = @JoinColumn(name = "game_id"))

    @JsonBackReference
    private Set<Game> game = new HashSet<>();

    public News() {
    }

    public News(Long id, String titleUA, String titleEN, String contentUA, String contentEN, String shortDescriptionUA, String shortDescriptionEN, String imgUA, String imgEN, NewsStatus newsStatus, Instant creationDate, Instant updateDate, Set<Game> game) {
        this.id = id;
        this.titleUA = titleUA;
        this.titleEN = titleEN;
        this.contentUA = contentUA;
        this.contentEN = contentEN;
        this.shortDescriptionUA = shortDescriptionUA;
        this.shortDescriptionEN = shortDescriptionEN;
        this.imgUA = imgUA;
        this.imgEN = imgEN;
        this.newsStatus = newsStatus;
        this.creationDate = creationDate;
        this.updateDate = updateDate;
        this.game = game;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleUA() {
        return titleUA;
    }

    public void setTitleUA(String titleUA) {
        this.titleUA = titleUA;
    }

    public String getTitleEN() {
        return titleEN;
    }

    public void setTitleEN(String titleEN) {
        this.titleEN = titleEN;
    }

    public String getContentUA() {
        return contentUA;
    }

    public void setContentUA(String contentUA) {
        this.contentUA = contentUA;
    }

    public String getContentEN() {
        return contentEN;
    }

    public void setContentEN(String contentEN) {
        this.contentEN = contentEN;
    }

    public String getShortDescriptionUA() {
        return shortDescriptionUA;
    }

    public void setShortDescriptionUA(String shortDescriptionUA) {
        this.shortDescriptionUA = shortDescriptionUA;
    }

    public String getShortDescriptionEN() {
        return shortDescriptionEN;
    }

    public void setShortDescriptionEN(String shortDescriptionEN) {
        this.shortDescriptionEN = shortDescriptionEN;
    }

    public String getImgUA() {
        return imgUA;
    }

    public void setImgUA(String imgUA) {
        this.imgUA = imgUA;
    }

    public String getImgEN() {
        return imgEN;
    }

    public void setImgEN(String imgEN) {
        this.imgEN = imgEN;
    }

    public NewsStatus getNewsStatus() {
        return newsStatus;
    }

    public void setNewsStatus(NewsStatus newsStatus) {
        this.newsStatus = newsStatus;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Instant getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Instant updateDate) {
        this.updateDate = updateDate;
    }

    public Set<Game> getGame() {
        return game;
    }

    public void setGame(Set<Game> game) {
        this.game = game;
    }
    /*
   public void addGame(Game game) {
        this.game.add(game);
        game.getNews().add(this);
    }

    public void removeGame(Game game) {
        this.getGame().remove(game);
        game.getNews().remove(this);
    }

    public void removeGames() {
        for (Game game : new HashSet<>(game)) {
            removeGame(game);
        }
    }*/

}


