import reqwest from 'reqwest'
import React from 'react'
import { Modal } from 'antd'

const requesting = {}

export default function fetch(options) {
  if(!options.url) {
    console.warn('请求url为空')
    return Promise.reject('请求url为空')
  }

  let uid = options.url + options.method + options.type + JSON.stringify(options.data)
  if(requesting[uid]) {
    console.warn('请勿重复请求')
    return Promise.reject('请勿重复请求')
  }
  requesting[uid] = true

  return new Promise((resolve, reject) => {
    reqwest(options).then(result => {
      if(result.success) {
        resolve(result)
      } else {
        if(result.condition == -1) {
          !options.noTip && Modal.info({
            title: '您还未登录或密码错误',
            okText: '确定',
            onOk: () => {
              location.href = "/#/user/login?redirect="+encodeURIComponent(location.href)
            }
          })
        } else {
          !options.noTip && Modal.error({
            title: (<p dangerouslySetInnerHTML={{ __html: result.msg || '服务器开小差啦，请稍后重试~' }}></p>)
          })
        }
        reject(result)
      }
    }).fail(err => {
      if(err.status == '403') {
        Modal.confirm({
          title: '暂无权限',
          content: '抱歉，您暂无权限访问此页面！',
          okText: '返回首页',
          cancelText: '退出登录',
          iconType: 'warning',
          onOk: () => {
            location.href = '/'
          },
          onCancel: () => {
            fetch({
              url: '/login/logout',
              method: 'post',
              type: 'json',
              noTip: true
            }).catch(() => {
              location.href = '/#/user/login'
            })
          }
        })
      } else {
        console.error('error: ', err)
        Modal.error({
          title: '未知错误，请稍后重试~'
        })
      }
      reject(err)
    }).always(() => {
      requesting[uid] = false
    })
  })
}