package ru.skypro.homework.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;
@Getter
@Setter
@Entity
@Table(name = "ad_model")
public class AdModel {
     @Id
    @GeneratedValue
    private Integer pk;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author")
    private UserModel userModel;
    @Column(nullable = false, name = "image")
    private String image;
    private String title;
    @Size(min = 0, max = 10000000)
    private int price;
    private String description;
    @OneToMany(mappedBy = "adModel")
    private List<CommentModel>commentModels;
    public AdModel(){}

    public Integer getPk() {
        return pk;
    }

    public void setPk(Integer pk) {
        this.pk = pk;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Size(min = 0, max = 10000000)
    public int getPrice() {
        return price;
    }

    public void setPrice(@Size(min = 0, max = 10000000) int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CommentModel> getCommentModels() {
        return commentModels;
    }

    public void setCommentModels(List<CommentModel> commentModels) {
        this.commentModels = commentModels;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof AdModel)) return false;
        AdModel adModel = (AdModel) object;
        return Objects.equals(pk, adModel.pk);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(pk);
    }

    @JsonIgnore
    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    @Override
    public String toString() {
        return "AdModel{" +
                "pk=" + pk +
                ", userModel=" + userModel +
                ", imageId='" + image + '\'' +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}

