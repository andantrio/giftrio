package com.fluffytrio.giftrio.settings;


import lombok.Getter;

import javax.persistence.*;


@Getter
@Entity
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
