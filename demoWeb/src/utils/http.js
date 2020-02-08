import axios from 'axios';
import {Loading, MessageBox} from 'element-ui';
import store from '../store/'
/* 引入其他组件*/
import api from './api'

export default {

  /**
   * 统一接口处理：get请求方法封装。this.$http.getUseToken(url, [options])
   * 请求之前会请求token接口 -> 在请求头中放入token
   */
  getUseToken: function (url, options = {}, sucCb, errorCb, isLoading = true, isLogin) {
    let self = this
    var res = axios.get(self.api.TokenCsrfGetNotReplace).then(function (response) {
      console.log(response.data.code);
    });
    console.log(res.data.code);
    if (!url) {
      return false;
    }
    //遮罩层
    if (isLoading) {
      var loadingInstance = Loading.service({
        text: "加载中,请稍后..."
      });
    }
    axios.get(url, options).then((response) => {
      console.info(response);
      // 响应成功回调
      if (response.data.errorCode) {
        MessageBox({
          title: '系统提示',
          message: '错误码: ' + response.data.errorCode + '<br/>' + '错误信息: ' + response.data.errorMessage,
          confirmButtonText: '确定',
          closeOnPressEscape: false,
          showCancelButton: false,
          dangerouslyUseHTMLString: true
        }).then(() => {
          if (response.data.errorCode == "system_login_noUserInfo") {
            store.dispatch('LogOut');
            location.reload();
          }
        }).catch(() => {

        });
      } else {
        sucCb(response.data);
      }
      if (isLoading) {
        loadingInstance.close();
      }
    }, (response) => {
      errorCb(response);
      if (isLoading) {
        setTimeout(() => {
          loadingInstance.close();
        }, 2000);
      }
    })
  },
  /**
   * 统一接口处理：post请求方法封装。this.$http.postUseToken(url, [options])
   * @param url { String } -必选 接口url
   * @param body { Object } -必选 含官方的所有body对象，可为null。传参时不需要参数名，例如body为{key:11}
   * @param options { Object } -必选 含官方的所有options对象，可为null。传参为{params:{key:11}}
   * @param sucCb { Function } -必选 成功回调
   * @param errorCb { Function } -可选 失败回调
   * @param isLoading { Boolean } -可选 是否显示加载状态
   * @param isLogin { Boolean } -可选 是否登陆信息（
   */
  postUseToken: async function (url, body, options, sucCb, errorCb, isLoading = true, isLogin) {
    if (!url) {
      // console.log('接口url不能为空！');
      return false;
    }
    let token;
    let headerName;
    var res = await axios.get(api.TokenCsrfGetNotReplace).then(function (response) {
      if (response.data.code == '0') {
        let content = response.data.content;
        token = content.token;
        headerName = content.headerName;
        options.headers[headerName] = token;
      } else {
        MessageBox({
          title: '系统提示',
          message: '错误码: ' + response.data.code + '<br/>' + '错误信息: ' + response.data.msg,
          confirmButtonText: '确定',
          closeOnPressEscape: false,
          showCancelButton: false,
          dangerouslyUseHTMLString: true
        })
      }
    });
    //遮罩层
    if (isLoading) {
      var loadingInstance = Loading.service();
    }
    axios.post(url, body, options).then((response) => {
      // 响应成功回调
      if (response.data.code != '0') {
        MessageBox({
          title: '系统提示',
          message: '错误码: ' + response.data.code + '<br/>' + '错误信息: ' + response.data.msg,
          confirmButtonText: '确定',
          closeOnPressEscape: false,
          showCancelButton: false,
          dangerouslyUseHTMLString: true
        }).then(() => {

        }).catch(() => {

        });
      } else {
        sucCb(response.data);
      }
      if (isLoading) {
        loadingInstance.close();
      }

    }, (response) => {
      // 响应错误回调
      errorCb(response);
      if (isLoading) {
        loadingInstance.close();
      }
    })
  },


  /**
   * 统一接口处理：get请求方法封装。this.$http.get(url, [options])
   * @param url { String } -必选 接口url
   * @param options { Object } -必选 含官方的所有options对象。传参为{params:{key:11}}
   * @param sucCb { Function } -必选 成功回调
   * @param errorCb { Function } -可选 失败回调
   * @param isLoading { Boolean } -可选 是否显示加载状态
   * @param isLogin { Boolean } -可选 是否登陆信息
   */
  get: function (url, options = {}, sucCb, errorCb, isLoading = true, isLogin) {
    if (!url) {
      return false;
    }
    //遮罩层
    if (isLoading) {
      var loadingInstance = Loading.service({
        text: "加载中,请稍后..."
      });
    }
    axios.get(url, options).then((response) => {
      console.info(response);
      // 响应成功回调
      if (response.data.errorCode) {
        MessageBox({
          title: '系统提示',
          message: '错误码: ' + response.data.errorCode + '<br/>' + '错误信息: ' + response.data.errorMessage,
          confirmButtonText: '确定',
          closeOnPressEscape: false,
          showCancelButton: false,
          dangerouslyUseHTMLString: true
        }).then(() => {
          if (response.data.errorCode == "system_login_noUserInfo") {
            store.dispatch('LogOut');
            location.reload();
          }
        }).catch(() => {

        });
      } else {
        sucCb(response.data);
      }
      if (isLoading) {
        loadingInstance.close();
      }
    }, (response) => {
      errorCb(response);
      if (isLoading) {
        setTimeout(() => {
          loadingInstance.close();
        }, 2000);
      }
    })
  },

  /**
   * 统一接口处理：post请求方法封装。this.$http.get(url, [options])
   * @param url { String } -必选 接口url
   * @param body { Object } -必选 含官方的所有body对象，可为null。传参时不需要参数名，例如body为{key:11}
   * @param options { Object } -必选 含官方的所有options对象，可为null。传参为{params:{key:11}}
   * @param sucCb { Function } -必选 成功回调
   * @param errorCb { Function } -可选 失败回调
   * @param isLoading { Boolean } -可选 是否显示加载状态
   * @param isLogin { Boolean } -可选 是否登陆信息（
   */
  post: function (url, body, options, sucCb, errorCb, isLoading = true, isLogin) {
    if (!url) {
      // console.log('接口url不能为空！');
      return false;
    }

    //遮罩层
    if (isLoading) {
      var loadingInstance = Loading.service();
    }
    axios.post(url, body, options).then((response) => {
      // 响应成功回调
      if (response.data.errorCode) {
        MessageBox({
          title: '系统提示',
          message: '错误码: ' + response.data.errorCode + '<br/>' + '错误信息: ' + response.data.errorMessage,
          confirmButtonText: '确定',
          closeOnPressEscape: false,
          showCancelButton: false,
          dangerouslyUseHTMLString: true
        }).then(() => {
          if (response.data.errorCode == "system_login_noLogin") {
            store.dispatch('LogOut');
            location.reload();
          }
        }).catch(() => {

        });
      } else {
        sucCb(response.data);
      }
      if (isLoading) {
        loadingInstance.close();
      }

    }, (response) => {
      // 响应错误回调
      errorCb(response);
      if (isLoading) {
        loadingInstance.close();
      }
    })
  },

};
