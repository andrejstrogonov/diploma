package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class Comment {
    @Schema(description = "id автора комментария")
    private int author;
    @Schema(description = "ссылка на аватар автора комментария")
    private String authorImage;
    @Schema(description = "имя создателя комментария")
    private String authorFirstName;
    @Schema(description = "дата и время создания комментария в миллисекундах с 00:00:00 01.01.1970")
    private long createdAt;
    @Schema(description = "id комментария")
    private int pk;
    @Schema(description = "текст комментария")
    private String text;

    public Comment() {
    }

    public int getAuthor() {
        return this.author;
    }

    public String getAuthorImage() {
        return this.authorImage;
    }

    public String getAuthorFirstName() {
        return this.authorFirstName;
    }

    public long getCreatedAt() {
        return this.createdAt;
    }

    public int getPk() {
        return this.pk;
    }

    public String getText() {
        return this.text;
    }

    public void setAuthor(int author) {
        this.author = author;
    }

    public void setAuthorImage(String authorImage) {
        this.authorImage = authorImage;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public void setPk(int pk) {
        this.pk = pk;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Comment)) return false;
        final Comment other = (Comment) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getAuthor() != other.getAuthor()) return false;
        final Object this$authorImage = this.getAuthorImage();
        final Object other$authorImage = other.getAuthorImage();
        if (this$authorImage == null ? other$authorImage != null : !this$authorImage.equals(other$authorImage))
            return false;
        final Object this$authorFirstName = this.getAuthorFirstName();
        final Object other$authorFirstName = other.getAuthorFirstName();
        if (this$authorFirstName == null ? other$authorFirstName != null : !this$authorFirstName.equals(other$authorFirstName))
            return false;
        if (this.getCreatedAt() != other.getCreatedAt()) return false;
        if (this.getPk() != other.getPk()) return false;
        final Object this$text = this.getText();
        final Object other$text = other.getText();
        if (this$text == null ? other$text != null : !this$text.equals(other$text)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Comment;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getAuthor();
        final Object $authorImage = this.getAuthorImage();
        result = result * PRIME + ($authorImage == null ? 43 : $authorImage.hashCode());
        final Object $authorFirstName = this.getAuthorFirstName();
        result = result * PRIME + ($authorFirstName == null ? 43 : $authorFirstName.hashCode());
        final long $createdAt = this.getCreatedAt();
        result = result * PRIME + (int) ($createdAt >>> 32 ^ $createdAt);
        result = result * PRIME + this.getPk();
        final Object $text = this.getText();
        result = result * PRIME + ($text == null ? 43 : $text.hashCode());
        return result;
    }

    public String toString() {
        return "Comment(author=" + this.getAuthor() + ", authorImage=" + this.getAuthorImage() + ", authorFirstName=" + this.getAuthorFirstName() + ", createdAt=" + this.getCreatedAt() + ", pk=" + this.getPk() + ", text=" + this.getText() + ")";
    }
}
