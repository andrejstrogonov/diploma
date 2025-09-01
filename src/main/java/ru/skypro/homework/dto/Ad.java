package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class Ad {
    @Schema(description = "id автора объявления")
    private int author;
    @Schema(description = "ссылка на картинку объявления")
    private String image;
    @Schema(description = "id объявления")
    private int pk;
    @Schema(description = "заголовок объявления")
    private String title;
    @Schema(description = "цена объявления")
    private int price;

    public Ad() {
    }

    public int getAuthor() {
        return this.author;
    }

    public String getImage() {
        return this.image;
    }

    public int getPk() {
        return this.pk;
    }

    public String getTitle() {
        return this.title;
    }

    public int getPrice() {
        return this.price;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Ad)) return false;
        final Ad other = (Ad) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getAuthor() != other.getAuthor()) return false;
        final Object this$image = this.getImage();
        final Object other$image = other.getImage();
        if (this$image == null ? other$image != null : !this$image.equals(other$image)) return false;
        if (this.getPk() != other.getPk()) return false;
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
        if (this.getPrice() != other.getPrice()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Ad;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getAuthor();
        final Object $image = this.getImage();
        result = result * PRIME + ($image == null ? 43 : $image.hashCode());
        result = result * PRIME + this.getPk();
        final Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        result = result * PRIME + this.getPrice();
        return result;
    }

    public String toString() {
        return "Ad(author=" + this.getAuthor() + ", image=" + this.getImage() + ", pk=" + this.getPk() + ", title=" + this.getTitle() + ", price=" + this.getPrice() + ")";
    }
}