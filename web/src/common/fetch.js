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
          Modal.info({
            title: '您还未登录，快去登录吧',
            okText: '确定',
            onOk: () => {
              location.href = "/#/user/login?redirect="+encodeURIComponent(location.href)
            }
          })
        } else {
          Modal.error({
            title: (<p dangerouslySetInnerHTML={{ __html: result.message || '服务器开小差啦，请稍后重试~' }}></p>)
          })
        }
        reject(result)
      }
    }).fail(err => {
      console.error('error: ', err)
      Modal.error({
        title: '未知错误，请稍后重试~'
      })
      reject(err)
    }).always(() => {
      requesting[uid] = false
    })
  })
}