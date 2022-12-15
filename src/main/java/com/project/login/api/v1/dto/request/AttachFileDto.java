package com.project.login.api.v1.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
@AllArgsConstructor
@NoArgsConstructor
public class AttachFileDto {
	private String realFileNm;
	private String attachFileNm;
}
