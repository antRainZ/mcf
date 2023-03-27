import {request} from '@/utils/axiosUtil'

export default {
  applyFriend(data) {
    return request({
      url: '/apply',
      method: 'post',
      data: data
    })
  },
  accept(id) {
    return request({
      url: `/apply/accept/${id}`,
      method: 'put'
    })
  },
  reject(id) {
    return request({
      url: `/apply/reject/${id}`,
      method: 'put'
    })
  },
  getMyFriend() {
    return request({
      url: '/apply/my/friend',
      method: 'get'
    })
  },
  getApplyList() {
    return request({
      url: '/apply/my/list',
      method: 'get'
    })
  },
  del(id) {
    return request({
      url: `/apply/${id}`,
      method: 'delete',
    })
  },
}