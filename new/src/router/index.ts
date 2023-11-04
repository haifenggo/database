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
		component: () => import("@/views/login/loginView.vue"),
	},
	{
		path: "/article",
		component: () => import("@/views/main/articleView.vue"),
		redirect:'/article/page',
		children: [
			{
				path: "/article/page",
				component: () => import("@/views/component/articlePageView.vue"),
			},
			{
				path: "/article/posted",
				component: () => import("@/views/component/postedView.vue"),
			},
			{
				path: "/article/like",
				component: () => import("@/views/component/likeView.vue"),
			},
			{
				path: "/article/read",
				component: () => import("@/views/component/readView.vue"),
			},
			{
				path: "/article/updateOrAdd",
				component: () => import("@/views/component/updateOrAddView.vue"),
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
