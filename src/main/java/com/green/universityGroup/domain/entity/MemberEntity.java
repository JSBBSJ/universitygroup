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
	private long memberNo;
	
	@Column(nullable = false)
	private String memberName;
	
	
	@Column(nullable = false)
	private String memberSub;
	
	
	@Column(nullable = false)
	private String memberRoll;
	
	
	@Column(nullable = false)
	private String memberNum;
	
	
	public MemberDTO toListDTO() {
	
		return MemberDTO.builder()

				.member_no(memberNo)
				.member_name(memberName)
				.member_sub(memberSub)
				.member_roll(memberRoll)
				.member_num(memberNum)
				.build();

	}

	
}