package com.project.login.api.lib;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

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
public class MailUtil {
	public static InternetAddress[] listToArray(List<String> addressList) {
		ArrayList<InternetAddress> listOfToAddress = new ArrayList<>();
		addressList.forEach(
			e->{
				try {
					listOfToAddress.add(new InternetAddress(e));
				} catch (AddressException ex) {
					throw new RuntimeException(ex);
				}
			}
		);

		return listOfToAddress.toArray(new InternetAddress[0]);
	}
}
