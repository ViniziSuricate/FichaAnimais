package com.CadFichaAnimal.pkg;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import javax.persistence.Column;
 

@Entity
@Table(name = "tb_Ficha")
public class Ficha {

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_Ficha")
    private int id_Ficha; 
    
    @Temporal(TemporalType.DATE)
    @Column(name = "dtCat_Ficha")
    private Date dtCat_Ficha;
 
    @Column(name = "cdStatus_Ficha")
    private String cdStatus_Ficha;
 
    @Column(name = "txtObs_Ficha")
    private String txtObs_Ficha;

    public Ficha() {}
    
	public Ficha(String txtObs) {
		this.txtObs_Ficha = txtObs;
	}
    
	public Ficha(int id, Date data, String status, String txtObs) {
		this.id_Ficha = id;
		this.dtCat_Ficha = data;
		this.cdStatus_Ficha = status;
		this.txtObs_Ficha = txtObs;	
	}

	public int getId() {
        return id_Ficha;
    }
 
    public void setId(int id) {
        this.id_Ficha = id;
    }
	
	public Date getData() {
        return dtCat_Ficha;
    }
 
    public void setData(Date data) {
        this.dtCat_Ficha = data;
    }
    
    public String getStatus() {
        return cdStatus_Ficha;
    }
 
    public void setStatus(String status) {
        this.cdStatus_Ficha = status;
    }
    
    public String getObs() {
        return txtObs_Ficha;
    }
 
    public void setObs(String txtObs) {
        this.txtObs_Ficha = txtObs;
    }
}