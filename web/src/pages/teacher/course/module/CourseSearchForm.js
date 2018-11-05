import React from 'react'
import { Form, Input, Button } from 'antd'

export default Form.create()(
  class CourseSearchForm extends React.Component {
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
      let { form, data } = this.props
      let { getFieldDecorator } = form
      return (
        <Form
          layout='inline'
          onSubmit={this.handleSearch}
        >
          <Form.Item label='课程名称'>
            {
              getFieldDecorator('bookName', {
                initialValue: data.bookName
              })(
                <Input />
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