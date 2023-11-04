<template>
	<div>
		<h1>adsfadsaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa</h1>
		<input type="button" value="点我" @click="handleLogin" />
	</div>
</template>

<script>
import {login} from "@/api/user";
import {setToken,getToken} from "@/utils/auth";

export default {
	methods: {
		// h() {
		// 	axios
		// 		.post("api/login", {
		// 			username: "xyh",
		// 			password: "123456",
		// 		})
		// 		.then((res) => {
		// 			console.log(res.data);
		// 		})
		// 		.catch((error) => {
		// 			console.log(error.response);
		// 		});
		// },

		//登录方法
		handleLogin() {
			//调用登录后端接口
			login({
				username: "xyh",
				password: "123456",
			}).then((result) => {
				console.log(result);
				alert(result.data)
				if (result.data.code == 1) {
					setToken(result.data.data);
					console.log("login success");
					alert(getToken())
					this.$router.push("/");
				} else {
					this.$message.error(result.data.msg);
				}
			});
		},
	},
};
</script>

<style></style>
