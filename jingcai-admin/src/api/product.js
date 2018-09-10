import request from '@/utils/request'

export function getList() {
  return request({
    url: '/product/list',
    method: 'get'
  })
}

export function update(param) {
  return request({
    url: '/product/update',
    method: 'post',
    data: param
  })
}

export function deleteItem(id) {
  return request({
    url: '/product/delete',
    method: 'post',
    data: {nId: id}
  })
}
