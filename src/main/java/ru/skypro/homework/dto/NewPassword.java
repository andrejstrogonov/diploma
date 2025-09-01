package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;


public class NewPassword {
    @Schema(description = "текущий пароль", minLength = 8, maxLength = 16)
    private String currentPassword;
    @Schema(description = "новый пароль", minLength = 8, maxLength = 16)
    private String newPassword;

    public NewPassword() {
    }

    public String getCurrentPassword() {
        return this.currentPassword;
    }

    public String getNewPassword() {
        return this.newPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof NewPassword)) return false;
        final NewPassword other = (NewPassword) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$currentPassword = this.getCurrentPassword();
        final Object other$currentPassword = other.getCurrentPassword();
        if (this$currentPassword == null ? other$currentPassword != null : !this$currentPassword.equals(other$currentPassword))
            return false;
        final Object this$newPassword = this.getNewPassword();
        final Object other$newPassword = other.getNewPassword();
        if (this$newPassword == null ? other$newPassword != null : !this$newPassword.equals(other$newPassword))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof NewPassword;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $currentPassword = this.getCurrentPassword();
        result = result * PRIME + ($currentPassword == null ? 43 : $currentPassword.hashCode());
        final Object $newPassword = this.getNewPassword();
        result = result * PRIME + ($newPassword == null ? 43 : $newPassword.hashCode());
        return result;
    }

    public String toString() {
        return "NewPassword(currentPassword=" + this.getCurrentPassword() + ", newPassword=" + this.getNewPassword() + ")";
    }
}
