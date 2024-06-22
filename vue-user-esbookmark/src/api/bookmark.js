import request from '@/utils/request'

export function page(page,pageSize,status) {
  return request({
    url: '/user/bookmark?page='+page+'&pageSize='+pageSize+'&status='+status,
    method: 'get'
  })
}


export function page2(title,status,typeId,begin,end,page,pageSize) {
  return request({
    url: '/user/bookmark/my?title='+title+'&status='+status+'&typeId='+typeId+'&begin='+begin+'&end='+end+'&page='+page+'&pageSize='+pageSize,
    method: 'get'
  })
}
export function add(bookmark) {
  return request({
    url: '/user/bookmark',
    method: 'post',
    data: bookmark
  })
}

export function update(bookmark) {
  return request({
    url: '/user/bookmark',
    method: 'put',
    data: bookmark
  })
}


export function deleteById(id) {
  return request({
    url: '/user/bookmark/'+id,
    method: 'delete',
  })
}


export function selectById(id) {
  return request({
    url: '/user/bookmark/'+id,
    method: 'get',
  })
}

