import React, {Component} from 'react'
import {Tabs} from 'antd'
import BaseInfo from './components/BaseInfo'
import ChangePwd from './components/ChangePwd'

export default class Center extends Component {
  constructor(props) {
    super(props)
    document.title = '用户中心'
  }

  render() {
    return (
      <div className='page_user_center'>
        <Tabs tabPosition='left' size='large'>
          <Tabs.TabPane key='baseinfo' tab='基本信息'>
            <BaseInfo></BaseInfo>
          </Tabs.TabPane>
          <Tabs.TabPane key='changepwd' tab='修改密码'>
            <ChangePwd></ChangePwd>
          </Tabs.TabPane>
        </Tabs>
      </div>
    )
  }
}