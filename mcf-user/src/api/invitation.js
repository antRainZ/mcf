import {request} from '@/utils/axiosUtil'

export default {
  indexRefine() {
    return request({
      url: '/invitation/index/refine',
      method: 'get'
    })
  },
  indexLatest() {
    return request({
      url: '/invitation/index/latest',
      method: 'get'
    })
  },
  topicRefine(query) {
    return request({
      url: '/invitation/topic/refine',
      method: 'get',
      params: query
    })
  },
  topicLatest(query) {
    return request({
      url: '/invitation/topic/latest',
      method: 'get',
      params: query
    })
  },
  getById(id) {
    return request({
      url: `/invitation/info/${id}`,
      method: 'get'
    })
  },
  topicList(query) {
    return request({
      url: '/invitation/topic/list',
      method: 'get',
      params: query
    })
  },
  myList(query) {
    return request({
      url: '/invitation/my/list',
      method: 'get',
      params: query
    })
  },
  add(data) {
    return request({
      url: '/invitation',
      method: 'post',
      data: data
    })
  },
  update(data) {
    return request({
      url: '/invitation',
      method: 'put',
      data: data
    })
  },
  del(id) {
    return request({
      url: `/invitation/${id}`,
      method: 'delete',
    })
  },
}