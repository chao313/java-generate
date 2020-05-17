var queryBase = "http://127.0.0.1:8000/demomaster/TTitleController/queryBase";
var host = "http://127.0.0.1:8000";

/* 设置后台项目名称 */
var BASE_URL = host + '/demomaster';
var WEBSTROM_BASE_URL = '/static';

/** Session操作 */
var login = BASE_URL + '/SessionController/login';
var adminLogin = BASE_URL + '/SessionController/admin/login';
/* 登出 */
var loginOut = BASE_URL + '/SessionController/loginOut';
/* 获取用户的id */
var getUserId = BASE_URL + '/SessionController/getUserId';
/* 获取用户的Name */
var getUserName = BASE_URL + '/SessionController/getUserName';

/* 配合富文本的文件url */
var uploadFile = BASE_URL + '/EditorController/uploadFile';

/* 获取验证码url */
var getCheckCode = BASE_URL + '/EmailController/getCheckCode';

/* 验证验证码url */
var validCheckCode = BASE_URL + '/EmailController/validCheckCode';

/* 密码找回url */
var forgetPassword = BASE_URL + '/EmailController/forgetPassword';


function uuid() {
    var s = [];
    var hexDigits = "0123456789abcdef";
    for (var i = 0; i < 36; i++) {
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
    }
    s[14] = "4"; // bits 12-15 of the time_hi_and_version field to 0010
    s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1); // bits 6-7 of the clock_seq_hi_and_reserved to 01
    s[8] = s[13] = s[18] = s[23] = "-";

    var uuid = s.join("");
    return uuid;
}

//  先使用encodeURIComponent 将中文转成ANSI
function base64Encode(str) {
    return btoa(encodeURIComponent(str));
}

// 将ANSI转成中文
function base64Decode(str) {
    return decodeURIComponent(atob(str));
}


function queryUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i"); // 匹配目标参数
    var result = window.location.search.substr(1).match(reg); // 对querystring匹配目标参数
    if (result != null) {
        return decodeURIComponent(result[2]);
    } else {
        return null;
    }
}

/**
 * 补全长度
 * @param milliseconds
 * @returns {*}
 */
function dealMilliseconds(milliseconds) {
    if (milliseconds.length == 1) {
        return milliseconds + '00';
    } else if (milliseconds.length == 2) {
        return milliseconds + '0';
    } else {
        return milliseconds;
    }


}

function dateFormat(fmt, date) {
    var ret;
    var opt = {
        "Y+": date.getFullYear().toString(),        // 年
        "m+": (date.getMonth() + 1).toString(),     // 月
        "d+": date.getDate().toString(),            // 日
        "H+": date.getHours().toString(),           // 时
        "M+": date.getMinutes().toString(),         // 分
        "S+": date.getSeconds().toString(),         // 秒
        "s+": dealMilliseconds(date.getMilliseconds().toString())          // 毫秒
        // 有其他格式化字符需求可以继续添加，必须转化成字符串
    };
    for (var k in opt) {
        ret = new RegExp("(" + k + ")").exec(fmt);
        if (ret) {
            fmt = fmt.replace(ret[1], (ret[1].length == 1) ? (opt[k]) : (opt[k].padStart(ret[1].length, "0")))
        }
        ;
    }
    ;
    return fmt;
}