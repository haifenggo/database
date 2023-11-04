import request from "../utils/request";

//查询所有文章
export function getArticleList(params) {
	return request({
		'url': `/article/page`,
		method: "get",
		params,
	});
}
//查询自己已收藏文章
export function getLikeArticleList(params) {
	return request({
		'url': `/like/page`,
		method: "get",
		params,
	});
}
//查询自己已发表的文章
export function getPostedArticleList() {
	return request({
		url: `/article/posted`,
		method: "get",
	});
}
//根据postId查询文章
export function queryArticle(postId) {
	return request({
		url: `/article/${postId}`,
		method: "get",
	});
}
//根据postId查询文章是否已赞
export function queryLikeOrNot(postId) {
	return request({
		url: `/like?postId=${postId}`,
		method: "get",
		// postId:postId
	});
}
//修改文章
export function updateArticle(params) {
	return request({
		url: `/article`,
		method: "put",
		'data': params,
	});
}
//添加文章
export function addArticle(params) {
	return request({
		url: `/article`,
		method: "post",
		data: params,
	});
}
//点赞
export function addLike(postId) {
	return request({
		url: `/like/${postId}`,
		method: "post",
	});
}
//取消赞
export function deleteLike(postId) {
	return request({
		url: `/like/${postId}`,
		method: "delete",
	});
}
//根据postIds删除文章
export function deleteArticleByPostId(ids){
	return request({
		url:`/article`,
		method:"delete",
		params:{ids:ids}
	})
}