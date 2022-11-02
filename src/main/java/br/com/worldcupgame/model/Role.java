package br.com.worldcupgame.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import br.com.worldcupgame.enums.RoleEnum;

@Entity
@Table(name = "tb_role")
public class Role implements GrantedAuthority {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Integer id;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, unique = true)
	private RoleEnum roleName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public RoleEnum getTipoRole() {
		return roleName;
	}

	public void setTipoRole(RoleEnum roleName) {
		this.roleName = roleName;
	}

	@Override
	public String getAuthority() {
		return this.roleName.toString();
	}
	
	
}
