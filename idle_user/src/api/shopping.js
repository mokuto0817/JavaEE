import request from '@/utils/request'

export function getList(params) {
  return request({
    url: '/shopping/list',
    method: 'get',
    params
  })
}

export function create(data) {
  return request({
    url: '/shopping/save',
    method: 'post',
    data
  })
}

export function update(data) {
  return request({
    url: '/shopping/updateById',
    method: 'put',
    data
  })
}

export function remove(data) {
  return request({
    url: '/shopping/removeById/' + data,
    method: 'delete',
  })
}
