import {request} from '@/utils/axiosUtil'

export default {
  indexRefine() {
    return request({
      url: '/article/refine',
      method: 'get'
    })
  },
  latest(query) {
    return request({
      url: '/article/list',
      method: 'get',
      params: query
    })
  },
  getById(id) {
    return request({
      url: `/article/info/${id}`,
      method: 'get'
    })
  },
  getCommentList(query) {
    return request({
      url: '/article/comment/list',
      method: 'get',
      params: query
    })
  },
  addComment(data) {
    return request({
      url: '/article/comment',
      method: 'post',
      data: data
    })
  },
}