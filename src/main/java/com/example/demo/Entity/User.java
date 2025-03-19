package com.example.demo.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate DOB;

    @Email
    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    @Column(unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    private CardType cardType;

    @Column(nullable = false)
    private String bankName;

    @Column(unique = true, nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private String ifscCode;

    private boolean isVerified =false;

    private Role role = Role.USER;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card_id", referencedColumnName = "id", unique = true)
    private Card card;

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        this.isVerified = verified;
    }
}
