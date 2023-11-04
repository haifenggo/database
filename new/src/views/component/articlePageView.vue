<template>
	<div class="big-container">
		<div class="tableBar">
			<label style="margin-right: 10px">标题：</label>
			<el-input v-model="title" placeholder="请输入查找标题" style="width: 20%" />
			<el-button type="primary" style="margin-left: 25px" @click="pageQuery()">查询</el-button>
			<el-button type="primary" style="float: right" @click="handleAddArticle">+发表爽文</el-button>
		</div>
		<hr />

		<div class="tableContent">
			<el-table :data="records" stripe style="width: 100%">
				<el-table-column prop="postId" label="文章编号" width="300"> </el-table-column>
				<el-table-column prop="userId" label="作者编号" width="300"> </el-table-column>
				<el-table-column prop="title" label="标题" width="300"> </el-table-column>
				<el-table-column prop="createTime" label="创作时间" width="180"> </el-table-column>
				<el-table-column label="操作" width="180">
					<template slot-scope="scope">
						<el-button type="text" size="small" class="blueBug" @click="handleGetArticle(scope.row.postId)"> 查看 </el-button>
					</template>
				</el-table-column>
			</el-table>
		</div>

		<hr />

		<el-pagination class="pageList" @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="page" :page-sizes="[10, 20]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total"> </el-pagination>
	</div>
</template>

<script>
import { getArticleList } from "@/api/article";

//getArticleByPostId
export default {
	created() {
		this.pageQuery();
	},

	data() {
		return {
			title: "",
			page: 1,
			pageSize: 10,
			total: 0,
			records: [],
		};
	},

	methods: {
		pageQuery() {
			const params = { title: this.title, page: this.page, pageSize: this.pageSize };

			getArticleList(params)
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
		handleAddArticle() {
			//跳转到新增页面，通过地址栏传递参数
			this.$router.push({
				path: "/article/updateOrAdd",
			});
		},
		handleGetArticle(postId) {
			//跳转到修改页面，通过地址栏传递参数
			this.$router.push({
				path: "/article/read",
				query: { postId },
			});
		},
		//pageSize发生变化时触发
		handleSizeChange(pageSize) {
			this.pageSize = pageSize;
			this.pageQuery();
		},
		//page发生变化时触发
		handleCurrentChange(page) {
			this.page = page;
			this.pageQuery();
		},
	},
};
</script>

<style></style>
