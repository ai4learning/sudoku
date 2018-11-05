import React from 'react'
import { Modal, Form, Input, Select } from 'antd'
import fetch from '@common/fetch'

export default Form.create()(
  class BatchCourseModalForm extends React.Component {
    constructor(props) {
      super(props)
    }

    state = {
      students: []
    }

    render() {
      const formItemLayout = {
        labelCol: { span: 6 },
        wrapperCol: { span: 16 },
      }
      const { form, visible, onOk, onCancel, courseMap, classMap } = this.props
      const { getFieldDecorator } = form
      return (
        <Modal
          visible={visible}
          title={'批量关联'}
          onOk={onOk}
          onCancel={onCancel}
          maskClosable={false}
        >
          <Form>
            <Form.Item {...formItemLayout} label='班级'>
              {
                getFieldDecorator('currentClass', {
                  rules: [{ required: true, message: '请选择班级' }],
                })(
                  <Select onChange={this.handleCurrentClassChange}>
                    {
                      Object.keys(classMap).map(item => {
                        return <Select.Option value={item} key={item}>{classMap[item].name}</Select.Option>
                      })
                    }
                  </Select>
                )
              }
            </Form.Item>
            <Form.Item {...formItemLayout} label='学生'>
              {
                getFieldDecorator('userIds', {
                })(
                  <Select mode='multiple' placeholder='不选则对该班级下所有学生关联课程'>
                    {
                      this.state.students.map(item => {
                        return <Select.Option key={item.userId} value={item.userId}>{item.userId}</Select.Option>
                      })
                    }
                  </Select>
                )
              }
            </Form.Item>
            <Form.Item {...formItemLayout} label='关联课程'>
              {
                getFieldDecorator('lessonIds', {
                  rules: [{ required: true, message: '请选择需要关联的课程' }],
                })(
                  <Select mode='multiple'>
                    {
                      Object.keys(courseMap).map(item => {
                        return <Select.Option value={item} key={item}>{courseMap[item].bookName}</Select.Option>
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

    handleCurrentClassChange = (value) => {
      let data = {
        currentClass: value
      }
      fetch({
        url: '/api/Ajax/AjaxGetStudents',
        method: 'post',
        type: 'json',
        contentType: 'application/json',
        data: JSON.stringify(data)
      }).then(result => {
        this.setState({
          students: result.data
        })
      })
    }
  }
)