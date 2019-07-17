package com.nursh.projectmanagementtool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "projects")
@Builder
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Project name is required")
    @Column(name = "name")
    private String name;

    @NotBlank(message = "Project identifier is required")
    @Column(name = "identifier", updatable = false, unique = true)
    @Size(min = 4, max = 5, message = "Use either 4 or 5 characters")
    private String identifier;

    @Column(name = "description")
    @NotBlank(message = "Project description is required")
    private String description;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "backlog")
    @JsonIgnore
    private Backlog backlog;

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(name = "start_date")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(name = "end_date")
    private Date endDate;

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(name = "created_at", updatable = false)
    private Date createdAt;

    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(name = "updated_at")
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    private String leader;


    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }
}
