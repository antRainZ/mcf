import {request} from '@/utils/axiosUtil'

export default {
  login(data) {
    return request({
      url: '/login',
      method: 'post',
      data: data
    })
  },
  register(data) {
    return request({
      url: '/register',
      method: 'post',
      data: data
    })
  },
  retrieve(data) {
    return request({
      url: '/retrieve',
      method: 'post',
      data: data
    })
  },
  getCode (value) {
    return request({
      url: `/code?email=${value}`,
      method: 'get'
    })
  },
  getInfo () {
    return request({
      url: '/user/info',
      method: 'get'
    })
  },
  update (data) {
    return request({
      url: '/user/updateInfo',
      method: 'put',
      data: data
    })    
  },
  updateAvatar (data) {
    return request({
      url: '/user/avatar',
      method: 'put',
      data: data
    })    
  },
  updatePwd (data) {
    return request({
      url: '/user/updatePwd',
      method: 'post',
      data: data
    })    
  },
  getList (query) {
    return request({
      url: '/user/list',
      method: 'get',
      params: query
    })
  },
  getSmartList (query) {
    return request({
      url: '/user/smart/list',
      method: 'get',
      params: query
    })
  },
  checkName (value) {
    return request({
      url: `/user/check/username?value=${value}`,
      method: 'get'
    })
  },
  checkEmail (value) {
    return request({
      url: `/user/check/email?value=${value}`,
      method: 'get'
    })
  },
  
}