function NumberUtil(){};

/**
 * 格式化数字
 * 
 * @param value 要格式化的值，将会去掉value中非数字部分
 * @param len 数学的总长度
 * @param precision 小数点位数。当precision == 0时，表示格式化为整数
 */
NumberUtil.format = function(value, len, precision) {	
	value = value.replace(/[^\d.]/g,""); // 去掉非数字字符
	value = value.replace(/^\./g,""); // 必须保证第一个为数字而不是.
	value = value.replace(/\.{2,}/g,"."); // 保证只有出现一个.而没有多个.
	
	/* 保证.只出现一次，而不能出现两次以上 */
	value = value.replace(".","$#$").replace(/\./g,"").replace("$#$",".");
	var reg = /^(\d+\.\d{2})\d*$/;

	// TODO
	value = value.toString().replace(reg,"$1");		
	return value;
};
