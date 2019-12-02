import request from '@/utils/request'

export function get(id) {
  const url = '/article/get/' + id
  return request({
    url: url,
    method: 'get'
  })
}

export function publish(data) {
  return request({
    url: '/article/publish',
    method: 'post',
    data
  })
}

export function draft(data) {
  return request({
    url: '/article/draft',
    method: 'post',
    data
  })
}
