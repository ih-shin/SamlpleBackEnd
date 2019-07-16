package com.orz.base.domain.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Data;

@Entity
@Table(name="user")
@Data
@Builder
public class User implements Serializable{

	private static final long serialVersionUID = 7959749808764674010L;

	public User() {}
	
	public User(long userNo, String userEmail, String userPwd, String userNm, String certYn, String salt, String token) {
		this.userNo=userNo;
		this.userEmail=userEmail;
		this.userPwd=userPwd;
		this.userNm=userNm;
		this.certYn=certYn;
		this.salt=salt;
		this.token=token;
	}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
	public long userNo;
	
	@Column(name="user_email", nullable = false, length = 100)
	@Email(message = "메일 양식이 부적절합니다.")
	@NotBlank(message = "메일을 작성해주세요.")
	@Size(max = 100, message = "메일은 최대 100자까지 입력 가능합니다.")
	public String userEmail;

	@Column(name="user_pwd", nullable = false )
	@NotBlank(message = "비밀번호를 작성해주세요.")
	public String userPwd;

	@Column(name="user_nm", nullable = false, length = 100)
	@Size(min = 1, max = 100, message = "이름은 최대 33자까지 입력 가능합니다.")
	public String userNm;

	@Column(name="cert_yn", length = 1)
	public String certYn;

	@Column(name="salt")
	public String salt;
	
	@Transient
	public String token;
	
}
