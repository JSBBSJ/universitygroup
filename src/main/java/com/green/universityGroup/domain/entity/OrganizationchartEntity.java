package com.green.universityGroup.domain.entity;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DynamicUpdate
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Entity
@Table(name = "oranization") //주소록임 주소록!!(전자결재용 주소록)
public class OrganizationchartEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //자동 증가 값 생성 
	private long oranizationNo;
	
	
	@Column(nullable = false, unique = true)
	private String oranizationField;
	
	
	@Column(nullable = false)
	private String oranizationName;
	
	

}
