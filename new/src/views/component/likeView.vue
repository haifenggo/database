<template>
	<div class="big-container">
		<div class="tableContent">
			<el-table :data="records" stripe style="width: 100%">
				<el-table-column prop="postId" label="文章编号" width="300"> </el-table-column>
				<el-table-column prop="userId" label="作者编号" width="300"> </el-table-column>
				<el-table-column prop="title" label="标题" width="300"> </el-table-column>
				<el-table-column prop="createTime" label="创作时间" width="180"> </el-table-column>
				<el-table-column label="操作" width="180">
					<template slot-scope="scope">
						<el-button type="text" size="small" class="blueBug" @click="handleGetArticle(scope.row.postId)"> 查看 </el-button>
						<el-button type="text" size="small" class="blueBug" @click="handleDislikeArticle(scope.row.postId)"> 取消赞 </el-button>
					</template>
				</el-table-column>
			</el-table>
		</div>

		<hr />

		<el-pagination class="pageList" @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page" :page-sizes="[10, 20]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total"> </el-pagination>
	</div>
</template>

<script>
import { getLikeArticleList, deleteLike } from "@/api/article";

export default {
	created() {
		this.likePageQuery();
	},

	data() {
		return {
			page: 1,
			pageSize: 10,
			total: 0,
			records: [],
		};
	},

	methods: {
		likePageQuery() {
			getLikeArticleList({ page: this.page, pageSize: this.pageSize })
				.then((res) => {
					if (res.data.code === 1) {
						this.total = res.data.data.total;
						this.records = res.data.data.records;
					}
				})
				.catch((err) => {
					this.$message.error("请求出错了：" + err.message);
				});
		},
		handleGetArticle(postId) {
			alert(`${postId}`);

			//跳转到修改页面，通过地址栏传递参数
			this.$router.push({
				path: "/article/read",
				query: { postId },
			});
		},
		handleDislikeArticle(postId) {
			deleteLike(postId)
				.then((res) => {
					if (res.data.code === 1) {
						this.$message.success("取消赞成功！");
						this.likePageQuery();
					}
				})
				.catch((err) => {
					this.$message.error("请求出错了：" + err.message);
				});
		},
		//page发生变化时触发
		handleCurrentChange(page) {
			this.page = page;
			this.likePageQuery();
		},
	},
};
</script>

<style></style>
