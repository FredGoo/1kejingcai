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

export function getProductByCategory(id) {
  return request({
    url: '/product/getProductByCategoryId',
    method: 'get',
    params: {
      categoryId: id
    }
  })
}

export function getAllProduct() {
  return request({
    url: '/product/list',
    method: 'get'
  })
}

export function saveProductAndCategoryRelation(data) {
  return request({
    url: '/category/relateCategoryList',
    method: 'post',
    data: data
  })
}
