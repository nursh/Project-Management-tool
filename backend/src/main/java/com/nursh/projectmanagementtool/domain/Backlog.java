package com.nursh.projectmanagementtool.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Backlog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int PTSequence = 0;
    private String projectIdentifier;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "project_id", nullable = false)
    @JsonIgnore
    private Project project;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "backlog")
    private List<Task> tasks = new ArrayList<>();

}
