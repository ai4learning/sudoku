import React from 'react'
import {Modal, Form, Input, Select} from 'antd'

export default Form.create()(
  class BatchStudentModalForm extends React.Component {
    constructor(props) {
      super(props)
    }
    
    render() {
      const formItemLayout = {
        labelCol: { span: 6 },
        wrapperCol: { span: 16 },
      }
      const { form, visible, onOk, onCancel, courseMap, classMap, userStateMap, stateMap } = this.props;
      const { getFieldDecorator } = form;
      return (
        <Modal
          visible={visible}
          title={'批量新增'}
          onOk={onOk}
          onCancel={onCancel}
          maskClosable={false}
          >
          <Form>
            <Form.Item {...formItemLayout} label='总数'>
              {
                getFieldDecorator('total', {
                  rules: [{ required: true, message: '请输入创建总数' }],
                })(
                  <Input type='number' />
                )
              }
            </Form.Item>
            <Form.Item {...formItemLayout} label='用户名前缀'>
              {
                getFieldDecorator('prefix', {
                  rules: [
                    {required: true, message: '请输入用户名前缀'},
                    {
                      validator: (rule, value, callback) => {
                        if(value.length > 20) {
                          callback('前缀不能超过20个字符')
                        } else {
                          callback()
                        }
                      }
                    }
                  ],
                })(
                  <Input />
                )
              }
            </Form.Item>
            <Form.Item {...formItemLayout} label='初始密码'>
              {
                getFieldDecorator('passwd', {
                  rules: [{ required: true, message: '请输入初始密码' }],
                })(
                  <Input />
                )
              }
            </Form.Item>
            <Form.Item {...formItemLayout} label='当前班级'>
              {
                getFieldDecorator('currentClass', {
                  rules: [{ required: true, message: '请选择当前班级' }],
                })(
                  <Select>
                    {
                      Object.keys(classMap).map(item => {
                        return <Select.Option key={item} value={item}>{classMap[item].name}</Select.Option>
                      })
                    }
                  </Select>
                )
              }
            </Form.Item>
            <Form.Item {...formItemLayout} label='关联课程'>
              {
                getFieldDecorator('lessonIds', {})(
                  <Select mode='multiple'>
                    {
                      Object.keys(courseMap).map(item => {
                        return <Select.Option key={item} value={item}>{courseMap[item].bookName}</Select.Option>
                      })
                    }
                  </Select>
                )
              }
            </Form.Item>
            {/* <Form.Item {...formItemLayout} label='用户状态'>
              {
                getFieldDecorator('userState', {
                  rules: [{ required: true, message: '请选择当前用户状态' }],
                })(
                  <Select>
                    {
                      Object.keys(userStateMap).map(item => {
                        return <Select.Option key={item} value={item}>{userStateMap[item]}</Select.Option>
                      })
                    }
                  </Select>
                )
              }
            </Form.Item>
            <Form.Item {...formItemLayout} label='状态'>
              {
                getFieldDecorator('state', {
                  rules: [{ required: true, message: '请选择状态' }],
                })(
                  <Select>
                    {
                      Object.keys(stateMap).map(item => {
                        return <Select.Option key={item}>{stateMap[item]}</Select.Option>
                      })
                    }
                  </Select>
                )
              }
            </Form.Item> */}
          </Form>
        </Modal>
      )
    }
  }
)