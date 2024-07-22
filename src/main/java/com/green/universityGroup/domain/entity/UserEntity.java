package com.green.universityGroup.domain.entity;

import java.util.Set;
import java.util.HashSet;
import org.hibernate.annotations.DynamicUpdate;

import com.green.universityGroup.domain.dto.UserUpdateDTO;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicUpdate
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE) // 빌더를 사용하려고 생성자초기화 막아주는것
@NoArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 값 생성
	private long user_no;

	@Column(nullable = false, length = 50)
	private String username;

	@Column(columnDefinition = "varchar(100)", nullable = false, unique = true)
	private String email;

	@Column(columnDefinition = "varchar(255)", nullable = false)
	private String password;

	@Column(nullable = false)
	private String profile_image;

	// StudentEntity와의 관계
	@OneToOne(mappedBy = "user")
	private StudentEntity student;

	// ProfessorEntity와의 관계
	@OneToOne(mappedBy = "user")
	private ProfessorEntity professor;

	@Enumerated(EnumType.STRING) // DB저장 데이터타입 문자로 저장 : default 숫자로 저장
	@CollectionTable(name = "role", // RoleEntity 이름
			joinColumns = @JoinColumn(name = "no")) // 선택:자동으로 만들어주지만 fk컬럼명
													// member_no //collection을 Table로 변환시키겠다
	@ElementCollection(fetch = FetchType.EAGER) // EAGER(즉시로딩(기본은 LAZY(지연로딩)) //해당클래스에서만 접근가능한 테이블로 만들어주겠다 // 1:N
	@Builder.Default // Builder를 사용할때 자동으로 초기화 됩니다. //null로 초기화 되지않고 HashSet의 값으로 초기화 된다
	@Column(name = "role_name") // 선택:자동으로 만들어주지만 Role 엔티티에서 컬럼명을 커스텀할때 사용가능
	private Set<Role> roles = new HashSet<>();

	// Role 등록하기위한 편의메서드
	public UserEntity addRole(Role role) {
		roles.add(role);
		return this;
	}

	public UserEntity addRoleByRange(String... rolesToAdd) {
		for (String roleName : rolesToAdd) {
			if (Role.valueOf(roleName).equals(Role.PROFESSOR) || Role.valueOf(roleName).equals(Role.STUDENT)) {
				roles.add(Role.valueOf(roleName));
			}
		}
		return this;
	}

	public UserEntity update(UserUpdateDTO dto) {
		this.profile_image=dto.getProfile_image();
		return this;
		
		
	}
}