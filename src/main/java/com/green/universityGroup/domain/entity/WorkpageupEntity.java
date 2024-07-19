package com.green.universityGroup.domain.entity;

import java.util.function.Function;

import org.hibernate.annotations.DynamicUpdate;

import com.green.universityGroup.domain.dto.WorkpageupDTO;

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
@Table(name = "workpageup") //주소록임 주소록!!(전자결재용 주소록)
public class WorkpageupEntity {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //자동 증가 값 생성 
	private long workpageup_no;
	
	
	private String workpageup_field;
	
	
	private String workpageup_name;
	
	
	@OneToOne
    @JoinColumn(name = "oranization_no", referencedColumnName = "oranization_no")
	private OrganizationchartEntity oranization;


	public WorkpageupDTO tolistDto() {
		
		return WorkpageupDTO.builder()
				.oranization_field(oranization.getOranization_field())
				.oranization_name(oranization.getOranization_name())
				.build();
	}
	

}
