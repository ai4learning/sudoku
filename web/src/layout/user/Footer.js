import React, {Component} from 'react'

export default class Footer extends Component {
  constructor(props) {
    super(props)
  }

  render() {
    return (
      <div className="footer">
        <p>
          成都小金鱼信息科技有限公司 版权所有 备案号：
          <a href="http://www.miitbeian.gov.cn" target="_blank">川ICP备XXXXXXX号</a>
        </p>
      </div>
    )
  }
}