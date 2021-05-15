import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/order/list',
    method: 'get',
    params
  })
}

export function createUser(data) {
  return request({
    url: '/order/save',
    method: 'post',
    data
  })
}

export function updateUser(data) {
  return request({
    url: '/order/updateById',
    method: 'put',
    data
  })
}

export function deleteUser(data) {
  return request({
    url: '/order/removeById/' + data,
    method: 'delete',
  })
}
