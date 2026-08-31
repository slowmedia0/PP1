package com.slowmedia0.PP1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Entity
@Getter
@Setter
@Table(name = "wallets")
@NoArgsConstructor

public class Wallet extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wallets_id_seq_gen")
    @SequenceGenerator(name = "wallets_id_seq_gen",
    sequenceName = "wallets_id_seq", allocationSize = 50)
    private BigInteger id;

    private BigInteger balance;

    //Связи
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "wallet")
    private WalletHistory walletHistory;
}
