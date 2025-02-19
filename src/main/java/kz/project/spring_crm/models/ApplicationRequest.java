package kz.project.spring_crm.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username", length = 200, nullable = false)
    private String username;

    @Column(name = "course_name", length = 200, nullable = false)
    private String courseName;

    @Column(name = "comment")
    private String comment;

    @Column(name = "phone", length = 20, nullable = false)
    private String phone;

    @Column(name = "is_handled", nullable = false, columnDefinition = "BOOLEAN DEFAULT false")
    private boolean isHandled;
}

