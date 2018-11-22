import React from 'react'
import { Form, Select, Button } from 'antd'

export default Form.create()(
  class WordSearchForm extends React.Component {
    constructor(props) {
      super(props)
    }

    state = {
      moduleCode: this.props.data.moduleCode
    }

    handleSearch = (e) => {
      e.preventDefault()
      this.props.form.validateFields((err, values) => {
        if (err) return
        this.props.onSearch(values)
      })
    }

    render() {
      let { form, courseMap, data } = this.props
      let { getFieldDecorator } = form
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
                <Select onChange={this.handleModuleCodeChange}>
                  {
                    Object.keys(courseMap).map(item => {
                      return <Select.Option value={item} key={item}>{courseMap[item].bookName}</Select.Option>
                    })
                  }
                </Select>
              )
            }
          </Form.Item>
          <Form.Item label='单元'>
            {
              getFieldDecorator('unit', {
                initialValue: +data.unit
              })(
                <Select>
                  {
                    new Array((courseMap[this.state.moduleCode]||{}).totalUnitNbr).fill(0).map((item, index) => {
                      return <Select.Option value={index+1} key={index+1}>{'单元'+(index+1)}</Select.Option>
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

    handleModuleCodeChange = (value) => {
      this.setState({
        moduleCode: value
      })
    }
  }
)