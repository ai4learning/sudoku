import React, {Component} from 'react'
import {Form, Input, Button, Modal} from 'antd'
import Panel from '@components/Panel'
import fetch from '@common/fetch'

class ChangePwdForm extends Component {
  constructor(props) {
    super(props)
  }

  render() {
    const {getFieldDecorator} = this.props.form
    const formItemLayout = {
      labelCol: {span: 4},
      wrapperCol: {span: 16}
    }

    return (
      <Form style={{padding: '0 260px'}} onSubmit={this.handleSubmit.bind(this)}>
        <Form.Item label='旧密码' {...formItemLayout}>
          {
            getFieldDecorator('txtOldPassword', {
              rules: [{required: true, message: '请输入旧密码'}]
            })(
              <Input type='password' placeholder='请输入旧密码' />
            )
          }
        </Form.Item>
        <Form.Item label='新密码' {...formItemLayout}>
          {
            getFieldDecorator('txtNewPassword', {
              rules: [{required: true, message: '请至少输入6位字符或数字', min:6}]
            })(
              <Input type='password' placeholder='请至少输入6位字符或数字' />
            )
          }
        </Form.Item>
        <Form.Item label='确认密码' {...formItemLayout}>
          {
            getFieldDecorator('txtComPassword', {
              rules: [
                {
                  required: true,
                  message: '请至少输入6位字符或数字'
                },
                {
                  validator: this.checkComPwd.bind(this)
                }
              ]
            })(
              <Input type='password' placeholder='两次输入密码保持一致' />
            )
          }
        </Form.Item>
        <Form.Item style={{textAlign: 'center'}}>
          <Button type='primary' htmlType='submit'>修改密码</Button>
        </Form.Item>
      </Form>
    )
  }

  handleSubmit(e) {
    e.preventDefault()
    this.props.form.validateFields((err, values) => {
      if(err) return
      fetch({
        url: '/api/Ajax/AjaxUpdatePassword',
        method: 'POST',
        type: 'json',
        data: values
      }).then(() => {
        Modal.success({
          title: '修改密码成功~',
          onOk: () => {
            location.href = '/'
          }
        })
      })
    })
  }

  checkComPwd(rule, value, callback) {
    if(value && value != this.props.form.getFieldValue('txtNewPassword')) {
      callback('两次输入密码不一致')
    } else {
      callback()
    }
  }
}

const WrappedChangePwdForm = Form.create()(ChangePwdForm)

export default class ChangePwd extends Component {
  constructor(props) {
    super(props)
  }

  render() {
    return (
      <div className='page_change_pwd'>
        <Panel title='修改密码'>
          <WrappedChangePwdForm></WrappedChangePwdForm>
        </Panel>
      </div>
    )
  }
}