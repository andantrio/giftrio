package com.fluffytrio.giftrio.settings;


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
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String colorScheme;

    @Column(nullable = false)
    private String primaryColor;

    @Column(nullable = false)
    private String accentColor;

    @Column(nullable = false)
    private String bgColor;

    @Column(nullable = false)
    private String subColor;
}
