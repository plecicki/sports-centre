package com.kodilla.sportscentre.domain;

import com.kodilla.sportscentre.domain.enums.Goals;
import com.kodilla.sportscentre.domain.prototype.Prototype;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
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
    private Boolean student;
    private Boolean gym;
    private Boolean swimmingPool;
    private Card card;
    private Boolean autoExtension;
    private LocalDate subValidity;

    public static class UserToCloneBuilder {
        private Long userId;
        private String name;
        private String surname;
        private LocalDate birthDate;
        private String email;
        private String phone;
        private Goals goal;
        private Boolean student;
        private Boolean gym;
        private Boolean swimmingPool;
        private Card card;
        private Boolean autoExtension;
        private LocalDate subValidity;

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

        public UserToCloneBuilder student(Boolean student) {
            this.student = student;
            return this;
        }

        public UserToCloneBuilder gym(Boolean gym) {
            this.gym = gym;
            return this;
        }

        public UserToCloneBuilder swimmingPool(Boolean swimmingPool) {
            this.swimmingPool = swimmingPool;
            return this;
        }

        public UserToCloneBuilder card(Card card) {
            this.card = card;
            return this;
        }

        public UserToCloneBuilder autoExtension(Boolean autoExtension) {
            this.autoExtension = autoExtension;
            return this;
        }

        public UserToCloneBuilder subValidity(LocalDate subValidity) {
            this.subValidity = subValidity;
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
                    card,
                    autoExtension,
                    subValidity
            );
        }
    }

    public UserToClone copy() throws CloneNotSupportedException {
        return super.clone();
    }
}
