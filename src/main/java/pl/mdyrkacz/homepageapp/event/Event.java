package pl.mdyrkacz.homepageapp.event;


import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import pl.mdyrkacz.homepageapp.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Size(max = 20, message = "Event name must be up to 20 characters long")
    private String eventName;
    @Size(max = 160, message = "Event description must be up to 160 characters long")
    private String eventDescription;
    private boolean eventNoExecutionDateTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate eventExecutionDate;
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime eventExecutionTime;
    private String priority;
    private boolean emailReminder;
    @ManyToOne
    private User user;
    private boolean done;
    @Transient
    public final String[] priorities = {"Normal", "Important", "Urgent"};
}
