package com.portfolio.portfoliomvc.util.random;

import java.util.UUID;

public class RandomFileName {
	
	public static String generateRandomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}

