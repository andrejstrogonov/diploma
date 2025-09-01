package ru.skypro.homework.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import ru.skypro.homework.dto.Role;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user_model")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false, name = "first_name", length = 16)
    @Size(min = 2, max = 16)
    private String firstName;
    @Column(nullable = false, name = "last_name", length = 16)
    @Size(min = 2, max = 16)
    private String lastName;
    @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}")
    @Schema(example = "+7(297)98339-39")
    private String phone;
    private String image;
    @OneToMany(mappedBy = "userModel")
    private List<AdModel> adModels;
    @Column(nullable = false, name = "username", length = 32)
    @Size(min = 4, max = 32)
    private String userName;
    @Column(length = 16)
    @Size(min = 8, max = 16)
    private String password;
    @Size(min = 8, max = 16)
    @Column(nullable = false, name = "new_password", length = 16)
    private String NewPassword;
    @Enumerated(EnumType.STRING)
    private Role role;

    public UserModel() {
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof UserModel)) return false;
        UserModel userModel = (UserModel) object;
        return Objects.equals(id, userModel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public @Size(min = 2, max = 16) String getFirstName() {
        return firstName;
    }

    public void setFirstName(@Size(min = 2, max = 16) String firstName) {
        this.firstName = firstName;
    }

    public @Size(min = 2, max = 16) String getLastName() {
        return lastName;
    }

    public void setLastName(@Size(min = 2, max = 16) String lastName) {
        this.lastName = lastName;
    }

    public @Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}") String getPhone() {
        return phone;
    }

    public void setPhone(@Pattern(regexp = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}") String phone) {
        this.phone = phone;
    }

    public UserModel(Integer id, String firstName, String lastName, String phone, String image, List<AdModel> adModels, String userName, String password, String newPassword, Role role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.image = image;
        this.adModels = adModels;
        this.userName = userName;
        this.password = password;
        NewPassword = newPassword;
        this.role = role;
    }

    public Integer getId() {
        return this.id;
    }

    public String getImage() {
        return this.image;
    }

    public List<AdModel> getAdModels() {
        return this.adModels;
    }

    public @Size(min = 4, max = 32) String getUserName() {
        return this.userName;
    }

    public @Size(min = 8, max = 16) String getPassword() {
        return this.password;
    }

    public @Size(min = 8, max = 16) String getNewPassword() {
        return this.NewPassword;
    }

    public Role getRole() {
        return this.role;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setAdModels(List<AdModel> adModels) {
        this.adModels = adModels;
    }

    public void setUserName(@Size(min = 4, max = 32) String userName) {
        this.userName = userName;
    }

    public void setPassword(@Size(min = 8, max = 16) String password) {
        this.password = password;
    }

    public void setNewPassword(@Size(min = 8, max = 16) String NewPassword) {
        this.NewPassword = NewPassword;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

