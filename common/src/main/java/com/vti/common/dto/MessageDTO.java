package com.vti.common.dto;

import java.util.Date;

import lombok.Data;

@Data
public class MessageDTO {
	public Date timestamp;
	public String event;
	public Object content;

}
