package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;


public class UpdateUser {
    @Schema(description = "имя пользователя", minLength = 3, maxLength = 10)
    private String firstName;
    @Schema(description = "фамилия пользователя", minLength = 3, maxLength = 10)
    private String lastName;
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    @Schema(description = "телефон пользователя", example = "+7 (695) 498-51-37")
    private String phone;

    public UpdateUser() {
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}") String getPhone() {
        return this.phone;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhone(@Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}") String phone) {
        this.phone = phone;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof UpdateUser)) return false;
        final UpdateUser other = (UpdateUser) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$firstName = this.getFirstName();
        final Object other$firstName = other.getFirstName();
        if (this$firstName == null ? other$firstName != null : !this$firstName.equals(other$firstName)) return false;
        final Object this$lastName = this.getLastName();
        final Object other$lastName = other.getLastName();
        if (this$lastName == null ? other$lastName != null : !this$lastName.equals(other$lastName)) return false;
        final Object this$phone = this.getPhone();
        final Object other$phone = other.getPhone();
        if (this$phone == null ? other$phone != null : !this$phone.equals(other$phone)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof UpdateUser;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $firstName = this.getFirstName();
        result = result * PRIME + ($firstName == null ? 43 : $firstName.hashCode());
        final Object $lastName = this.getLastName();
        result = result * PRIME + ($lastName == null ? 43 : $lastName.hashCode());
        final Object $phone = this.getPhone();
        result = result * PRIME + ($phone == null ? 43 : $phone.hashCode());
        return result;
    }

    public String toString() {
        return "UpdateUser(firstName=" + this.getFirstName() + ", lastName=" + this.getLastName() + ", phone=" + this.getPhone() + ")";
    }
}
