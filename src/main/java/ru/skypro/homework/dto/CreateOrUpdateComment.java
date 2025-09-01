package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreateOrUpdateComment {
    @Schema(description = "имя пользователя", minLength = 8, maxLength = 64)
    private String text;

    public CreateOrUpdateComment() {
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof CreateOrUpdateComment)) return false;
        final CreateOrUpdateComment other = (CreateOrUpdateComment) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$text = this.getText();
        final Object other$text = other.getText();
        if (this$text == null ? other$text != null : !this$text.equals(other$text)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof CreateOrUpdateComment;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $text = this.getText();
        result = result * PRIME + ($text == null ? 43 : $text.hashCode());
        return result;
    }

    public String toString() {
        return "CreateOrUpdateComment(text=" + this.getText() + ")";
    }
}
