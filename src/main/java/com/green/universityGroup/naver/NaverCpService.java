package com.green.universityGroup.naver;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.universityGroup.naver.dto.NaverTokenDTO;
import com.green.universityGroup.naver.dto.OrgUnitSaveDTO;
import com.green.universityGroup.naver.dto.ResponseResultDTO;
import com.green.universityGroup.utils.naver.OpenApiUtil;

import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Service
public class NaverCpService {
	
	private final OpenApiUtil openApiUtil;
	
	//private final ObjectMapper objectMapper; //빈으로 생성 완료 

	@Value("${naver.client.id}")
	private String clientId;
	
	@Value("${naver.client.secret}")
	private String clientSecret;
	
	@Value("${naver.client.domain}")
	private String domainId;
	
	
	
	public void orgUnitRead(String code, Model model) throws Exception {
		
		//접근을 위해 토큰을 생성 
		String responResult=getAccessToken(code);
		NaverTokenDTO result=openApiUtil.objectMapper(responResult, new TypeReference<NaverTokenDTO>(){}); 
		
		String accessToken=result.getAccess_token();
		
		//부서정보 조회 url
		String apiUrl="https://www.worksapis.com/v1.0/orgunits";
		
		StringBuilder urlBuilder= new StringBuilder(apiUrl);
		urlBuilder.append("?domainId=");urlBuilder.append(domainId);
		apiUrl=urlBuilder.toString();
		String method="GET";
		
		Map<String, String> headers=new HashMap<>();
		headers.put("Authorization", "Bearer "+accessToken);
		//JSON문자열 결과
		String orgUnitResponseresult=openApiUtil.request(apiUrl, headers, method, null);
		System.out.println("orgUnitResponseresult" + orgUnitResponseresult);
		
		ResponseResultDTO resultOrgUnits=openApiUtil.objectMapper(orgUnitResponseresult, new TypeReference<ResponseResultDTO>() {});
		//System.out.println(resultOrgUnits.getOrgUnits());
		//JSON문자열 -> JAVA의 객체로 
		
		model.addAttribute("list",resultOrgUnits.getOrgUnits());
		
	}


	private String getAccessToken(String code) {
		//문자열 + 연산하는 로직(이렇게 길고 빈번한 경우-성능상 좋지 않음) -> StingBuilder, String Buffer 이용하면 개선
		//String url="https://auth.worksmobile.com/oauth2/v2.0/token?code="+code+"&grant_type=authorization_code&client_id"+clientId+"&client_secret"+clientSecret;
		
		String apiUrl="https://auth.worksmobile.com/oauth2/v2.0/token";
		
		//Get요청 방식 
		StringBuilder urlBuilder= new StringBuilder(apiUrl);
		urlBuilder.append("?code=");urlBuilder.append(code);
		urlBuilder.append("&grant_type=authorization_code");
		urlBuilder.append("&client_id=");urlBuilder.append(clientId);
		urlBuilder.append("&client_secret=");urlBuilder.append(clientSecret);
		apiUrl=urlBuilder.toString();
		
		
		String method="POST";
		
		Map<String, String> headers=new HashMap<>();
		headers.put("Content-Type", "application/x-www-form-urlencoded");
		
		/*
		//post인 경우 json {key:value, key:value}
		Map<String, String> bodyData=new HashMap<>();
		bodyData.put("code", code);
		bodyData.put("grant_type", "authorization_code");
		bodyData.put("client_id", clientId);
		bodyData.put("client_secret", clientSecret);
		//bodyData =>json형식의 문자열로 변환 Gson
		String requestBody=objectMapper.writeValueAsString(bodyData);
		//System.out.println("requestBody: "+requestBody);
		*/
		
		//요청하는것 //아래에서 토근만 추출해야함 
		return openApiUtil.request(apiUrl, headers, method, null); //요청하고 나서 응답을 받습니다
		//System.out.println("responResult:"+responResult); //json 형식의 문자열로 리턴
		
		//Json문자열을->객체로 변환(T타입에 정의한)
		
		//System.out.println(">>>>>"+result);
		
	}


	//부서 등록하는 저장 메서드
	public void savedepartment(OrgUnitSaveDTO dto) {
		//
		
	}
	

}
