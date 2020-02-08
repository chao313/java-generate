/**
 * Created by 陈雪颖
 */
export default {

	/*收费站编号*/
	istollGateNo: function(rule, value, callback) {
		if(!value) {
			return callback(new Error('该输入框不能为空'));
		}
		const reg = /^\d{1,20}$/;

		if(!reg.test(value)) {
			callback(new Error('请输入1-20位的数字'));
		} else {
			callback();
		}
	},

	/* 收费站名称*/
	istollGateName: function(rule, value, callback) {
		if(!value) {
			return callback(new Error('该输入框不能为空'));
		}
		const reg = /^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9]){1,20}$/;
		if(!reg.test(value)) {
			callback(new Error('请输入1-20个字符，可包含中文、英文、数字'));
		} else {
			callback();
		}
	},

	/*校验父级ID*/
	isParentId: function(rule, value, callback) {
		if(!value) {
			return callback(new Error('该输入框不能为空'));
		}
		const reg = /^[a-zA-Z0-9]{1,64}$/;

		if(!reg.test(value)) {
			callback(new Error('请输入1-64位的数字或字母'));
		} else {
			callback();
		}
	},

	/*用户名*/
	isUserId: function(rule, value, callback) {
		if(!value) {
			return callback(new Error('该输入框不能为空'));
		}
		const reg = /^[a-zA-Z0-9]{6,15}$/;

		if(!reg.test(value)) {
			callback(new Error('请输入6-15位的数字或字母'));
		} else {
			callback();
		}
	},

	/*姓名*/
	isRealName: function(rule, value, callback) {
		if(!value) {
			return callback(new Error('该输入框不能为空'));
		}
		const reg = /^[\u4e00-\u9fa5]{2,10}$/;

		if(!reg.test(value)) {
			callback(new Error('请输入2-10位汉字'));
		} else {
			callback();
		}
	},

	/*校验超重下限*/
	isLowerLimit: function(rule, value, callback) {

		if (value==0) {
			return callback(new Error('该输入框不能为0'));
		}
		if(!value) {
			return callback(new Error('该输入框不能为空'));
		}
		const reg =  /^[0-9]{0,3}(\.[0-9]{1,2})?$/;//
		if(!reg.test(value)) {
			callback(new Error('请输入数字，格式最多支持小数点前三位后两位',));
		} else {
			callback();
		}
	},

	/*校验手机号码和座机*/
	isPhoneNum: function(rule, value, callback) {
		//const regMobile = 11 && /^((13|14|15|17|18)[0-9]{1}\d{8})$/;
		//const regMobile = /^\d{11}$/ ;
		const regMobile = /^([0-9]{3,4}-)?[0-9]{7,8}$/ ;
		if(!value) {
			return callback(new Error('该输入框不能为空'));
		}
		if(!regMobile.test(value)) {
			callback(new Error('请输入格式如:0123-12345678'));
		} else {
			callback();
		}

	},

	/*校验所属高速*/
	isExpressway: function(rule, value, callback) {
		if(!value) {
			return callback(new Error('该输入框不能为空'));
		}
		const reg = /^([\u4E00-\uFA29]|[\uE7C7-\uE7F3]|[a-zA-Z0-9]){1,20}$/;
		if(!reg.test(value)) {
			callback(new Error('请输入1-20个字符，可包含中文、英文、数字'));
		} else {
			callback();
		}
	},
  /*校验车牌号码*/
  isPlateNo: function(rule, value, callback) {
    const reg = /^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领a-zA-Z]{1}[a-zA-Z]{1}[a-zA-Z0-9]{5}[黄蓝]{1}$/;
    if(!value) {
      return callback(new Error('请输入车牌号码'));
    }
    if(!reg.test(value)) {
      callback(new Error('格式:苏A12345黄'));
    } else {
      callback();
    }
  },
}
