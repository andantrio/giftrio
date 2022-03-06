package com.fluffytrio.giftrio.settings;

import com.fluffytrio.giftrio.users.Users;

import javax.persistence.*;

@Entity
public class Settings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

}
