package com.kodilla.sportscentre.domain;

import com.kodilla.sportscentre.domain.enums.Goals;
import com.kodilla.sportscentre.domain.prototype.Prototype;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserToClone extends Prototype<UserToClone> {

    private Long userId;
    private String name;
    private String surname;
    private LocalDate birthDate;
    private String email;
    private String phone;
    private Goals goal;
    private boolean student;
    private boolean gym;
    private boolean swimmingPool;
    private Card card;

    public UserToClone copy() throws CloneNotSupportedException {
        return super.clone();
    }
}
