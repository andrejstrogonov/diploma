package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;


public class CreateOrUpdateAd {
    @Schema(description = "заголовок объявления", minLength = 4, maxLength = 32)
    private String title;
    @Schema(description = "описание объявления", minLength = 8, maxLength = 64)
    private String description;
    @Schema(description = "цена объявления", minimum = "0", maximum = "10000000")
    private int price;

    public CreateOrUpdateAd() {
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public int getPrice() {
        return this.price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CreateOrUpdateAd)) return false;
        final CreateOrUpdateAd other = (CreateOrUpdateAd) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        if (this.getPrice() != other.getPrice()) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CreateOrUpdateAd;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        result = result * PRIME + this.getPrice();
        return result;
    }

    public String toString() {
        return "CreateOrUpdateAd(title=" + this.getTitle() + ", description=" + this.getDescription() + ", price=" + this.getPrice() + ")";
    }
}
