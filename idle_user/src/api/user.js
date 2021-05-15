import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/user/login',
    method: 'post',
    data
  })
}

export function getList(params) {
  return request({
    url: '/user/list',
    method: 'get',
    params
  })
}

export function createUser(data) {
  return request({
    url: '/user/save',
    method: 'post',
    data
  })
}

export function updateUser(data) {
  return request({
    url: '/user/updateById',
    method: 'put',
    data
  })
}

export function deleteUser(data) {
  return request({
    url: '/user/removeById/' + data,
    method: 'delete',
  })
}
