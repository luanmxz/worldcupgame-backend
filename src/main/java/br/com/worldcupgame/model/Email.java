package br.com.worldcupgame.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import br.com.worldcupgame.enums.StatusEmail;
import lombok.Data;

@Data
@Entity
@Table(name= "tb_email")
public class Email {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long email_id;
	private String ownerRef;
	private String emailFrom;
	private String emailTo;
	private String subject;
	@Column(columnDefinition= "TEXT")
	private String text;
	private LocalDateTime sendDateEmail;
	private StatusEmail statusEmail;
	
}
