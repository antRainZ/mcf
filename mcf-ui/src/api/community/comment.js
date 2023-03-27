import request from '@/utils/request'

// 查询帖子评论列表
export function listComment(query) {
  return request({
    url: '/community/comment/list',
    method: 'get',
    params: query
  })
}

// 查询帖子评论详细
export function getComment(id) {
  return request({
    url: '/community/comment/' + id,
    method: 'get'
  })
}

// 新增帖子评论
export function addComment(data) {
  return request({
    url: '/community/comment',
    method: 'post',
    data: data
  })
}

// 修改帖子评论
export function updateComment(data) {
  return request({
    url: '/community/comment',
    method: 'put',
    data: data
  })
}

// 删除帖子评论
export function delComment(id) {
  return request({
    url: '/community/comment/' + id,
    method: 'delete'
  })
}

// 导出帖子评论
export function exportComment(query) {
  return request({
    url: '/community/comment/export',
    method: 'get',
    params: query
  })
}