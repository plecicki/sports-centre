package com.kodilla.sportscentre.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOldNew {
    private User oldUser;
    private User newUser;
}
