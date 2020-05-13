<template>
  <div class="nav-menu">
    <span class="nav-tit">后台系统</span>
    <div class="right-menu">
      <el-button type="text" @click="logOut()">注销</el-button>
      <el-dropdown class="avatar-container right-menu-item" trigger="click">
        <span class="avatar-btn">{{userName}}<img class="ml5"
                                                  src="../../../assets/images/arrow-down1.png"></span>
        <el-dropdown-menu slot="dropdown">
          <router-link to="/ForgetPassword">
            <el-dropdown-item divided>
              修改密码
            </el-dropdown-item>
          </router-link>
          <el-dropdown-item divided>
            <span @click="logOut" style="display:block;">注销</span>
          </el-dropdown-item>
        </el-dropdown-menu>
      </el-dropdown>
    </div>
  </div>
</template>

<script>
    export default {
        data() {
            return {
                userInfo: {},
                userName: '1',
                userId: '',
            }
        },
        created() {
            let self = this;
            self.getUserName();
            self.getUserId();
        },

        methods: {
            logOut() {
                console.info("注销")
                let self = this;
                self.$http.get(self.api.loginOut, {
                    params: {}
                }, function (response) {
                    if (response.code == '0') {
                        self.$message({
                            type: 'success',
                            message: '注销成功，页面即将跳转至登录页',
                            duration: 1000,
                            onClose: function () {
                                location.reload();
                            }
                        });
                    } else {
                        self.$message({
                            type: 'error',
                            message: '注销失败',
                            duration: 1000
                        });
                    }

                }, function (response) {
                })
            },
            getUserName() {
                let self = this;
                self.$http.get(self.api.getUserName, {}, function (response) {
                        if (response.code == '0') {
                            self.userName = response.content;
                        } else {
                            MessageBox({
                                title: '系统提示',
                                message: '错误码: ' + response.code + '<br/>' + '错误信息: ' + response.msg,
                                confirmButtonText: '确定',
                                closeOnPressEscape: false,
                                showCancelButton: false,
                                dangerouslyUseHTMLString: true
                            })
                        }
                    }, function (response) {
                        //失败回调
                        self.$message({
                            type: 'warning',
                            message: '请求异常',
                            duration: 1000
                        });
                    }
                )

            },
            getUserId() {
                let self = this;
                self.$http.get(self.api.getUserId, {}, function (response) {
                        if (response.code == '0') {
                            self.userId = response.content;
                        } else {
                            MessageBox({
                                title: '系统提示',
                                message: '错误码: ' + response.code + '<br/>' + '错误信息: ' + response.msg,
                                confirmButtonText: '确定',
                                closeOnPressEscape: false,
                                showCancelButton: false,
                                dangerouslyUseHTMLString: true
                            })
                        }
                    }, function (response) {
                        //失败回调
                        self.$message({
                            type: 'warning',
                            message: '请求异常',
                            duration: 1000
                        });
                    }
                )

            }

        }
    }
</script>

<style rel="stylesheet/scss" lang="scss">
  .nav-menu {
    background: #EDEDED;
    border-bottom: 1px solid #D9DEE4;
    height: 55px;
    width: 100%;
  }

  .nav-tit {
    line-height: 48px;
    font-size: 20px;
    font-weight: bold;
    padding-left: 20px;
    color: #2A3F54;
  }

  .right-menu {
    float: right;
    height: 100%;
    &:focus {
      outline: none;
    }
    .right-menu-item {
      display: inline-block;
      margin: 0 8px;
    }
    .international {
      vertical-align: top;
    }
    .theme-switch {
      vertical-align: 15px;
    }
    .avatar-container {
      height: 50px;
      margin-right: 30px;
      .avatar-btn {
        display: inline-block;
        line-height: 55px;
        cursor: pointer;
      }
      .avatar-wrapper {
        cursor: pointer;
        margin-top: 5px;
        position: relative;
        .user-avatar {
          width: 40px;
          height: 40px;
          border-radius: 10px;
        }
        .el-icon-caret-bottom {
          position: absolute;
          right: -20px;
          top: 25px;
          font-size: 12px;
        }
      }
    }
  }
</style>
