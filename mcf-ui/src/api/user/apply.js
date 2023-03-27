import request from '@/utils/request'

// 查询好友申请列表
export function listApply(query) {
  return request({
    url: '/user/apply/list',
    method: 'get',
    params: query
  })
}

// 查询好友申请详细
export function getApply(id) {
  return request({
    url: '/user/apply/' + id,
    method: 'get'
  })
}

// 新增好友申请
export function addApply(data) {
  return request({
    url: '/user/apply',
    method: 'post',
    data: data
  })
}

// 修改好友申请
export function updateApply(data) {
  return request({
    url: '/user/apply',
    method: 'put',
    data: data
  })
}

// 删除好友申请
export function delApply(id) {
  return request({
    url: '/user/apply/' + id,
    method: 'delete'
  })
}

// 导出好友申请
export function exportApply(query) {
  return request({
    url: '/user/apply/export',
    method: 'get',
    params: query
  })
}