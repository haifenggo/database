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
          path: "/post/:postId/",
          name: "postDetail",
          component: () => import("@/views/PostDetail.vue"),
        },
        {
          path: "login",
          name: "login",
          component: () => import("@/views/login/loginView.vue"),
        },
      ],
    },
  ],
});

export default router;
