<template>
	<div>
		<div>
			<el-input v-model="title" style="width: 20%" />
		</div>
		<br />
		<div>
			<el-input v-model="content" style="width: 20%" />
		</div>

		<br /><br /><br />
		<el-button type="primary" style="float: right" @click="handleFinish">完成</el-button>
	</div>
</template>

<script>
import { queryArticle, updateArticle, addArticle } from "@/api/article";

export default {
	data() {
		return {
			title: "",
			content: "",
			postId: 0,
		};
	},

	created() {
		//修改，页面回显使用
		alert(this.$route.query.postId);
		if (this.$route.query.postId) {
			queryArticle(this.$route.query.postId).then((res) => {
				if (res.data.code === 1) {
					this.title = res.data.data.title;
					this.content = res.data.data.content;
					this.postId = res.data.data.postId;
				}
			});
		}
	},

	methods: {
		handleFinish() {
			// alert(`"路由地址参数：${this.$route.query.postId}`);
			if (this.$route.query.postId) {
				updateArticle({
					postId: this.postId,
					title: this.title,
					content: this.content,
				}).then((res) => {
					if (res.data.code === 1) {
						alert("修改成功！");

						this.$router.push({
							path: "/article",
						});
					}
				});
			} else {
				if (this.title.trim() && this.content.trim()) {
					addArticle({
						title: this.title,
						content: this.content,
					}).then((res) => {
						if (res.data.code === 1) {
							alert("发表成功！");

							this.$router.push({
								path: "/article",
							});
						}
					});
				}
			}
		},
	},
};
</script>

<style></style>
