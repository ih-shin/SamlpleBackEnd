package com.orz.base.domain.error;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Table(name="ErrorCode")
@Entity
public class ErrorCode implements Serializable{
	
	private static final long serialVersionUID = 6349573712366547848L;
	
	public ErrorCode() {}
	
	@Builder
	public ErrorCode(String code, String message, int status) {
		this.message=message;
		this.status=status;
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq")
	private long seq;
	
    @Column(name = "code")
	private String code;
    
    @Column(name = "message")
    private String message;
    
    @Column(name = "status")
    private int status;
}
