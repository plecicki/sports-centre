package com.kodilla.sportscentre.domain;

import com.kodilla.sportscentre.domain.enums.CardStatus;
import com.kodilla.sportscentre.domain.prototype.Prototype;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardToClone extends Prototype<CardToClone> {

    private Long cardId;
    private User user;
    private String accessPass;
    private CardStatus cardStatus;

    public static class CardToCloneBuilder {
        private Long cardId;
        private User user;
        private String accessPass;
        private CardStatus cardStatus;

        public CardToCloneBuilder cardId(Long cardId) {
            this.cardId = cardId;
            return this;
        }

        public CardToCloneBuilder user(User user) {
            this.user = user;
            return this;
        }

        public CardToCloneBuilder accessPass(String accessPass) {
            this.accessPass = accessPass;
            return this;
        }

        public CardToCloneBuilder cardStatus(CardStatus cardStatus) {
            this.cardStatus = cardStatus;
            return this;
        }

        public CardToClone build() {
            return new CardToClone(
                    cardId,
                    user,
                    accessPass,
                    cardStatus
            );
        }
    }

    public CardToClone copy() throws CloneNotSupportedException {
        return super.clone();
    }
}
