<template>
	<div>
		<div>
			<h2>
				{{ title }}
				<hr />
				<el-button type="warning" icon="el-icon-star-off" circle></el-button>
				<el-button type="danger" icon="el-icon-delete" circle></el-button>
				<el-button type="text" @click="handleLikeOrNot()">{{ status === 0 ? "点赞" : "取消赞" }}</el-button>
			</h2>
		</div>

		<br />
		<div>
			<h6>{{ content }}</h6>
		</div>
	</div>
</template>

<script>
import { queryArticle, queryLikeOrNot, addLike, deleteLike } from "@/api/article";

export default {
	data() {
		return {
			t: 0,
			status: 1,
			title: "",
			content: "",
		};
	},

	created() {
		this.t = this.$route.query.postId;

		queryArticle(this.t).then((res) => {
			if (res.data.code === 1) {
				this.title = res.data.data.title;
				this.content = res.data.data.content;
			}
		});
		queryLikeOrNot(this.t).then((res) => {
			if (res.data.code === 1) {
				this.status = res.data.data;
				alert(this.status);
			}
		});
	},

	methods: {
		handleLikeOrNot() {
			if (this.status === 1) {
				deleteLike(this.t).then((res) => {
					if (res.data.code === 1) {
						alert("取消赞成功！");
						this.status = 0;
					}
				});
			} else {
				addLike(this.t).then((res) => {
					if (res.data.code === 1) {
						alert("点赞成功！");
						this.status = 1;
					}
				});
			}
		},
	},
};
</script>

<style></style>
