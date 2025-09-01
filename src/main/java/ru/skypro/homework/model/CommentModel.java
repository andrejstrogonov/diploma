package ru.skypro.homework.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Objects;

@Table(name = "comment_model")
@Entity
public class CommentModel {
    @Id
    @GeneratedValue
    private Integer pk;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ad_pk")
    private AdModel adModel;
    @Column(nullable = false, name = "created_at")
    private long createdAt;
    private String text;

    public CommentModel() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof CommentModel)) return false;
        CommentModel that = (CommentModel) object;
        return Objects.equals(pk, that.pk);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(pk);
    }

    @JsonIgnore
    public void setAdModel(AdModel adModel) {
        this.adModel = adModel;
    }

    @Override
    public String toString() {
        return "CommentModel{" +
                "pk=" + pk +
                ", adModel=" + adModel +
                ", createdAt=" + createdAt +
                ", text='" + text + '\'' +
                '}';
    }

    public Integer getPk() {
        return this.pk;
    }

    public AdModel getAdModel() {
        return this.adModel;
    }

    public long getCreatedAt() {
        return this.createdAt;
    }

    public String getText() {
        return this.text;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public void setText(String text) {
        this.text = text;
    }
}




