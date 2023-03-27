import axios from 'axios'
import { Loading, Message, MessageBox } from 'element-ui'
import router from '@/router'
axios.defaults.baseURL = 'http://localhost:8081'
axios.defaults.timeout = 40000
axios.defaults.withCredentials = true

// 拦截请求，添加请求头
axios.interceptors.request.use(config => {
  // console.log(config)
  if (!info.whiteList.includes(config.url)) {
    const token = window.sessionStorage.getItem('token')
    if (!token || token ==='undefined') {
      MessageBox.alert('登录信息已过期，请重新登录', '登录过期', {
        confirmButtonText: '跳转到登录页',
        callback: action => { router.push('/') }
      })
      return
    }
    config.headers.Authorization = token
  }
  // 在最后必须 return config
  return config
})

axios.interceptors.response.use(config => config,
  err => {
    // console.dir(err)
    if (err.response.status && err.response.status === 404) {
      Message.error('无法请求到' + err.response.config.baseURL + err.response.data.path)
    }
  }
)

const loadMsgGet = {
  lock: true,
  text: '数据加载中',
  spinner: 'el-icon-loading',
  background: 'rgba(255, 255, 255, 0.7)'
}

const loadMsgPut = {
  lock: true,
  text: '数据提交中',
  spinner: 'el-icon-loading',
  background: 'rgba(255, 255, 255, 0.7)'
}

const callbackhandler = (promise, callback, loading) => {
  promise.then(
    response => {
      // console.log(response)
      if (response.data.status === 200) {
        Message.success(response.data.message)
        callback(response.data)
      } else {
        Message.error(response.data.message)
      }
    }
  ).catch(response => {
    // console.log(response)
    Message.error('服务器发生错误，正在联系程序员小哥哥排查中.....')
  }).finally(() => {
    loading.close()
  })
}

export default {
  get (url, callback, params = {}) {
    const loading = Loading.service(loadMsgGet)
    callbackhandler(axios.get(url, { params: params }), callback, loading)
  },

  post (url, callback, data = {}) {
    const loading = Loading.service(loadMsgPut)
    setTimeout(() => {
      loading.close()
    }, 10000)
    callbackhandler(axios.post(url, data), callback, loading)
  },

  put (url, callback, data = {}) {
    const loading = Loading.service(loadMsgPut)
    setTimeout(() => {
      loading.close()
    }, 10000)
    callbackhandler(axios.put(url, data), callback, loading)
  },

  del (url, callback, params = {}) {
    MessageBox.confirm('确定要删除数据吗?', '删除操作', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    }).then(() => {
      const loading = Loading.service(loadMsgPut)
      setTimeout(() => {
        loading.close()
      }, 10000)
      callbackhandler(axios.delete(url, { params: params }), callback, loading)
    }).catch(err => err)
  },
  // 讲原生的axios 暴露 ，自定义使用
  http: axios,
  downLoadExcelData (_this, url, data) {
    axios.get(url, {
      headers: {
        'Content-Type': 'multipart/form-data' // hearder 很重要，Content-Type 要写对
      },
      params: {
        ...data
      },
      responseType: 'arraybuffer' // 一定要写
    }).then(response => {
      console.log(response)
      const blob = new Blob([response.data], {
        type: 'application/octet-stream' // word文档为msword,pdf文档为pdf
      })
      const objectUrl = URL.createObjectURL(blob)
      const link = document.createElement('a')
      link.href = objectUrl
      link.setAttribute('download', 'data.xlsx')
      document.body.appendChild(link)
      link.click()
    }).catch(error => {
      console.log(error)
    })
  }
}

const request=axios.create({
  baseURL: 'http://localhost:8081/app', // api的base_url
  timeout: 60000, // request timeout
  headers: {
   'Content-Type': 'application/json;charset=utf-8'
  }
});


let requestingCount = 0;
let loading  = null

const handleRequestLoading = () => {
  if (!requestingCount)  {
    loading = Loading.service({
      lock: true,
      text: 'Loading',
      spinner: 'el-icon-loading',
      background: 'rgba(0, 0, 0, 0.7)'
    });
  }
  requestingCount++
}
const handleResponseLoading = () => {
  requestingCount--
  if (!requestingCount)  {
    if (loading != null) loading.close();
  }
}

request.interceptors.request.use(function (config) {
  const token = window.sessionStorage.getItem('token')
  config.headers.Authorization = token
  handleRequestLoading()
  return config;
}, function (error) {
  handleResponseLoading()
  return Promise.reject(error);
});


request.interceptors.response.use(function (response) {
  handleResponseLoading()
  if(response.data){
    response=response.data
  }
  if (response.code != 200) {
    Message.error(response.msg)
  }
  return response
}, function (error) {
  handleResponseLoading()
  let response=error.response
  if(!response){
    Message.error("网络错误")
  }
  return Promise.reject(error)
});

export {request}