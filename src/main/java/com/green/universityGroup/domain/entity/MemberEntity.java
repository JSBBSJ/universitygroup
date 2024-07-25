package com.green.universityGroup.domain.entity;

import java.util.Set;

import org.hibernate.annotations.DynamicUpdate;

import com.green.universityGroup.domain.dto.MemberDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicUpdate
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE) //빌더를 사용하려고 생성자초기화 막아주는것
@NoArgsConstructor
@Entity
@Table(name = "memeber")
public class MemberEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //자동 증가 값 생성 
	private long member_no;
	
	@Column(nullable = false)
	private String member_name;
	
	
	@Column(nullable = false)
	private String member_sub;
	
	
	@Column(nullable = false)
	private String member_roll;
	
	
	@Column(nullable = false)
	private String member_num;
	
	
	public MemberDTO toListDTO() {
	
		return MemberDTO.builder()

				.member_no(member_no)
				.member_name(member_name)
				.member_sub(member_sub)
				.member_roll(member_roll)
				.member_num(member_num)
				.build();

	}

	
}