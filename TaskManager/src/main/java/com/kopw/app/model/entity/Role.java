package com.kopw.app.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(name="roles",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "Role_Name") })
public class Role {
    @Id
    @GeneratedValue
    @Column(name = "role_id", nullable = false)
    private Long roleId;

    @Column(name = "Role_Name", length = 30, nullable = false)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    @JsonBackReference
    private List<UserLogin> users;
}
