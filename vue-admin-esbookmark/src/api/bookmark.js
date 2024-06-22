import request from '@/utils/request'

export function page(title,status,typeId,begin,end,page,pageSize) {
  return request({
    url: '/admin/bookmark?title='+title+'&status='+status+'&typeId='+typeId+'&begin='+begin+'&end='+end+'&page='+page+'&pageSize='+pageSize,
    method: 'get'
  })
}

export function add(bookmark) {
  return request({
    url: '/admin/bookmark',
    method: 'post',
    data: bookmark
  })
}

export function update(bookmark) {
  return request({
    url: '/admin/bookmark',
    method: 'put',
    data: bookmark
  })
}


export function deleteById(id) {
  return request({
    url: '/admin/bookmark/'+id,
    method: 'delete',
  })
}


export function selectById(id) {
  return request({
    url: '/admin/bookmark/'+id,
    method: 'get',
  })
}

