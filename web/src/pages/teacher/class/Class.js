import React from 'react'
import {Table, Card, Divider, Button, Modal, message} from 'antd'
import Panel from '@components/Panel'
import ClassModalForm from './module/ClassModalForm'
import fetch from '@common/fetch'

export default class Class extends React.Component {
  constructor(props) {
    super(props)

    this.state = {
      list: [],
      loading: false,
      isClassModalFormVisible: false,
      isClassModalFormEdit: false,
      classItem: {}
    }
  }

  componentWillMount() {
    this.getClassData()
  }

  getClassData = () => {
    this.setState({
      loading: true
    })
    fetch({
      url: '/api/Ajax/AjaxGetClassList',
      method: 'post',
      type: 'json'
    }).then(result => {
      this.setState({
        list: result.data || [],
        loading: false
      })
    })
  }

  render() {
    return (
      <div className="page_teacher_class">
        <Panel title="班级管理">
          <Card title='班级列表' extra={<Button type='primary' size='small' onClick={() => this.showClassModalForm(false)}>班级新增</Button>}>
            <Table
              loading={this.state.loading}
              dataSource={this.state.list}
              pagination={{ showTotal: (total) => `共 ${total} 条数据`, showQuickJumper: true, showSizeChanger: true, pageSizeOptions: [10, 20, 50, 100]}}
              columns={[
                {
                  title: '班级编号',
                  dataIndex: 'id',
                  key: 'id'
                },
                {
                  title: '班级名称',
                  dataIndex: 'name',
                  key: 'name'
                },
                {
                  title: '开始时间',
                  dataIndex: 'start',
                  key: 'start'
                },
                {
                  title: '结束时间',
                  dataIndex: 'end',
                  key: 'end'
                },
                {
                  title: '创建时间',
                  dataIndex: 'created',
                  key: 'created'
                },
                {
                  title: '操作',
                  key: 'actions',
                  render: (text, record) => {
                    return (
                      <span>
                        <a href='javascript:;' onClick={() => this.showClassModalForm(true, record)}>编辑</a>
                        {/* <Divider type='vertical'></Divider>
                        <a href='javascript:;' onClick={() => this.handleDeleteClass(record)}>删除</a> */}
                      </span>
                    )
                  }
                }
              ]}
              >
            </Table>
          </Card>
          <ClassModalForm
            visible={this.state.isClassModalFormVisible}
            isEdit={this.state.isClassModalFormEdit}
            data={this.state.classItem}
            onOk={() => this.handleClassModalForm(true)}
            onCancel={() => this.handleClassModalForm(false)}
            ref={this.classModalFormRef}
            >
          </ClassModalForm>
        </Panel>
      </div>
    )
  }

  classModalFormRef = (form) => {
    this.classForm = form
  }

  showClassModalForm = (isEdit, record) => {
    this.setState({
      isClassModalFormEdit: isEdit,
      isClassModalFormVisible: true,
      classItem: record
    })
  }

  handleClassModalForm = (f) => {
    let hide = () => {
      this.setState({
        isClassModalFormVisible: false
      })
      this.classForm.resetFields()
    }
    if(!f) return hide()
    this.classForm.validateFields((err, values) => {
      if(err) return
      let format = "YYYY-MM-DD HH:mm:ss"
      values['start'] = values['start'].format(format)
      values['end'] = values['end'].format(format)
      let url = ''
      let tip = ''

      if(this.state.isClassModalFormEdit) {
        url = '/api/Ajax/AjaxUpdateClass'
        tip = '修改成功'
      } else {
        url = '/api/Ajax/AjaxAddClass'
        tip = '新增成功'
      }
      fetch({
        url: url,
        method: 'post',
        type: 'json',
        contentType: 'application/json',
        data: JSON.stringify(values)
        }).then(() => {
          hide()
          message.success(tip)
          this.getClassData()
        })
    })
  }

  handleDeleteClass = (record) => {
    Modal.confirm({
      title: '班级删除',
      content: '确定要删除该班级？',
      onOk: () => {
        fetch({
          url: '/api/Ajax/AjaxDeleteClass',
          method: 'post',
          type: 'json'
        })
      }
    })
  }
}