package com.green.universityGroup.naver.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.ToString;

@ToString
@JsonIgnoreProperties(ignoreUnknown = true) //속서을 모두 매핑하려고 하면 힘들기때문에 없는것은 무시하기 위해 사용
@Getter
public class OrgUnit {
	
	//private List<String>aliasEmails;
	//private boolean canReceiveExternalMail;
	//private String description;
	private int displayLevel;
	private int displayOrder;
	//private int domainId;
	//private String email;
	//private List<OrgUniti18nName>i18nNames;
	//private List<OrgUnitAllowedMember>membersAllowedToUseOrgUnitEmailAsRecipient;
	//private List<OrgUnitAllowedMember>membersAllowedToUseOrgUnitEmailAsSender;
	private String orgUnitExternalKey;
	private String orgUnitId;		//부서id
	private String orgUnitName;   //부서이름
	private String parentExternalKey;
	private String parentOrgUnitId;
	//private boolean useCalendar;
	//private boolean useFolder;
	//private boolean useMessage;
	//private boolean useNote;
	//private boolean useServiceNotification;
	//private boolean useTask;
	//private boolean visible;
	
	
}
