package edu.icet.ecom.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users") // Matches DB table
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment
    private Integer id;

    @NotBlank(message = "Username is required")
    @Column(nullable = false, length = 50)
    private String name;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    @Column(unique = true, nullable = false, length = 100)
    private String email;

    @NotBlank(message = "Phone number is required")
    @Column(nullable = false, length = 15)
    private String pnumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    public enum Role {
        ADMIN, PHARMACIST, ASSISTANT
    }
}
