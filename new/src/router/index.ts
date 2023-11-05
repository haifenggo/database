import Vue from "vue";
import VueRouter, { RouteConfig } from "vue-router";

Vue.use(VueRouter);

const routes: Array<RouteConfig> = [
	{
		path: "/",
		redirect: "/login",
	},
	{
		path: "/login",
		component: () => import("@/../../../frontend/src/views/login/loginView.vue"),
	},
	{
		path: "/article",
		component: () => import("@/../../../frontend/src/views/main/articleView.vue"),
		redirect:'/article/page',
		children: [
			{
				path: "/article/page",
				component: () => import("@/../../../frontend/src/views/component/articlePageView.vue"),
			},
			{
				path: "/article/posted",
				component: () => import("@/../../../frontend/src/views/component/postedView.vue"),
			},
			{
				path: "/article/like",
				component: () => import("@/../../../frontend/src/views/component/likeView.vue"),
			},
			{
				path: "/article/read",
				component: () => import("@/../../../frontend/src/views/component/readView.vue"),
			},
			{
				path: "/article/updateOrAdd",
				component: () => import("@/../../../frontend/src/views/component/updateOrAddView.vue"),
			}
		],
	},
	{
		path: "/aaa",
		component: () => import("@/views/TmpView.vue"),
	}
];

const router = new VueRouter({
	routes,
});

export default router;
