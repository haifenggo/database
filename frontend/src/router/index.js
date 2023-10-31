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
          component: () => import("@/views/Article.vue"),
        },
      ],
    },
  ],
});

export default router;
