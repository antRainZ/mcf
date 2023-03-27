import {request} from '@/utils/axiosUtil'

export default {
  getMyList(query) {
    return request({
      url: '/message/my/list',
      method: 'get',
      params: query
    })
  },
  getLatest(id) {
    return request({
      url: `/message/my/latest/${id}`,
      method: 'get'
    })
  },
  reset(id) {
    return request({
      url: `/message/my/reset/${id}`,
      method: 'get'
    })
  },
  add(data) {
    return request({
      url: '/message',
      method: 'post',
      data: data
    })
  }
}