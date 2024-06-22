import request from '@/utils/request'

export function like(bookmarkId) {
  return request({
    url: '/redis/like',
    method: 'post',
    params:{
      bookmarkId
    }
  })
}
export function deleteLike(bookmarkId) {
  return request({
    url: '/redis/deleteLike',
    method: 'post',
    params:{
      bookmarkId
    }
  })
}


export function disLike(bookmarkId) {
  return request({
    url: '/redis/disLike',
    method: 'post',
    params:{
      bookmarkId
    }
  })
}

export function deleteDisLike(bookmarkId) {
  return request({
    url: '/redis/deleteDisLike',
    method: 'post',
    params:{
      bookmarkId
    }
  })
}
export function getRedisRank() {
  return request({
    url: '/redis/getRedisRank',
    method: 'post',
  })
}

