<template>
	<div>
		<!-- 设置最外层容器高度为700px,在加上一个很细的边框 -->
		<el-container style="height: 900px; border: 1px solid #eee">
			<!-- 标题 -->
			<el-header style="font-size: 40px; background-color: rgb(238, 241, 246)">爽文分享</el-header>
			<!-- 主要内容 -->
			<el-container>
				<!-- 边栏选项 -->
					<el-aside width="200px" style="background-color: rgb(238, 241, 246)">
						<el-menu :default-openeds="['1', '2']">
							<el-submenu index="1">
								<template slot="title"><i class="el-icon-message"></i>爽文</template>							
								<el-menu-item index="1-1"><router-link to="/article/page">全部</router-link></el-menu-item>
								<el-menu-item index="1-1"><router-link to="/article/posted">已发表</router-link></el-menu-item>
								<el-menu-item index="1-1"><router-link to="/article/like">已收藏</router-link></el-menu-item>
							</el-submenu>
							<el-submenu index="2">
								<template slot="title"><i class="el-icon-menu"></i>日志</template>
								<el-menu-item index="1-1"><router-link to="/login">登录日志</router-link></el-menu-item>
								<el-menu-item index="1-1"><router-link to="/login">操作日志</router-link></el-menu-item>
							</el-submenu>
						</el-menu>
					</el-aside>
				<!-- 主要显示区域 -->
				<el-main>
					<router-view></router-view>
				</el-main>
			</el-container>
		</el-container>
	</div>
</template>

<script>
export default {};
</script>

<style>

.el-header,
.el-footer {
	background-color: #b3c0d1;
	color: #333;
	text-align: center;
	line-height: 60px;
}

.el-aside {
	background-color: #d3dce6;
	color: #333;
	text-align: center;
	line-height: 200px;
}

.el-main {
	background-color: #e9eef3;
	color: #333;
	text-align: center;
	line-height: 160px;
}

body > .el-container {
	margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
	line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
	line-height: 320px;
}
</style>