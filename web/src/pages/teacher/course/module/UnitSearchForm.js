import React from 'react'
import {Form, Select, Button} from 'antd'

export default Form.create()(
  class UnitSearchForm extends React.Component {
    constructor(props) {
      super(props)
    }

    handleSearch = (e) => {
      e.preventDefault()
      this.props.form.validateFields((err, values) => {
        if(err) return
        this.props.onSearch(values)
      })
    }
    
    render() {
      let {form, courseMap, data} = this.props
      let {getFieldDecorator} = form
      return (
        <Form
          layout='inline'
          onSubmit={this.handleSearch}
          >
          <Form.Item label='课程'>
            {
              getFieldDecorator('moduleCode', {
                initialValue: data.moduleCode
              })(
                <Select>
                  {
                    Object.keys(courseMap).map(item => {
                      return <Select.Option value={item} key={item}>{courseMap[item].bookName}</Select.Option>
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