package com.CadFichaAnimal.pkg;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
 
@Entity
@Table(name = "tb_Animal")
public class Animal {
 
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_Seq")
    private int id_Seq; 
    
    @Column(name = "id_Animal")
    private String id_Animal;
 
    @Column(name = "txt_NomeAnimal")
    private String txt_NomeAnimal;
 
    public Animal() {}
    
	public Animal(String id, String nome) {
		this.id_Animal = id;
		this.txt_NomeAnimal = nome;
	}
    
	public Animal(int idSeq, String id, String nome) {
		this.id_Seq = idSeq;
		this.id_Animal = id;
		this.txt_NomeAnimal = nome;
	}
	
	public int getSeq() {
        return id_Seq;
    }
 
    public void setSeq(int idSeq) {
        this.id_Seq = idSeq;
    }

	public String getId() {
        return id_Animal;
    }
 
    public void setId(String id) {
        this.id_Animal = id;
    }
 
    public String getNome() {
        return txt_NomeAnimal;
    }
 
    public void setNome(String nome) {
        this.txt_NomeAnimal = nome;
    }
}