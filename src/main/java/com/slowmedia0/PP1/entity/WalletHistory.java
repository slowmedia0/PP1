package com.slowmedia0.PP1.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcType;
import org.hibernate.dialect.type.PostgreSQLEnumJdbcType;
import org.springframework.data.rest.core.annotation.RestResource;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "wallet_histories")

public class WalletHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "wallet_histories_id_seq_gen")
    @SequenceGenerator(name = "wallet_histories_id_seq_gen",
    sequenceName = "wallet_histories_id_seq", allocationSize = 50)
    BigInteger id;
    @Column(name = "wallet_transaction_type")
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    WalletTransactionType walletTransactionType;
    @Column(name = "wallet_transaction_status")
    @Enumerated(EnumType.STRING)
    @JdbcType(PostgreSQLEnumJdbcType.class)
    WalletTransactionStatus walletTransactionStatus;
    @Column(name = "sum_price_of_operation")
    BigInteger sumPriceOfOperation;
    @Column(name = "time_of_operation")
    LocalDateTime timeOfOperation;

    //Связи
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

}
