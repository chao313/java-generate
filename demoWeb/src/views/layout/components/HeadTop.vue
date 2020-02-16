<template>
  <div class="nav-menu">
    <span class="nav-tit">后台系统</span>
    <div class="right-menu">
      <el-button type="text" @click="ReleaseResources()">释放资源并注销</el-button>
      <el-dropdown class="avatar-container right-menu-item" trigger="click">
        <span class="avatar-btn">{{userInfo.userName}}<img class="ml5"
                                                           src="../../../assets/images/arrow-down1.png"></span>
        <el-dropdown-menu slot="dropdown">
          <router-link to="/">
            <el-dropdown-item>
              首页
            </el-dropdown-item>
          </router-link>
          <router-link to="/accountMgt">
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
        userInfo: {}
      }
    },
    created() {
      let self = this;
      self.$store.dispatch('GetUserInfo').then(res => {
        self.userInfo = res.data;
      })
      window.onbeforeunload = function (e) {
        var e = window.event || e;
        self.DoLogout();
      }
    },

    methods: {
      logOut() {
        let self = this;
        self.$http.get(self.api.userLogoff, {
          params: {}
        }, function (response) {
          if (response.result) {
            self.$store.dispatch('LogOut');
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
      ReleaseResources() {
        this.logOut();
      },
      DoLogout() {
        let g_imosActivePlayer = document.all.h3c_IMOS_ActiveX;
        try {
          if (g_imosActivePlayer === undefined) {
            if (!g_imosActivePlayer) {
              //alert("未安装控件，请先安装后再使用本页面");
              return;
            }
            var flag = g_imosActivePlayer.IMOSAX_UnregOCX();
            if (0 != flag) {
              alert("IMOSAX_UnregOCX Error:" + flag);
              return;
            }
          } else {
            if (!g_imosActivePlayer) {
              alert("未安装控件，请先安装后再使用本页面");
              return;
            }
            var flag = g_imosActivePlayer.IMOSAX_UnregOCX();
            if (0 != flag) {
              alert("IMOSAX_UnregOCX Error:" + flag);
              return;
            }
          }
        } catch (e) {
          window.console && console.log("变量未声明，");
        }


      },
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
