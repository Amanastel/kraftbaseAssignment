package com.kraftbase.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transactions {

 @Id
 @GeneratedValue(strategy=GenerationType.AUTO)
 private Integer transactionId;
 
 @JsonFormat(pattern="yyyy-MM-dd-HH-mm-ss")
 private LocalDateTime transactionDate;

 @DecimalMin("0.0")
 private Float amount;

 @DecimalMin("0.0")
 private Float CurrentBalance;
 
 @Enumerated(EnumType.STRING)
 private TransactionType type;


}
