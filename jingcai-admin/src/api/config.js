import request from '@/utils/request'

export function getConfigByCategories(data) {
  return request({
    url: '/config/getConfigByCategories?categories=' + data,
    method: 'get'
  })
}

export function updateConfig(param) {
  return request({
    url: '/config/update',
    method: 'post',
    data: param
  })
}
