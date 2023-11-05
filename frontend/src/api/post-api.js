import { postRequest, getRequest } from '@/api/axios';

export const postApi = {
  getPostList: (params) => {
    return postRequest('/post', params);
  },
  getPostDetail:(postId)=>{
    return getRequest(`/post/${postId}`);
  },
  deleteByPostId: (postId) => {
    return getRequest(`/post/delete/${postId}`);
  },
  updatePost: (params) => {
    return postRequest('/post/update', params);
  },
  insertPost: (params) => {
    return postRequest('/post/insert', params);
  },

  getLikeArticleList: (params) => {
    return getRequest('/like/page', params);
  },
  getPostedArticleList: () => {
    return getRequest('/article/posted');
  },
  queryArticle: (postId) => {
    return getRequest(`/article/${postId}`);
  },
  queryLikeOrNot: (postId) => {
    return getRequest(`/like?postId=${postId}`);
  },

  addArticle: (params) => {
    return postRequest('/article', params);
  },
  addLike: (postId) => {
    return postRequest(`/like/${postId}`);
  },
  deleteLike: (postId) => {
    return postRequest(`/like/${postId}`, null, 'DELETE');
  },

};
