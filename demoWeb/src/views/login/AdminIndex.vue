<template>
    <div class="login-container">
        <div class="login-des">
            <img src="../../assets/images/logo-my.png"/>
        </div>
        <div class="title-container">
            <h3 class="title"></h3>
        </div>
        <div class="login-main">

            <el-form class="login-form" autoComplete="on" :model="loginForm" :rules="loginRules" ref="loginForm"
                     label-position="left">
                <el-form-item prop="loginId" class="mt20">
					<span class="user-icon">
						<img src="../../assets/images/user.png"/>
					</span>
                    <input type="text" class="login-input" v-model="loginForm.loginId" autoComplete="on"
                           placeholder="请输入用户名"/>
                </el-form-item>

                <el-form-item prop="password" class="mt30">
					<span class="user-icon">
						<img src="../../assets/images/psw.png"/>
					</span>
                    <input class="login-input" name="password" :type="passwordType"
                           v-model="loginForm.password" placeholder="请输入密码"/>
                    <span class="show-pwd" @click="showPwd">
						<img v-if="showPsw" src="../../assets/images/eye-open.png"/>
						<img v-else src="../../assets/images/eye-close.png"/>
					</span>
                </el-form-item>
                <el-form-item>
                    <el-button class="login-btn" @click="handleLogin">登&nbsp;&nbsp;&nbsp;录</el-button>
                    <span class="cursor" @click="routerToForgetPasswordView()">忘记密码?</span>
                </el-form-item>
            </el-form>
        </div>

    </div>
</template>

<script>
    import axios from 'axios';
    import {Loading, MessageBox} from 'element-ui';
    import router from '@/router'

    export default {
        name: 'login',
        data() {
            return {
                loginForm: {
                    loginId: '',
                    password: ''
                },
                loginRules: {
                    loginId: [{
                        required: true,
                        trigger: 'blur',
                        message: '请输入用户名',
                    }],
                    password: [{
                        required: true,
                        trigger: 'blur',
                        message: '请输入密码',
                    }]
                },
                passwordType: 'password',
                loading: false,
                showPsw: false,
            }
        },
        methods: {
            showPwd() {
                this.showPsw = !this.showPsw;
                if (this.passwordType === 'password') {
                    this.passwordType = ''
                } else {
                    this.passwordType = 'password'
                }
            },
            handleLogin() {
                console.info("click");
                let self = this
                this.$refs.loginForm.validate(valid => {
                        if (valid) {
                            self.$http.get(self.api.adminLogin, {
                                params: {
                                    userName: self.loginForm.loginId,
                                    password: self.loginForm.password
                                }
                            }, function (response) {
                                if (response.code == '0') {
                                    let content = response.content;
                                    console.log(content)
                                    console.log(content == true);
                                    if (content == true) {
                                        router.push({
                                            path: '/'
                                        })
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
                                }

                            }, function (response) {
                                //失败回调
                                self.$message({
                                    type: 'warning',
                                    message: '请求异常',
                                    duration: 1000
                                });
                            })

                        } else {
                            console.log('error submit!!')
                            return false
                        }
                    }
                )
            },
            routerToForgetPasswordView() {
                /**页面跳转 */
                window.open("#/ForgetPassword", '_self');
            },

        },
        created() {

        }
        ,
    }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
    .el-input input {
        padding: 0 51px;
    }

    $bg: #f7f7f7;
    $dark_gray: #467190;
    $light_gray: #094e80;
    .title-container {
        position: absolute;
        top: 35%;
        left: 26%;
        width: 300px;
        color: #fff;
        text-align: center;
        font-weight: bold;
        .title {
            font-size: 36px;
            line-height: 72px;
            font-weight: bold;
        }
        .title2 {
            font-size: 32px;
            line-height: 64px;
            font-weight: bold;
        }
    }

    .login-container {
        position: fixed;
        height: 100%;
        width: 100%;
        //background-image: radial-gradient(#44a9cc, #051c38);
        background: #006999;
        /*background-image: url(../../assets/images/bj1.png);
            background-repeat: repeat;
            background-size: contain;*/
        .login-des {
            img {
                /*width: 500px;
                        position: absolute;
                        top: 35%;
                        left: 22%;*/
                width: 200px;
                position: absolute;
                top: 20px;
                left: 26px;
            }
        }
        .login-main {
            position: absolute;
            left: 54%;
            right: 0;
            top: 24%;
            width: 360px;
        }
        .login-form {
            width: 360px;
            padding: 40px;
            margin-top: 30px;
            background: rgba(242, 248, 255, 0.2);
            -webkit-box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.1);
            box-shadow: 5px 5px 10px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
        }
        .login-input {
            -webkit-appearance: none;
            background-color: #fff;
            background-image: none;
            border-radius: 4px;
            border: 1px solid #dcdfe6;
            -webkit-box-sizing: border-box;
            box-sizing: border-box;
            color: #606266;
            display: inline-block;
            font-size: inherit;
            height: 40px;
            line-height: 40px;
            outline: 0;
            padding: 0 50px;
            width: 100%;
        }

        .show-pwd {
            position: absolute;
            right: 10px;
            top: 3px;
            font-size: 16px;
            color: $dark_gray;
            cursor: pointer;
            user-select: none;
            img {
                width: 20px;
                height: 20px;
            }
        }
        .user-icon {
            position: absolute;
            left: 10px;
            top: 7px;
            z-index: 10;
            border-right: solid 1px #ddd;
            height: 27px;
            padding-right: 7px;
            img {
                position: relative;
                bottom: 7px;
                width: 18px;
                height: 20px;
            }
        }
        .forgetPsw {
            color: #7c7a7a;
            float: right;
            margin-right: 5px;
            cursor: pointer;
        }
        .login-btn {
            width: 100%;
            margin-top: 30px;
            padding: 13px 20px;
            background-color: #1dca29;
            border-color: #1dca29;
            color: #fff;
            border-radius: 5px;
        }
    }

    .verify-ipt {
        border-radius: 4px;
        border: 1px solid #dcdfe6;
        box-sizing: border-box;
        color: #606266;
        height: 38px;
        font-size: 12px;
        line-height: 1;
        outline: 0;
        padding: 0 15px;
        width: 250px;
    }

    .verify-btn {
        font-size: 11px;
        border: none;
        background: #eeeeee;
        border-radius: 2px;
        height: 39px;
        width: 200px;
        margin-left: 5px;
        color: #333;
        cursor: pointer;
    }

    .cursor {
        cursor: pointer;
    }

    .verify-img {
        height: 39px;
        width: 160px;
        margin-left: 5px;
        cursor: pointer;
    }
</style>
