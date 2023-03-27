import {request} from '@/utils/axiosUtil'

export default {
  signIn() {
    return request({
      url: '/integral',
      method: 'post'
    })
  },
  getMyList(query) {
    return request({
      url: '/integral/my/list',
      method: 'get',
      params: query
    })
  },
  checksignIn() {
    return request({
      url: '/integral/check',
      method: 'get'
    })
  }
}