import React from 'react'
import { Form, Modal, Input, Select } from 'antd'

export default Form.create()(
  class StudentModalForm extends React.Component {
    constructor(props) {
      super(props)
    }

    render() {
      const formItemLayout = {
        labelCol: { span: 6 },
        wrapperCol: { span: 16 },
      }
      const { form, isEdit, visible, data, onOk, onCancel, courseMap, classMap, userStateMap, stateMap } = this.props;
      const { getFieldDecorator } = form;
      let title = isEdit ? '学生编辑' : '学生新增';
      let okText = isEdit ? '修改' : '新增';
      return (
        <Modal
          visible={visible}
          title={title}
          okText={okText}
          onOk={onOk}
          onCancel={onCancel}
          maskClosable={false}
          >
          <Form>
            <Form.Item {...formItemLayout} label='用户名'>
              {
                getFieldDecorator('userId', {
                  rules: [{ required: true, message: '请输入用户名' }],
                  initialValue: isEdit ? data.userId : ''
                })(
                  <Input readonly={isEdit} />
                )
              }
            </Form.Item>
            <Form.Item {...formItemLayout} label='密码'>
              {
                getFieldDecorator('passwd', {
                  rules: [{ required: true, message: '请输入密码' }],
                  initialValue: isEdit ? data.passwd : ''
                })(
                  <Input />
                )
              }
            </Form.Item>
            <Form.Item {...formItemLayout} label='用户code'>
              {
                getFieldDecorator('userCode', {
                  initialValue: isEdit ? data.userCode : ''
                })(
                  <Input />
                )
              }
            </Form.Item>
            <Form.Item {...formItemLayout} label='别名'>
              {
                getFieldDecorator('nikeName', {
                  initialValue: isEdit ? data.nikeName : ''
                })(
                  <Input />
                )
              }
            </Form.Item>
            <Form.Item {...formItemLayout} label='关联课程'>
              {
                getFieldDecorator('lessonIds', {
                  initialValue: (isEdit&&data.lessonIds) ? (data.lessonIds).split(',') : []
                })(
                  <Select mode="multiple">
                    {
                      Object.keys(courseMap).map(item => {
                        let bookName = (courseMap[item]||{}).bookName
                        return <Select.Option key={item} value={item}>{bookName}</Select.Option>
                      })
                    }
                  </Select>
                )
              }
            </Form.Item>
            <Form.Item {...formItemLayout} label='当前班级'>
              {
                getFieldDecorator('currentClass', {
                  rules: [{ required: true, message: '请选择当前班级' }],
                  initialValue: isEdit ? data.currentClass : undefined
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
            <Form.Item {...formItemLayout} label='用户状态'>
              {
                getFieldDecorator('userState', {
                  rules: [{ required: true, message: '请选择用户状态' }],
                  initialValue: isEdit ? data.userState+'' : undefined
                })(
                  <Select>
                    {
                      Object.keys(userStateMap).map(item => {
                        return <Select.Option key={item+''} value={item+''}>{userStateMap[item]}</Select.Option>
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
                  initialValue: isEdit ? data.state+'' : undefined
                })(
                  <Select>
                    {
                      Object.keys(stateMap).map(item => {
                        return <Select.Option key={item+''} value={item+''}>{stateMap[item]}</Select.Option>
                      })
                    }
                  </Select>
                )
              }
            </Form.Item>
          </Form>
        </Modal>
      )
    }
  }
)