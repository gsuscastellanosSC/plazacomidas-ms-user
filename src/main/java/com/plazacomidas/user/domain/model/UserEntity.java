package com.plazacomidas.user.domain.model;

import com.plazacomidas.user.domain.util.UserConstants;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = UserConstants.COLUMN_FIRST_NAME)
    private String firstName;

    @Column(name = UserConstants.COLUMN_LAST_NAME)
    private String lastName;

    @Column(name = UserConstants.COLUMN_DOCUMENT_ID)
    private String documentId;

    @Column(name = UserConstants.COLUMN_PHONE)
    private String phone;

    @Column(name = UserConstants.COLUMN_BIRTH_DATE)
    private LocalDate birthDate;

    @Column(name = UserConstants.COLUMN_EMAIL)
    private String email;

    @Column(name = UserConstants.COLUMN_PASSWORD)
    private String password;

    @Column(name = UserConstants.COLUMN_ROLE_ID)
    private Long roleId;

    @Column(name = UserConstants.COLUMN_RESTAURANT_ID)
    private Long restaurantId;
}
