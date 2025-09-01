package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ExtendedAd {
    @Schema(description = "id объявления")
    private int pk;
    @Schema(description = "имя автора объявления")
    private String authorFirstName;
    @Schema(description = "фамилия автора объявления")
    private String authorLastName;
    @Schema(description = "описание объявления")
    private String description;
    @Schema(description = "логин автора объявления")
    private String email;
    @Schema(description = "ссылка на картинку объявления")
    private String image;
    @Schema(description = "телефон автора объявления")
    private String phone;
    @Schema(description = "цена объявления")
    private int price;
    @Schema(description = "заголовок объявления")
    private String title;

    public ExtendedAd() {
    }

    public int getPk() {
        return this.pk;
    }

    public String getAuthorFirstName() {
        return this.authorFirstName;
    }

    public String getAuthorLastName() {
        return this.authorLastName;
    }

    public String getDescription() {
        return this.description;
    }

    public String getEmail() {
        return this.email;
    }

    public String getImage() {
        return this.image;
    }

    public String getPhone() {
        return this.phone;
    }

    public int getPrice() {
        return this.price;
    }

    public String getTitle() {
        return this.title;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof ExtendedAd)) return false;
        final ExtendedAd other = (ExtendedAd) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getPk() != other.getPk()) return false;
        final Object this$authorFirstName = this.getAuthorFirstName();
        final Object other$authorFirstName = other.getAuthorFirstName();
        if (this$authorFirstName == null ? other$authorFirstName != null : !this$authorFirstName.equals(other$authorFirstName))
            return false;
        final Object this$authorLastName = this.getAuthorLastName();
        final Object other$authorLastName = other.getAuthorLastName();
        if (this$authorLastName == null ? other$authorLastName != null : !this$authorLastName.equals(other$authorLastName))
            return false;
        final Object this$description = this.getDescription();
        final Object other$description = other.getDescription();
        if (this$description == null ? other$description != null : !this$description.equals(other$description))
            return false;
        final Object this$email = this.getEmail();
        final Object other$email = other.getEmail();
        if (this$email == null ? other$email != null : !this$email.equals(other$email)) return false;
        final Object this$image = this.getImage();
        final Object other$image = other.getImage();
        if (this$image == null ? other$image != null : !this$image.equals(other$image)) return false;
        final Object this$phone = this.getPhone();
        final Object other$phone = other.getPhone();
        if (this$phone == null ? other$phone != null : !this$phone.equals(other$phone)) return false;
        if (this.getPrice() != other.getPrice()) return false;
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof ExtendedAd;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getPk();
        final Object $authorFirstName = this.getAuthorFirstName();
        result = result * PRIME + ($authorFirstName == null ? 43 : $authorFirstName.hashCode());
        final Object $authorLastName = this.getAuthorLastName();
        result = result * PRIME + ($authorLastName == null ? 43 : $authorLastName.hashCode());
        final Object $description = this.getDescription();
        result = result * PRIME + ($description == null ? 43 : $description.hashCode());
        final Object $email = this.getEmail();
        result = result * PRIME + ($email == null ? 43 : $email.hashCode());
        final Object $image = this.getImage();
        result = result * PRIME + ($image == null ? 43 : $image.hashCode());
        final Object $phone = this.getPhone();
        result = result * PRIME + ($phone == null ? 43 : $phone.hashCode());
        result = result * PRIME + this.getPrice();
        final Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        return result;
    }

    public String toString() {
        return "ExtendedAd(pk=" + this.getPk() + ", authorFirstName=" + this.getAuthorFirstName() + ", authorLastName=" + this.getAuthorLastName() + ", description=" + this.getDescription() + ", email=" + this.getEmail() + ", image=" + this.getImage() + ", phone=" + this.getPhone() + ", price=" + this.getPrice() + ", title=" + this.getTitle() + ")";
    }
}
