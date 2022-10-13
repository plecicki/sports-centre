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

    public static class UserToCloneBuilder {
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

        public UserToCloneBuilder userId(Long userId) {
            this.userId = userId;
            return this;
        }

        public UserToCloneBuilder name(String name) {
            this.name = name;
            return this;
        }

        public UserToCloneBuilder surname(String surname) {
            this.surname = surname;
            return this;
        }

        public UserToCloneBuilder birthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public UserToCloneBuilder email(String email) {
            this.email = email;
            return this;
        }

        public UserToCloneBuilder phone(String phone) {
            this.phone = phone;
            return this;
        }

        public UserToCloneBuilder goal(Goals goal) {
            this.goal = goal;
            return this;
        }

        public UserToCloneBuilder student(boolean student) {
            this.student = student;
            return this;
        }

        public UserToCloneBuilder gym(boolean gym) {
            this.gym = gym;
            return this;
        }

        public UserToCloneBuilder swimmingPool(boolean swimmingPool) {
            this.swimmingPool = swimmingPool;
            return this;
        }

        public UserToCloneBuilder card(Card card) {
            this.card = card;
            return this;
        }

        public UserToClone build() {
            return new UserToClone(
                    userId,
                    name,
                    surname,
                    birthDate,
                    email,
                    phone,
                    goal,
                    student,
                    gym,
                    swimmingPool,
                    card
            );
        }
    }

    public UserToClone copy() throws CloneNotSupportedException {
        return super.clone();
    }
}
