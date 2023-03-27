import {request} from '@/utils/axiosUtil'

export default {
  getPostList(query) {
    return request({
      url: '/post/comment/list',
      method: 'get',
      params: query
    })
  },
  getMyList(query) {
    return request({
      url: '/post/comment/my/list',
      method: 'get',
      params: query
    })
  },
  add(data) {
    return request({
      url: '/post/comment',
      method: 'post',
      data: data
    })
  },
  del(id) {
    return request({
      url: `/post/comment/${id}`,
      method: 'delete',
    })
  },
}