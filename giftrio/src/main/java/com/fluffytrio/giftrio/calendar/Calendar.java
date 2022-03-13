package com.fluffytrio.giftrio.calendar;

import com.fluffytrio.giftrio.advent.Advent;
import com.fluffytrio.giftrio.settings.Settings;
import com.fluffytrio.giftrio.users.Users;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false, updatable=false)
    private Users userId;

    @ManyToOne
    @JoinColumn(name="setting_id", nullable=false)
    private Settings settingId;

    @OneToMany
    @JoinColumn(name="calendar_id")
    private List<Advent> adventList;

    private LocalDate startDate;
    private LocalDate endDate;
    private String title;
    private String detail;
    private String backgroundImg;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Builder
    public Calendar(Users users, Settings settings) {
        this.userId = users;
        this.settingId = settings;
    }
}
