package jp.wintercat.veterinaryclinic.entity;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.List;

/**
 * カルテ　
 */
@Entity
@Getter
@Setter
@DynamicUpdate
@Table(name = "clinical_records")
public class ClinicalRecords {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "cid")
        private Long cid;
        @Column(name = "lodging")
        private Integer lodging;//宿泊
        @Column(name = "meal")
        private Integer meal;//食事
        @Column(name = "first_visit")
        private Integer firstVisit;// 1 初診　0 初診ではない
        // --- --- --- --- ---　--- ---
        @ManyToOne(cascade = {CascadeType.ALL},fetch=FetchType.LAZY)
        @JoinColumn(name = "drug_id")
        private DrugList drugList;
        // -- --- - - ---
        @OneToOne(mappedBy = "clinicalRecords",targetEntity = Invoice.class,cascade = {CascadeType.ALL})
        @PrimaryKeyJoinColumn
        private Invoice invoice;

        public ClinicalRecords() {}

        public ClinicalRecords(Integer lodging, Integer meal, Integer firstVisit, DrugList drugList) {
                this.lodging = lodging;
                this.meal = meal;
                this.firstVisit = firstVisit;
                this.drugList = drugList;
        }

}
