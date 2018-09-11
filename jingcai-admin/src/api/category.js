import request from '@/utils/request'

export function getList() {
  return request({
    url: '/category/listAll',
    method: 'get'
  })
}

export function update(param) {
  return request({
    url: '/category/update',
    method: 'post',
    data: param
  })
}

export function deleteItem(id) {
  return request({
    url: '/category/delete',
    method: 'post',
    data: {nId: id}
  })
}
