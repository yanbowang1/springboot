package com.demo.springboot.wyb.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: user
 * @date: 2018/01/01
 */
public class DeleteJS {
	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";
	private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";
	private static final String regEx_html = "<[^>]+>";
	private static final String regEx_space = "\\s*|\t|\r|\n";
	private static final String regEx_special = "[()''<>\"\"]";

	/**
	 * @param htmlStr
	 * @return 鍒犻櫎Html鏍囩
	 */
	private static String delHTMLTag(String htmlStr) {
		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll("");

		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll("");

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll("");

		Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
		Matcher m_space = p_space.matcher(htmlStr);
		htmlStr = m_space.replaceAll("");

		Pattern p_specialString = Pattern.compile(regEx_special, Pattern.CASE_INSENSITIVE);
		Matcher m_specialString = p_specialString.matcher(htmlStr);
		htmlStr = m_specialString.replaceAll("");

		return htmlStr.trim();
	}

	public static String getTextFromHtml(String htmlStr) {
		if (htmlStr != null && !"".equals(htmlStr)) {
			return null;
		}
		htmlStr = delHTMLTag(htmlStr);
		htmlStr = htmlStr.replaceAll("&nbsp;", "");
		return htmlStr;
	}

	public static void main(String[] args) {
		String aa = "aaaa-xxxx-sss-MM%-dd";
		System.out.println(aa);
		System.out.println(getTextFromHtml(aa));
	}

	public static String delHTMLTag2(String htmlStr) {
		String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>";
		String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>";
		String regEx_html = "<[^>]+>";

		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll("");

		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll("");

		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll("");

		return htmlStr.trim().replace("&nbsp;", "");
	}
}