import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "HomePage",
      component: () => import("@/views/HomePage.vue"),
      children: [
        {
          path: "user",
          name: "user",
          component: () => import("@/views/User.vue"),
        },
        {
          path: "article",
          name: "article",
          component: () => import("@/views/Post.vue"),
          // component: () => import("@/views/component/articlePageView.vue"),
        },
        {
          path: "like",
          name: "like",
          component: () => import("@/views/like.vue"),
        },
        {
          path: "/post/:postId/",
          name: "postDetail",
          component: () => import("@/views/PostDetail.vue"),
        },
        {
          path: "login",
          name: "login",
          component: () => import("@/views/login/loginView.vue"),
        },

        {
          path: "loginLog",
          name: "loginLog",
          component: () => import("@/views/LoginLog.vue"),
        },

        {
          path: "dataTracer",
          name: "dataTracer",
          component: () => import("@/views/DataTracer.vue"),
        },
      ],
    },
  ],
});

export default router;
