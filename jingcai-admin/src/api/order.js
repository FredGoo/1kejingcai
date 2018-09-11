import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/order/list',
    method: 'post',
    data: params
  })
}

export function getListCount(params) {
  return request({
    url: '/order/listCount',
    method: 'post',
    data: params
  })
}

export function setOrderFinished(id) {
  return request({
    url: '/order/update',
    method: 'post',
    data: {
      nId: id,
      nStatus: 3
    }
  })
}
