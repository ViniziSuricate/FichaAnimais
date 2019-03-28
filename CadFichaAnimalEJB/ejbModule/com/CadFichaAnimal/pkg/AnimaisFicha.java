package com.CadFichaAnimal.pkg;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
 
@Entity
@Table(name = "tb_AnimaisFicha")

public class AnimaisFicha {
 
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_AnimaisFicha")
    private int id_AnimaisFicha; 
    
    @Column(name = "idFicha_AnimaisFicha")
    private int idFicha_AnimaisFicha;
 
    @Column(name = "idAnimal_AnimaisFicha")
    private int idAnimal_AnimaisFicha;
 
    public AnimaisFicha() {}
      
	public AnimaisFicha(int idAnimal) {
		this.idAnimal_AnimaisFicha = idAnimal;
	}
	
	public AnimaisFicha(int idFicha, int idAnimal) {
		this.idFicha_AnimaisFicha = idFicha;
		this.idAnimal_AnimaisFicha = idAnimal;
	}
    
	public AnimaisFicha(int id, int idFicha, int idAnimal) {
		this.id_AnimaisFicha = id;
		this.idFicha_AnimaisFicha = idFicha;
		this.idAnimal_AnimaisFicha = idAnimal;
	}
	
	public int getId() {
        return id_AnimaisFicha;
    }
 
    public void setId(int id) {
        this.id_AnimaisFicha = id;
    }

	public int getIdAnimal() {
        return idAnimal_AnimaisFicha;
    }
 
    public void setIdAnimal(int id) {
        this.idAnimal_AnimaisFicha = id;
    }
 
    public int getIdFicha() {
        return idFicha_AnimaisFicha;
    }
 
    public void setIdFicha(int id) {
        this.idFicha_AnimaisFicha = id;
    }
    
}