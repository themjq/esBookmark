import request from '@/utils/request'

export function findAll() {
  return request({
    url: '/admin/bm_type',
    method: 'get'
  })
}

export function add(bmType) {
  return request({
    url: '/admin/bm_type',
    method: 'post',
    data: bmType
  })
}

export function update(bmType) {
  return request({
    url: '/admin/bm_type',
    method: 'put',
    data: bmType
  })
}

export function deleteById(id) {
  return request({
    url: '/admin/bm_type/' + id,
    method: 'delete'
  })
}

export function selectById(id) {
  return request({
    url: '/admin/bm_type/' + id,
    method: 'get'
  })
}
