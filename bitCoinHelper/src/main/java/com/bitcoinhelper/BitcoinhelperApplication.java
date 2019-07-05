package com.bitcoinhelper;

import com.bitcoinhelper.platform.OKEX;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.*")
public class BitcoinhelperApplication implements CommandLineRunner {
	@Autowired
	private OKEX okex;
	public static void main(String[] args) {
		SpringApplication.run(BitcoinhelperApplication.class, args);
	}

	@Override
	public void run(String... strings) throws Exception {
		okex.init();
	}
}
