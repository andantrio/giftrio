package com.fluffytrio.giftrio.settings;


import javax.persistence.*;

@Entity
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}
