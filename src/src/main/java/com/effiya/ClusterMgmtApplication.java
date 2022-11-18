package com.effiya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.effiya")
@EntityScan("com.effiya.cm.model")
public class ClusterMgmtApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClusterMgmtApplication.class, args);
	}

}
