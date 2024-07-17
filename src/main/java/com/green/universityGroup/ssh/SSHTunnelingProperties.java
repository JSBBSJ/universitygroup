package com.green.universityGroup.ssh;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@Component
@ConfigurationProperties("spring.ssh.tunnel") //properties의 내용을 기반으로
public class SSHTunnelingProperties {

	
	private String username;
	private String sshHost;
	private int sshPort;
	private String privateKey;
	//private int localPort; //config에서 랜덤적용
	private String rdsHost;
	private int rdsPort;


}
