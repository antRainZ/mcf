import {request} from '@/utils/axiosUtil'

export default {
  indexTopic() {
    return request({
      url: '/topic/refine',
      method: 'get'
    })
  },
  myTopic() {
    return request({
      url: '/topic/my',
      method: 'get'
    })
  },
  getList(query) {
    return request({
      url: '/topic/list',
      method: 'get',
      params: query
    })
  },
  getInfo(id) {
    return request({
      url: `/topic/info/${id}`,
      method: 'get'
    })
  },
  add (data) {
    return request({
      url: '/topic',
      method: 'post',
      data: data
    })    
  }
}