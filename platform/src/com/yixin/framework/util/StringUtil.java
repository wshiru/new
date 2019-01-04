/*
 * Project platform
 *
 * Classname StringUtils.java
 * 
 * Version 1.0.0
 * 
 * Creator 
 * CreateAt 2011-6-1 下午02:40:10
 *
 * ModifiedBy 无
 * ModifyAt 无
 * ModifyDescription 无
 * 
 * Copyright (c) 2009-2011 亿鑫新能源 版权所有
 */
package com.yixin.framework.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类。实现了对字符的长度判断、去除空白字符、替换指定字符等功能。
 * 
 * @version v1.0.0
 * @author 
 */
public abstract class StringUtil {

	/**
	 * 检查所给定的String是否拥有长度。当为<code>null</code>或长度为0时，返回<code>false</code> ，否则返回
	 * <code>true</code><br />
	 * <b>注意：</b>当String只含有空白字符时，也会返回<code>true</code>
	 * 
	 * @param str
	 *            要检查的String
	 * @return <code>true</true> - str不为空，且长度大于0；<br />
	 *         <code>false</code> - str为空，或者长度为0；
	 */
	public static boolean hasLength(String str) {
		return (null != str && str.length() > 0);
	}

	/**
	 * 
	 * 检查所给定的String是否拥有真正的内容。即包含除空白字符外的其他内容。
	 * 
	 * @param str
	 *            要检查的String
	 * @return <code>true</true> - str不为空，且至少包含一个非空白字符；<br />
	 *         <code>false</code> - str为空，或者长度为0，或者只包含空白字符；
	 */
	public static boolean containsText(String str) {
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (!Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 检查所给定的String是否包含任何空白字符。
	 * 
	 * @param str
	 *            要检查的String
	 * @return <code>true</true> - str不为空，且至少包含一个空白字符；
	 * @see #containsWhitespace(CharSequence)
	 */
	public static boolean containsWhitespace(String str) {
		if (!hasLength(str)) {
			return false;
		}
		int strLen = str.length();
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(str.charAt(i))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 去除给定String开头与尾端的空白字符
	 * 
	 * @param str
	 *            要检查的String
	 * @return 去除开头与尾端空白字符后的字符串
	 * @see java.lang.Character#isWhitespace
	 */
	public static String trimWhitespace(String str) {
		return trimTrailingWhitespace(trimLeadingWhitespace(str));
	}

	/**
	 * 去除给定String中包含的任何空白字符。包括开头、尾端、字符串中间的所有空白字符。
	 * 
	 * @param str
	 *            要检查的String
	 * @return 去除空白字符后的字符串
	 * @see java.lang.Character#isWhitespace
	 */
	public static String trimAllWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		int index = 0;
		while (sb.length() > index) {
			if (Character.isWhitespace(sb.charAt(index))) {
				sb.deleteCharAt(index);
			} else {
				index++;
			}
		}
		return sb.toString();
	}

	/**
	 * 去除给定String的开头空白字符。
	 * 
	 * @param str
	 *            要检查的String
	 * @return 去除开头空白字符后的字符串
	 * @see java.lang.Character#isWhitespace
	 */
	public static String trimLeadingWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() > 0 && Character.isWhitespace(sb.charAt(0))) {
			sb.deleteCharAt(0);
		}
		return sb.toString();
	}

	/**
	 * 去除给定String的尾端空白字符。
	 * 
	 * @param str
	 *            要检查的String
	 * @return 去除尾端空白字符后的字符串
	 * @see java.lang.Character#isWhitespace
	 */
	public static String trimTrailingWhitespace(String str) {
		if (!hasLength(str)) {
			return str;
		}
		StringBuilder sb = new StringBuilder(str);
		while (sb.length() > 0 && Character.isWhitespace(sb.charAt(sb.length() - 1))) {
			sb.deleteCharAt(sb.length() - 1);
		}
		return sb.toString();
	}

	/**
	 * 去除给定字符串str中以character开头与结尾的字符
	 * 
	 * @param str
	 *            要检查的字符串
	 * @param character
	 *            要去除的字符
	 * @return 去除字符character后的字符串。当 <code>null == str</code> 时，返回空字符串
	 * @see #trimString(String, String)
	 */
	public static String trimCharacter(String str, char character) {
		if (!hasLength(str)) {
			return "";
		}
		return trimString(str, String.valueOf(character));
	}

	/**
	 * 去除给定字符串str中出现的任何character字符。包括开头、尾端、字符串中间出现的。
	 * 
	 * @param str
	 *            要检查的字符串
	 * @param character
	 *            要去除的字符
	 * @return 去除字符character后的字符串。当 <code>null == str</code> 时，返回空字符串
	 * @see #replace(String, String, String)
	 */
	public static String trimAllCharacter(String str, char character) {
		if (!hasLength(str)) {
			return "";
		}
		return trimAllString(str, String.valueOf(character));
	}

	/**
	 * 去除给定字符串str中以leadingCharacter开头的字符
	 * 
	 * @param str
	 *            要检查的字符串
	 * @param leadingCharacter
	 *            要去除的字符
	 * @return 去除字符leadingCharacter后的字符串。当 <code>null == str</code> 时，返回空字符串
	 * @see #trimLeadingString(String, String)
	 */
	public static String trimLeadingCharacter(String str, char leadingCharacter) {
		if (!hasLength(str)) {
			return "";
		}
		return trimLeadingString(str, String.valueOf(leadingCharacter));
	}

	/**
	 * 去除给定字符串str中以trailingCharacter结尾的字符
	 * 
	 * @param str
	 *            要检查的字符串
	 * @param trailingCharacter
	 *            要去除的字符
	 * @return 去除字符trailingCharacter后的字符串。当 <code>null == str</code> 时，返回空字符串
	 * @see #trimTrailingString(String, String)
	 */
	public static String trimTrailingCharacter(String str, char trailingCharacter) {
		if (!hasLength(str)) {
			return "";
		}
		return trimTrailingString(str, String.valueOf(trailingCharacter));
	}

	/**
	 * 去除给定字符串str中以subString开头与结尾的字符串
	 * 
	 * @param str
	 *            要检查的字符串
	 * @param subString
	 *            要去除的字符串
	 * @return 去除字符subString后的字符串。当 <code>null == str</code> 时，返回空字符串
	 */
	public static String trimString(String str, String subString) {
		if (!hasLength(str)) {
			return "";
		}
		if (null == subString || 0 == subString.length()) {
			return str;
		}
		String result = trimLeadingString(str, subString);
		result = trimTrailingString(result, subString);
		return result;
	}

	/**
	 * 去除给定字符串str中出现的任何subString字符。包括开头、尾端、字符串中间出现的。
	 * 
	 * @param str
	 *            要检查的字符串
	 * @param subString
	 *            要去除的字符
	 * @return 去除字符subString后的字符串。当 <code>null == str</code> 时，返回空字符串
	 * @see #replace(String, String, String)
	 */
	public static String trimAllString(String str, String subString) {
		if (!hasLength(str)) {
			return "";
		}
		if (null == subString || 0 == subString.length()) {
			return str;
		}
		return replace(str, subString, "");
	}

	/**
	 * 去除给定字符串str中以leadingString开头的字符
	 * 
	 * @param str
	 *            要检查的字符串
	 * @param leadingString
	 *            要去除的字符串
	 * @return 去除字符leadingString后的字符串。当 <code>null == str</code> 时，返回空字符串
	 */
	public static String trimLeadingString(String str, String leadingString) {
		if (!hasLength(str)) {
			return "";
		}
		if (null == leadingString || 0 == leadingString.length()) {
			return str;
		}
		String result = str;
		while (startsWithIgnoreCase(result, leadingString)) {
			result = result.substring(leadingString.length());
		}
		return result;
	}

	/**
	 * 去除给定字符串str中以trailingString结尾的字符
	 * 
	 * @param str
	 *            要检查的字符串
	 * @param trailingString
	 *            要去除的字符串
	 * @return 去除字符trailingString后的字符串。当 <code>null == str</code> 时，返回空字符串
	 */
	public static String trimTrailingString(String str, String trailingString) {
		if (!hasLength(str)) {
			return "";
		}
		if (null == trailingString || 0 == trailingString.length()) {
			return str;
		}
		String result = str;
		int trailingCharLen = trailingString.length();
		while (endsWithIgnoreCase(result, trailingString)) {
			result = result.substring(0, result.length() - trailingCharLen);
		}
		return result;
	}

	/**
	 * 检查给定字符串str是否以prefix开头。<b>注意：</b>不区分大小写
	 * 
	 * @param str
	 *            要检查的字符串
	 * @param prefix
	 *            开头的字符串
	 * @return <code>true</code> - 字符串str以prefix开头<br />
	 *         <code>false</code> - 字符串str不以prefix开头<br />
	 * @see java.lang.String#startsWith
	 */
	public static boolean startsWithIgnoreCase(String str, String prefix) {
		if (null == str || null == prefix) {
			return false;
		}
		if (str.length() < prefix.length()) {
			return false;
		}
		String lowerStr = str.substring(0, prefix.length()).toLowerCase();
		String lowerPrefix = prefix.toLowerCase();
		return lowerStr.equals(lowerPrefix);
	}

	/**
	 * 检查给定字符串str是否以prefix结尾。<b>注意：</b>不区分大小写
	 * 
	 * @param str
	 *            要检查的字符串
	 * @param suffix
	 *            结尾的字符串
	 * @return <code>true</code> - 字符串str以suffix结尾<br />
	 *         <code>false</code> - 字符串str不以suffix结尾<br />
	 */
	public static boolean endsWithIgnoreCase(String str, String suffix) {
		if (null == str || null == suffix) {
			return false;
		}
		if (str.length() < suffix.length()) {
			return false;
		}
		String lowerStr = str.substring(str.length() - suffix.length()).toLowerCase();
		String lowerPrefix = suffix.toLowerCase();
		return lowerStr.equals(lowerPrefix);
	}

	/**
	 * 统计子串sub在给定字符串str中出现的次数。
	 * 
	 * @param str
	 *            要检查的字符串
	 * @param sub
	 *            要匹配的子串
	 * @return 子串sub在给定字符串str中出现的次数
	 */
	public static int countOccurrencesOf(String str, String sub) {
		if (null == str || null == sub || 0 == str.length() || 0 == sub.length()) {
			return 0;
		}
		int count = 0;
		int pos = 0;
		int idx;
		while ((idx = str.indexOf(sub, pos)) != -1) {
			++count;
			pos = idx + sub.length();
		}
		return count;
	}

	/**
	 * 用子串newSubString替换给定字符串inString中所有出现的子串oldSubString。
	 * 
	 * @param inString
	 *            要检查的字符串
	 * @param oldSubString
	 *            inString串中出现的子串
	 * @param newSubString
	 *            用于替换oldSubString的字符串
	 * @return 替换后的新字符串
	 */
	public static String replace(String inString, String oldSubString, String newSubString) {
		if (!hasLength(inString) || !hasLength(oldSubString) || null == newSubString) {
			return inString;
		}
		Pattern p = Pattern.compile(oldSubString);
		Matcher m = p.matcher(inString);
		return m.replaceAll(newSubString);
	}

	/**
	 * 删除所有匹配的字符串。
	 * 
	 * @param str
	 *            要检查的字符串
	 * @param subString
	 *            要删除的子串
	 * @return 删除后的字符串
	 */
	public static String delete(String str, String subString) {
		return replace(str, subString, "");
	}

	/**
	 * 将给定字符串str与原数组array组成一个新的数组并返回。str将在新数组的最后一个元素。
	 * 
	 * @param array
	 *            被追加的数组。可以为 <code>null</code>
	 * @param str
	 *            要追加的字符串
	 * @return 组成的新数组。当 <code>null == str</code> 时返回空数组。
	 */
	public static String[] addStringToArray(String[] array, String str) {
		if (ArrayUtil.isEmpty(array)) {
			return null == str ? new String[] {} : new String[] { str };
		}
		String[] newArr = new String[array.length + 1];
		System.arraycopy(array, 0, newArr, 0, array.length);
		newArr[array.length] = str;
		return newArr;
	}

	/**
	 * 将两个数组组合成一个新数组并返回。
	 * 
	 * @param array1
	 *            要组合的第一个数组
	 * @param array2
	 *            要组合的第二个数组
	 * @return 组合成的新数组。当 <code>null == array1 && null == array2</code> 为
	 *         <code>true</code> 时，返回空数组
	 */
	public static String[] concatenateStringArrays(String[] array1, String[] array2) {
		if (null == array1 && null == array2) {
			return new String[0];
		}
		if (ArrayUtil.isEmpty(array1)) {
			return array2;
		}
		if (ArrayUtil.isEmpty(array2)) {
			return array1;
		}
		String[] newArr = new String[array1.length + array2.length];
		System.arraycopy(array1, 0, newArr, 0, array1.length);
		System.arraycopy(array2, 0, newArr, array1.length, array2.length);
		return newArr;
	}

	/**
	 * 将两个数组合并，有重复元素时，只保留一份。
	 * 
	 * @param array1
	 *            要合并的第一个数组
	 * @param array2
	 *            要合并的第二个数组
	 * @return 合并后的新数组。当 <code>null == array1 && null == array2</code> 为
	 *         <code>true</code> 时，返回空数组
	 */
	public static String[] mergeStringArrays(String[] array1, String[] array2) {
		if (null == array1 && null == array2) {
			return new String[0];
		}
		if (ArrayUtil.isEmpty(array1)) {
			return array2;
		}
		if (ArrayUtil.isEmpty(array2)) {
			return array1;
		}
		List<String> result = new ArrayList<String>();
		result.addAll(Arrays.asList(array1));
		for (String str : array2) {
			if (!result.contains(str)) {
				result.add(str);
			}
		}
		return toStringArray(result);
	}

	/**
	 * 排序数组
	 * 
	 * @param array
	 *            要排序的数组
	 * @return 排序后的数组。当 <code>null == array</code> 时，返回空数组。
	 * @see java.util.Arrays#sort(Object[])
	 */
	public static String[] sortStringArray(String[] array) {
		if (ArrayUtil.isEmpty(array)) {
			return new String[0];
		}
		Arrays.sort(array);
		return array;
	}

	/**
	 * 将集合转换成字符串数组
	 * 
	 * @param collection
	 *            要转换的集合
	 * @return 转换后的字符串数组。当 <code>null == collection</code> 为 <code>true</code>
	 *         时，返回空数组
	 */
	public static String[] toStringArray(Collection<String> collection) {
		if (null == collection) {
			return new String[0];
		}
		return collection.toArray(new String[collection.size()]);
	}

	/**
	 * 去除数组中每个元素的开头空白与结尾空白
	 * 
	 * @param array
	 *            要检查的数组
	 * @return 去除每个数组元素组成的新数组。当 <code>null == array</code> 为 <code>true</code>
	 *         时，返回空数组
	 */
	public static String[] trimArrayElements(String[] array) {
		if (ArrayUtil.isEmpty(array)) {
			return new String[0];
		}
		String[] result = new String[array.length];
		for (int i = 0; i < array.length; i++) {
			String element = array[i];
			result[i] = (null == element ? null : element.trim());
		}
		return result;
	}

	/**
	 * 根据给定正则表达式的匹配拆分此字符串，同时去除分隔后每个数组元素的开头和尾端空白字符。
	 * 
	 * @param str
	 *            要拆分的字符串
	 * @param regex
	 *            定界正则表达式
	 * @return 分隔后组成的数组
	 * @see java.lang.String#split(String)
	 */
	public static String[] split(String str, String regex) {
		String[] strArray = str.split(regex);
		for (int i = 0, len = strArray.length; i < len; i++) {
			strArray[i] = trimWhitespace(strArray[i]);
		}
		return strArray;
	}
}
