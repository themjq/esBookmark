import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/admin/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/users/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/logout',
    method: 'post'
  })
}


export function page(name,sex,begin,end,page,pageSize) {
  return request({
    url: '/admin/admin?name='+name+'&sex='+sex+'&begin='+begin+'&end='+end+'&page='+page+'&pageSize='+pageSize,
    method: 'get'
  })
}

export function add(admin) {
  return request({
    url: '/admin/admin',
    method: 'post',
    data: admin
  })
}

export function update(admin) {
  return request({
    url: '/admin/admin',
    method: 'put',
    data: admin
  })
}


export function deleteById(id) {
  return request({
    url: '/admin/admin/'+id,
    method: 'delete',
  })
}


export function selectById(id) {
  return request({
    url: '/admin/admin/'+id,
    method: 'get',
  })
}