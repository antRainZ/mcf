import request from '@/utils/request'

// 查询帖子列表
export function listInvitation(query) {
  return request({
    url: '/community/invitation/list',
    method: 'get',
    params: query
  })
}

// 查询帖子详细
export function getInvitation(id) {
  return request({
    url: '/community/invitation/' + id,
    method: 'get'
  })
}

// 新增帖子
export function addInvitation(data) {
  return request({
    url: '/community/invitation',
    method: 'post',
    data: data
  })
}

// 修改帖子
export function updateInvitation(data) {
  return request({
    url: '/community/invitation',
    method: 'put',
    data: data
  })
}

// 删除帖子
export function delInvitation(id) {
  return request({
    url: '/community/invitation/' + id,
    method: 'delete'
  })
}

// 导出帖子
export function exportInvitation(query) {
  return request({
    url: '/community/invitation/export',
    method: 'get',
    params: query
  })
}