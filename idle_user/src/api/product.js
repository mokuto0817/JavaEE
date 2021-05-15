import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/product/list',
    method: 'get',
    params
  })
}

export function create(data) {
  return request({
    url: '/product/save',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: '/product/updateById',
    method: 'put',
    data
  })
}

export function remove(data) {
  return request({
    url: '/product/removeById/' + data,
    method: 'delete',
  })
}
