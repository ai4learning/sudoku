import React, {Component} from 'react'
import Panel from '@components/Panel'
import {Row, Col, Button} from 'antd'
import fetch from '@common/fetch'

export default class BaseInfo extends Component {
  constructor(props) {
    super(props)

    this.state = {
      userInfo: {}
    }
  }

  componentWillMount() {
    fetch({
      url: '/api/Ajax/AjaxGetUserInfo',
      method: 'post',
      type: 'json'
    }).then(data => {
      this.setState({
        userInfo: data
      })
    })
  }

  handleClick() {
    history.back()
  }

  render() {
    return (
      <div className='page_base_info'>
        <Panel title='基本信息'>
          <div className='base_info_body'>
            <Row>
              <Col span={8}>用户名：</Col>
              <Col span={16} style={{textAlign: 'right'}}>{this.state.userInfo.userId}</Col>
            </Row>
            <Row>
              <Col span={8}>真实姓名：</Col>
              <Col span={16} style={{ textAlign: 'right' }}>{this.state.userInfo.nickName}</Col>
            </Row>
            <Row>
              <Col span={8}>总登录次数：</Col>
              <Col span={16} style={{ textAlign: 'right' }}>{this.state.userInfo.totalLoginTimes}</Col>
            </Row>
            <Row>
              <Col span={8}>上次登录时间：</Col>
              <Col span={16} style={{ textAlign: 'right' }}>{this.state.userInfo.modified}</Col>
            </Row>
            <Row>
              <Col span={8}>注册时间：</Col>
              <Col span={16} style={{ textAlign: 'right' }}>{this.state.userInfo.created}</Col>
            </Row>
            <Row>
              <Col span={8}>用户状态：</Col>
              <Col span={16} style={{ textAlign: 'right' }}>正常</Col>
            </Row>
            <div style={{textAlign: 'center'}}><Button size='large' type='primary' onClick={this.handleClick.bind(this)}>确定</Button></div>
          </div>
        </Panel>
      </div>
    )
  }
}