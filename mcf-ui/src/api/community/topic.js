import request from '@/utils/request'

// 查询主题列表
export function listTopic(query) {
  return request({
    url: '/community/topic/list',
    method: 'get',
    params: query
  })
}

// 查询主题详细
export function getTopic(id) {
  return request({
    url: '/community/topic/' + id,
    method: 'get'
  })
}

// 新增主题
export function addTopic(data) {
  return request({
    url: '/community/topic',
    method: 'post',
    data: data
  })
}

// 修改主题
export function updateTopic(data) {
  return request({
    url: '/community/topic',
    method: 'put',
    data: data
  })
}

// 删除主题
export function delTopic(id) {
  return request({
    url: '/community/topic/' + id,
    method: 'delete'
  })
}

// 导出主题
export function exportTopic(query) {
  return request({
    url: '/community/topic/export',
    method: 'get',
    params: query
  })
}