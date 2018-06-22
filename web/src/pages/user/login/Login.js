import React, {Component} from 'react'
import {Button, Form, Input, Icon, Card} from 'antd'
import fetch from '@common/fetch'
import {getQueryString} from '@common/util'

let router = null

class LoginForm extends Component {
  constructor(props) {
    super(props)
  }

  componentWillMount() {
    fetch({
      url: '/api/Ajax/AjaxGetUserInfo',
      method: 'post',
      type: 'json',
      noTip: true
    }).then(data => {
      if(data.success && data.userId) {
        location.href = '/#/user'
      }
    })
  }

  render() {
    const { getFieldDecorator } = this.props.form

    return (
      <Form onSubmit={this.handleSubmit.bind(this)} className='login_form'>
        <Form.Item>
          {
            getFieldDecorator('userName', {
              rules: [{ required: true, message: '请输入用户名' }],
            })(
              <Input size='large' prefix={<Icon type="user" style={{ color: 'rgba(0,0,0,.25)' }} />} placeholder="用户名" />
            )
          }
        </Form.Item>
        <Form.Item>
          {
            getFieldDecorator('password', {
              rules: [{ required: true, message: '请输入密码' }],
            })(
              <Input size='large' prefix={<Icon type="lock" style={{ color: 'rgba(0,0,0,.25)' }} />} type="password" placeholder="密码" />
            )
          }
        </Form.Item>
        <Form.Item>
          <Button size='large' type="primary" htmlType="submit" className="">登录</Button>
        </Form.Item>
      </Form>
    )
  }

  handleSubmit(e) {
    e.preventDefault()
    this.props.form.validateFields((err, values) => {
      if(err) return
      var redirect = getQueryString('redirect')
      fetch({
        url: '/login/doLogin',
        method: 'post',
        type: 'json',
        data: values
      }).then(() => {
        router.push(redirect || '/user')
      }).catch((e) => {
        console.log(e)
      })
    })
    
  }
}

const WrapperLoginForm = Form.create()(LoginForm)

export default class Login extends Component {
  constructor(props) {
    super(props)
    document.title = '登录'
    router = props.router
  }

  componentWillReceiveProps(nextProps) {
    router = nextProps.router
  }

  render() {
    return (
      <div class='page_user_login'>
        <Card title='登录' className='login_card'>
          <WrapperLoginForm></WrapperLoginForm>
        </Card>
      </div>
    )
  }
}

