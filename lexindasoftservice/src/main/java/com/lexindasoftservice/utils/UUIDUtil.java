package com.lexindasoftservice.utils;

import java.util.UUID;

public class UUIDUtil {
	public static String getUUID(){
		 UUID uuid = UUID.randomUUID();
         return uuid.toString().toLowerCase().replace("-","");
	}
	
	
}
