package com.plazacomidas.user.domain.model;

import com.plazacomidas.user.domain.util.RoleConstants;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleEntity {
    @Id
    private Long id;


    @Column(name = RoleConstants.COLUMN_NAME)
    private String name;

    @Column(name = RoleConstants.COLUMN_DESCRIPTION)
    private String description;
}
