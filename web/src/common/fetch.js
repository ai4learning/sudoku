import reqwest from 'reqwest'

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
        reject(result)
      }
    }).fail(err => {
      console.error(err)
      reject(err)
    }).always(() => {
      requesting[uid] = false
    })
  })
}