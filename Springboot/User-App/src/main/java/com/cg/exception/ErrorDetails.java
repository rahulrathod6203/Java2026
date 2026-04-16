package com.cg.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {

	private LocalDateTime timestamp;
	private String errorMessage;
	private String path;
	private String errorCode;

}