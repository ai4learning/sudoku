import React, {Component} from 'react'
import {Form, Radio, Button, Checkbox, Select} from 'antd'
import Panel from '@components/Panel'



class TestSelfForm extends Component {
  constructor(props) {
    super(props)
  }

  render() {
    const { getFieldDecorator } = this.props.form
    const questionTypes = [
      {
        label: '英译汉',
        value: '0',
      },
      {
        label: '汉译英',
        value: '1',
      },
      {
        label: '听选',
        value: '2',
      },
      {
        label: '听写',
        value: '3'
      }
    ]

    const formItemLayout = {
      labelCol: {span: 4},
      wrapperCol: {span: 16}
    }

    return (
      <Form onSubmit={this.handleSubmit.bind(this)}>
        <Form.Item label='测试内容' {...formItemLayout}>
          {
            getFieldDecorator('testArea', {
              rules: [{required: true, message: '请选择测试内容'}],
              initialValue: '0'
            })(
              <Radio.Group>
                <Radio.Button value='0'>生词测试</Radio.Button>
                <Radio.Button value='1'>熟词测试</Radio.Button>
              </Radio.Group>
            )
          }
        </Form.Item>
        <Form.Item label='题目类型' {...formItemLayout}>
          {
            getFieldDecorator('questionTypes', {
              rules: [{required: true, message: '请选择题目类型'}],
              initialValue: ['0', '1', '2']
            })(
              <Checkbox.Group options={questionTypes}></Checkbox.Group>
            )
          }
        </Form.Item>
        <Form.Item label='每组单词数' {...formItemLayout}>
          {
            getFieldDecorator('questionNbr', {
              rules: [{required: true, message: '请选择每组单词数'}],
              initialValue: '5'
            })(
              <Select>
                <Select.Option value='5'>5</Select.Option>
                <Select.Option value='10'>10</Select.Option>
                <Select.Option value='15'>15</Select.Option>
              </Select>
            )
          }
        </Form.Item>
        <Form.Item style={{textAlign: 'center'}}>
          <Button type='primary' htmlType='submit'>开始测试</Button>
        </Form.Item>
      </Form>
    )
  }

  handleSubmit(e) {
    e.preventDefault()
    this.props.form.validateFields((err, values) => {
      if(err) return
      window.open('/user/test/self?testArea='+values.testArea+'&questionTypes='+values.questionTypes.join(',')+'&questionNbr='+values.questionNbr)
    })
  }
}

const WrappedTestSelfForm = Form.create()(TestSelfForm)

export default class TestSelf extends Component {
  constructor(props) {
    super(props)
  }

  render() {
    return (
      <div className='test-self'>
        <Panel title='自主测试'>
          <WrappedTestSelfForm></WrappedTestSelfForm>
        </Panel>
      </div>
    )
  }
}