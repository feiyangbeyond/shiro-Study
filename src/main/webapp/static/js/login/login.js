function checkForm() {
	var pwdvalue = $("#pwd").val();
	pwdvalue = md5(pwdvalue);
	// 加密后的密码进行第二次加密（可以解密的加密）
	var uuidsalt = $("#uuidsalt").val();
	pwdvalue = encrypt(pwdvalue, uuidsalt, uuidsalt);
	// alert(pwdipt.value + "," + pwdipt.value.length);
	$("#pwd").val(pwdvalue);
	// alert(pwdvalue);
	if ($("#pwd").val(pwdvalue).length == 44) {
		return true;
	} else {
		return false;
	}
}

function encrypt(data, key, iv) { // key，iv：16位的字符串
	var key1 = CryptoJS.enc.Latin1.parse(key);
	var iv1 = CryptoJS.enc.Latin1.parse(iv);
	return CryptoJS.AES.encrypt(data,key1,{iv:iv1,mode:CryptoJS.mode.CBC,padding:CryptoJS.pad.ZeroPadding}).toString();
}