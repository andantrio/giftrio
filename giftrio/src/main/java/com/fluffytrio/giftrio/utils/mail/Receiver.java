package com.fluffytrio.giftrio.utils.mail;

import com.fluffytrio.giftrio.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Receiver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="mail_id", nullable = false, updatable = false)
    private Mail mail;

    @ManyToOne
    @JoinColumn(nullable = false, updatable = false)
    private User user;
}
