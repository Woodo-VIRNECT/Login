package com.project.login.api.v1.dto.request;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Project        : login
 * DATE           : 2022/12/14
 * AUTHOR         : dnejdzlr2 (Woodo Lee)
 * EMAIL          : dnejdzlr2@virnect.com
 * DESCRIPTION    :
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022/12/14      dnejdzlr2          최초 생성
 */

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class MailDto {
	private String fromAddress;
	private List<String> toAddressList = new ArrayList<>();
	private List<String> ccAddressList = new ArrayList<>();
	private List<String> bccAddressList = new ArrayList<>();
	private String subject; // 제목
	private String content; // 메일 내용
	private boolean isUseHtmlYn; // 메일 형식이 HTML인지 여부(true, false)
	private List<AttachFileDto> attachFileList = new ArrayList<>();
}
