package com.kopw.app.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(name="user_login")
public class UserLogin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "username", length = 36, nullable = false)
    private String userName;

    @Column(name = "Encryted_Password", length = 128, nullable = false)
    private String encrytedPassword;

    @Column(name = "Enabled", length = 1, nullable = false)
    private boolean enabled;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles", // Tên bảng liên kết
            joinColumns = @JoinColumn(name = "user_id"), // Tên cột liên kết với User
            inverseJoinColumns = @JoinColumn(name = "role_id") // Tên cột liên kết với Role
    )
    private List<Role> roles;

    @Transient
    @Override
    public String toString() {
        return "UserLogin{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", Encryted_Password='" + encrytedPassword + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
