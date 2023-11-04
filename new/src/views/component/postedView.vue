<template>
	<div class="big-container">
		<hr />
		<el-button type="danger" @click="handleDeleteArticles">删除</el-button>

		<div class="tableContent">
			<el-table :data="records" stripe class="tableBox" @selection-change="handleSelectionChange">
				<el-table-column type="selection" width="25" />

				<el-table-column prop="postId" label="文章编号" width="400"> </el-table-column>
				<el-table-column prop="title" label="标题" width="400"> </el-table-column>
				<el-table-column prop="createTime" label="创作时间" width="180"> </el-table-column>

				<el-table-column label="操作" width="180">
					<template slot-scope="scope">
						<el-button type="text" size="small" class="blueBug" @click="handleGetArticle(scope.row.postId)"> 查看 </el-button>
						<el-button type="text" size="small" class="blueBug" @click="handleUpdateArticle(scope.row.postId)"> 修改 </el-button>
					</template>
				</el-table-column>
			</el-table>
		</div>
		<br />
		<hr />
		<el-pagination class="pageList" @current-change="handleCurrentChange" :current-page="page" layout="total,  prev, pager, next" :total="total"> </el-pagination>
	</div>
</template>

<script>
import { getPostedArticleList, deleteArticleByPostId } from "@/api/article";
export default {
	created() {
		this.postedPageQuery();
	},

	data() {
		return {
			title: "",
			page: 1,
			pageSize: 10,
			total: 0,
			records: [],
			multipleSelection: [],
		};
	},

	methods: {
		postedPageQuery() {
			getPostedArticleList()
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

		//page发生变化时触发
		handleCurrentChange(page) {
			this.page = page;
			this.pageQuery();
		},
		handleSelectionChange(val) {
			this.multipleSelection = val;
		},
		handleGetArticle(postId) {
			//跳转到查看readView页面，通过地址栏传递参数
			this.$router.push({
				path: "/article/read",
				query: { postId },
			});
		},
		handleUpdateArticle(postId) {
			this.$router.push({
				path: "/article/updateOrAdd",
				query: { postId },
			});
		},
		handleDeleteArticles() {
			//弹出确认提示框
			this.$confirm("真的要删除选择文章?", "提示", {
				confirmButtonText: "确定",
				cancelButtonText: "取消",
				type: "warning",
			}).then(() => {
				let arr = [];
				this.multipleSelection.forEach((element) => {
					arr.push(element.postId);
				});
				const ids = arr.join(",");
				alert(ids);

				deleteArticleByPostId(ids).then((res) => {

					if (res.data.code === 1) {
						this.$message.success("删除成功！");
						this.postedPageQuery();
					}
					else {
						this.$message.error(res.data.msg)
					}
				});
			});
		},
	},
};
</script>

<style></style>
