import React from 'react'
import { Form, Input, Button, Select } from 'antd'

export default Form.create()(
  class StudentSearchForm extends React.Component {
    constructor(props) {
      super(props)
    }

    handleSearch = (e) => {
      e.preventDefault()
      this.props.form.validateFields((err, values) => {
        if (err) return
        this.props.onSearch(values)
      })
    }

    render() {
      let { form, data, classMap, userStateMap, stateMap } = this.props
      let { getFieldDecorator } = form
      return (
        <Form
          layout='inline'
          onSubmit={this.handleSearch}
        >
          <Form.Item label='用户名'>
            {
              getFieldDecorator('userId', {
                initialValue: data.userId
              })(
                <Input />
              )
            }
          </Form.Item>
          <Form.Item label='当前班级'>
            {
              getFieldDecorator('currentClass', {
                initialValue: data.currentClass
              })(
                <Select>
                  <Select.Option key='' value=''>请选择...</Select.Option>
                  {
                    Object.keys(classMap).map(item => {
                      return <Select.Option key={item} value={item}>{classMap[item].name}</Select.Option>
                    })
                  }
                </Select>
              )
            }
          </Form.Item>
          <Form.Item label='用户状态'>
            {
              getFieldDecorator('userState', {
                initialValue: data.userState
              })(
                <Select>
                  <Select.Option key='' value=''>请选择...</Select.Option>
                  {
                    Object.keys(userStateMap).map(item => {
                      return <Select.Option key={item} value={item}>{userStateMap[item]}</Select.Option>
                    })
                  }
                </Select>
              )
            }
          </Form.Item>
          <Form.Item label='状态'>
            {
              getFieldDecorator('state', {
                initialValue: data.state
              })(
                <Select>
                  <Select.Option key='' value=''>请选择...</Select.Option>
                  {
                    Object.keys(stateMap).map(item => {
                      return <Select.Option key={item} value={item}>{stateMap[item]}</Select.Option>
                    })
                  }
                </Select>
              )
            }
          </Form.Item>
          <Form.Item>
            <Button type='primary' icon='search' shape='circle' htmlType='submit'></Button>
          </Form.Item>
        </Form>
      )
    }
  }
)