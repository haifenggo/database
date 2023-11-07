import { postRequest, getRequest } from "@/api/axios";

export const likeApi = {
  getLikeList: (params) => {
    return postRequest("/like", params);
  },

  deleteLike: (id) => {
    return getRequest(`/like/delete/${id}`);
  },

  likePost: (id) => {
    return getRequest(`/like/post/${id}`);
  },
};
