export function readArrayBySession (key) {
  return JSON.parse(window.sessionStorage.getItem(key) || '[]')
}

export  function  writeArrayBySession (key, val) {
  window.sessionStorage.setItem(key, JSON.stringify(val))
}

export  function  readObjBySession (key) {
  return JSON.parse(window.sessionStorage.getItem(key) || '{}')
}

export  function  writeObjBySession (key, val) {
  window.sessionStorage.setItem(key, JSON.stringify(val))
}

export  function  readBySession (key) {
  return window.sessionStorage.getItem(key) || ''
}

export  function  writeBySession (key, val) {
  window.sessionStorage.setItem(key, val)
}
export  function  readArrayByLocal (key) {
  return JSON.parse(window.localStorage.getItem(key) || '[]')
}

export  function  writeArrayByLocal (key, val) {
  window.localStorage.setItem(key, JSON.stringify(val))
}

export  function  readObjByLocal (key) {
  return JSON.parse(window.localStorage.getItem(key) || '{}')
}

export  function writeObjByLocal (key, val) {
  window.localStorage.setItem(key, JSON.stringify(val))
}

export  function  readByLocal (key) {
  return window.localStorage.getItem(key) || ''
}

export  function writeByLocal (key, val) {
  window.localStorage.setItem(key, val)
}

export  function saveUser(user) {
  window.sessionStorage.setItem("token",user.token)
  window.sessionStorage.setItem("avatar",user.avatar)
  window.sessionStorage.setItem("nickname",user.nickname)
  window.sessionStorage.setItem("username",user.username)
  
}

export  function clearUser() {
  window.sessionStorage.removeItem("token")
  window.sessionStorage.removeItem("avatar")
  window.sessionStorage.removeItem("nickname")
}