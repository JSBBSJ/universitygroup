package com.green.universityGroup.ssh;

import java.util.Random;

import javax.sql.DataSource;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@Profile("ssh")
@Configuration
public class SSHTunnelingConfig {
	/*
	@Bean
	JSch jsch(SSHTunnelingProperties sshTunnelingProperties) throws JSchException {
		JSch jsch=new JSch();
		jsch.addIdentity(sshTunnelingProperties.getPrivateKey());
		return new JSch();
	}
	*/
	
	//HikariDataSource 빈 수동 생성
	@Bean
	DataSource dataSource(
			DataSourceProperties dataSourceProperties,
			SSHTunnelingProperties sshTunnelingProperties) throws JSchException {
		//System.out.println(">>>>SSHTunnelingProperties:" + sshTunnelingProperties);
		//ec2-tunneling
		//Java로 작성된 SSH2 구현 라이브러리
		JSch jsch=new JSch();
		//파일을 읽는것이기 떄문에 예외전가가 필요함
		//sshTunnelingProperties에 저장해둔 properties
		jsch.addIdentity(sshTunnelingProperties.getPrivateKey());
	
		
		Session session=jsch.getSession(
				sshTunnelingProperties.getUsername(),
				sshTunnelingProperties.getSshHost(),
				sshTunnelingProperties.getSshPort());
		//호스트 키 검사 비활성화
		session.setConfig("StrictHostKeyChecking", "no");
		session.connect();
		
		//localPort를 하나로 고정해서 사용하다보니 수정시 이미 바인딩된 포트라고 떠서
		//램덤으로 적용했어요
		int lport=new Random().nextInt(999)+33001;
		
		int localPort=session.setPortForwardingL(
				lport,
				sshTunnelingProperties.getRdsHost(),
				sshTunnelingProperties.getRdsPort());
		
		
		//DataSource 정보를 세팅 실제 접속정보(rds)정보
		HikariConfig config=new HikariConfig();
		String url=dataSourceProperties.getUrl();
		config.setJdbcUrl(url.replace("[LOCAL_PORT]", String.valueOf(localPort)));
		config.setDriverClassName(dataSourceProperties.getDriverClassName());
		config.setUsername(dataSourceProperties.getUsername());
		config.setPassword(dataSourceProperties.getPassword());
		return new HikariDataSource(config);
	}

}
