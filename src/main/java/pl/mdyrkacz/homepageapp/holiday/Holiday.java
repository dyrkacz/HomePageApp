package pl.mdyrkacz.homepageapp.holiday;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;
import pl.mdyrkacz.homepageapp.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Holiday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 40, message = "Holiday name must be up to 40 characters long")
    private String holidayName;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate holidayDate;
    private String priority;
    private boolean annual;
    private boolean emailReminder;
    @ManyToOne
    private User user;
    @Transient
    public final String[] priorities = {"Normal", "Important", "Urgent"};
}
