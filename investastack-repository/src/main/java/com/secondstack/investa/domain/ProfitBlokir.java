package com.secondstack.investa.domain;


import com.secondstack.investa.domain.enums.Month;
import com.secondstack.investa.domain.enums.ProfitStatus;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "profit_blokir", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "bulan", "tahun"})})
public class ProfitBlokir implements DomainObject {
/*------------------------------ Fields ------------------------------*/

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private User user;
    private Month bulan;
    private int tahun;
    private int jumlah;
    private ProfitStatus status;
    private String keterangan;

/* --------------------- Getter and Setter ---------------------*/

    public int getTahun() {
        return tahun;
    }

    public void setTahun(int tahun) {
        this.tahun = tahun;
    }

    public Month getBulan() {
        return bulan;
    }

    public void setBulan(Month bulan) {
        this.bulan = bulan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public ProfitStatus getStatus() {
        return status;
    }

    public void setStatus(ProfitStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String[] getDataInArray() {
        String[] result = new String[6];
        result[0] = this.user.getUsername();
        result[1] = bulan.name().toLowerCase() + ", " + tahun;
        result[2] = String.valueOf(this.jumlah);
        result[3] = this.status.name();
        result[4] = this.keterangan;
        result[5] = Long.toString(this.id);
        return result;
    }
}
