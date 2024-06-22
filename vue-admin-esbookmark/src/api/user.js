import request from '@/utils/request'

export function page(name,sex,begin,end,page,pageSize) {
  return request({
    url: '/admin/user?name='+name+'&sex='+sex+'&begin='+begin+'&end='+end+'&page='+page+'&pageSize='+pageSize,
    method: 'get'
  })
}

export function add(user) {
  return request({
    url: '/admin/user',
    method: 'post',
    data: user
  })
}

export function update(user) {
  return request({
    url: '/admin/user',
    method: 'put',
    data: user
  })
}


export function deleteById(id) {
  return request({
    url: '/admin/user/'+id,
    method: 'delete',
  })
}


export function selectById(id) {
  return request({
    url: '/admin/user/'+id,
    method: 'get',
  })
}

