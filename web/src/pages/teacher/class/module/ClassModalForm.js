import React from 'react'
import {Form, Modal, Input, DatePicker} from 'antd'
import moment from 'moment'

class ClassModalForm extends React.Component {
  constructor(props) {
    super(props)
  }

  render() {
    const formItemLayout = {
      labelCol: { span: 6 },
      wrapperCol: { span: 16 },
    }
    const { form, isEdit, visible, data, onOk, onCancel } = this.props
    const { getFieldDecorator } = form
    let title = isEdit ? '班级编辑' : '班级新增'
    let okText = isEdit ? '修改' : '新增'
    let format = 'YYYY-MM-DD HH:mm:ss'
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
          {
            isEdit ?
            <Form.Item {...formItemLayout} label='班级编号'>
              {
                getFieldDecorator('id', {
                  initialValue: data.id
                })(
                  <Input disabled />
                )
              }
            </Form.Item> : null
          }
          <Form.Item {...formItemLayout} label='班级名称'>
            {
              getFieldDecorator('name', {
                rules: [{required: true, message: '请输入班级名称'}],
                initialValue: isEdit ? data.name : ''
              })(
                <Input />
              )
            }
          </Form.Item>
          <Form.Item {...formItemLayout} label='开始时间'>
            {
              getFieldDecorator('start', {
                rules: [{ required: true, message: '请选择开始时间' }],
                initialValue: isEdit ? moment(data.start, format) : ''
              })(
                <DatePicker showTime format={format} />
              )
            }
          </Form.Item>
          <Form.Item {...formItemLayout} label='结束时间'>
            {
              getFieldDecorator('end', {
                rules: [{ required: true, message: '请选择结束时间' }],
                initialValue: isEdit ? moment(data.end, "YYYY-MM-DD HH:mm:ss") : ''
              })(
                <DatePicker showTime format={format} />
              )
            }
          </Form.Item>
        </Form>
      </Modal>
    )
  }
}

export default Form.create()(ClassModalForm)