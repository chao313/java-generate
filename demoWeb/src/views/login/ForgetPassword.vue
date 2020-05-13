<template>
	<div class="app-form ml60 mt40">
		<el-form :model="addInfo" :rules="rules" ref="addInfo" label-width="120px" size="mini" :inline-message="true">
			<el-form-item label="旧密码：" prop="oldPassword">
				<el-input v-model="addInfo.oldPassword" type="password"></el-input>
			</el-form-item>
			<el-form-item label="新密码" prop="newPassword">
				<el-input v-model="addInfo.newPassword"  type="password"></el-input><span class="ml10" style="color: #f45151;">大小写字母和数字6-15位</span>
			</el-form-item>
			<el-form-item label="确认新密码：" prop="newPswConfirm">
				<el-input v-model="addInfo.newPswConfirm" type="password"></el-input>
			</el-form-item>
		</el-form>
		<div class="mt40 ml40">
			<el-button type="primary" @click="submitForm('addInfo')">确定</el-button>
			<el-button @click="resetForm('addInfo')">重置</el-button>
		</div>
	</div>
</template>
<script>
	export default {
		data() {
			var validateCommitPass = (rule, value, callback) => {
				if(value === '') {
					callback(new Error('请再次输入密码'));
				} else if(value !== this.addInfo.newPassword) {
					callback(new Error('两次输入密码不一致'));
				} else {
					callback();
				}
			};
			var validatePassword = (rule, value, callback) => {
				const reg = /^[a-zA-Z0-9]{6,15}$/;
				if(value === '') {
					callback(new Error('请输入新密码'));
				} else if(reg.test(this.addInfo.newPassword)) {
					callback();

				} else {
					callback(new Error('密码格式错误'));
				}
			};

			return {
				addInfo: {
					newPassword: '',
					newPswConfirm: '',
					oldPassword:''
				},
				rules: {
					oldPassword: [{
						required: true,
						trigger: 'blur',
						message: '请输入旧密码',
					}],
					newPassword: [{
						required: true,
						trigger: 'blur',
						validator: validatePassword,
					}],
					newPswConfirm: [{
						validator: validateCommitPass,
						trigger: 'blur',
						required: true,
					}],
				}
			};
		},
		watch: {

		},
		methods: {
			submitForm(formName) {
				let self = this;
				this.$refs[formName].validate((valid) => {
					if(valid) {
						var params = new FormData();
						params.append('newPassword', self.addInfo.newPassword);
						params.append('password', self.addInfo.oldPassword);
						self.$http.post(self.api.updatePassword, params, {
								headers: {
									"Content-Type": "multipart/form-data"
								},
						}, function(response) {
							if(response.result) {
								self.logOut()
							} else {
								self.$message({
									type: 'error',
									message: '修改失败',
									duration: 2000
								});
							}
						}, function(response) {
							//失败回调
						})

					}
				});
			},
			logOut() {
				let self = this;
				self.$http.get(self.api.userLogoff, {
					params: {}
				}, function(response) {
					if(response.result) {
						self.$store.dispatch('LogOut');
						self.$message({
							type: 'success',
							message: '注销成功，页面即将跳转至登录页',
							duration: 1000,
							onClose: function() {
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

				}, function(response) {})
			},
			 resetForm(formName) {
		        this.$refs[formName].resetFields();
		      }
		}
	}
</script>
<style rel="stylesheet/scss" scoped lang="scss">
.psw-Tip{
	margin-top: 20px;
	p{
		line-height: 24px;
		color: #888;
		margin-left: 10px;
	}
}
 .el-form-item .el-input {
    position: relative;
    font-size: 12px;
    display: inline-block;
    width: 320px;
}
</style>
