package com.nhbank.batchquartz.dto;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TestDTO {
	
	private Long id;
    private String name;
    private LocalDateTime createdAt;
    
}
