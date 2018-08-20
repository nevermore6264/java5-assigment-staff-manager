package com.fpoly.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "`departs`")
public class Depart implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Field depart isn't null")
    @NotBlank(message = "Field depart isn't blank")
    @Length(min = 2,message = "Field name must >=2")
    private String name;

    @OneToMany
    private transient List<Staff> staff;

    public Depart() {
    }

    public Depart(String name, List<Staff> staff) {
        this.name = name;
        this.staff = staff;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Staff> getStaff() {
        return staff;
    }

    public void setStaff(List<Staff> staff) {
        this.staff = staff;
    }

}
