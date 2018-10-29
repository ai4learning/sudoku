import React from 'react'
import { Table, Card, Button, Divider, Modal, Tag} from 'antd'
import Panel from '@components/Panel'
import fetch from '@common/fetch'
import StudentModalForm from './module/StudentModalForm'
import BatchModalForm from './module/BatchModalForm'

export default class Student extends React.Component {
  constructor(props) {
    super(props)
  }

  userStateMap = {
    1: '初始化',
    2: '已激活',
    3: '不可用'
  }

  stateMap = {
    1: '有效',
    2: '无效'
  }

  state = {
    loading: false,
    isStudentModalFormVisible: false,
    isStudentModalFormEdit: false,
    isBatchModalFormVisible: false,
    list: [],
    studentItem: {},
    courseMap: {},
    classMap: {}
  }

  componentWillMount() {
    this.getClassData()
    this.getCourseData()
    this.getStudentData()
  }

  getStudentData = () => {
    this.setState({
      loading: true
    })
    fetch({
      url: '/api/Ajax/AjaxGetStudents',
      method: 'post',
      type: 'json'
    }).then(result => {
      this.setState({
        list: result.data || [],
        loading: false
      })
    })
  }

  getClassData = () => {
    fetch({
      url: '/api/Ajax/AjaxGetClassList',
      method: 'post',
      type: 'json'
    }).then(result => {
      let classMap = {}
      ;(result.data || []).forEach(item => {
        classMap[item.id] = item
      })
      this.setState({
        classMap: classMap
      })
    })
  }

  getCourseData = () => {
    fetch({
      url: '/api/Ajax/AjaxGetCourses',
      method: 'post',
      type: 'json'
    }).then(result => {
      let courseMap = {}
      ;(result.data || []).forEach(item => {
        courseMap[item.id] = item
      })
      this.setState({
        courseMap: courseMap
      })
    })
  }

  render() {
    return (
      <div className="page_teacher_student">
        <Panel title="学生管理">
          <Card
            title='学生列表'
            extra={
              <span>
                <Button type='primary' size='small' onClick={() => this.showStudentModalForm(false)}>学生新增</Button>
                <Button type='primary' size='small' onClick={this.showBatchModalForm} style={{marginLeft: '5px'}}>批量新增</Button>
              </span>
            }
            >
            <Table
              loading={this.state.loading}
              dataSource={this.state.list}
              columns={[
                {
                  title: '用户名',
                  dataIndex: 'userId',
                  key: 'userId'
                },
                {
                  title: '密码',
                  dataIndex: 'passwd',
                  key: 'passwd'
                },
                {
                  title: '用户code',
                  dataIndex: 'userCode',
                  key: 'userCode'
                },
                {
                  title: '别名',
                  dataIndex: 'nikeName',
                  key: 'nikeName'
                },
                {
                  title: '关联课程',
                  key: 'lessonIds',
                  dataIndex: 'lessonIds',
                  render: (text, record) => {
                    let lessonIds = (text||'').split(',')
                    return lessonIds.map(item => {
                      let bookName = (this.state.courseMap[item] || {}).bookName
                      return bookName && <Tag color='blue'>{bookName}</Tag>
                    })
                  }
                },
                {
                  title: '当前班级',
                  key: 'currentClass',
                  dataIndex: 'currentClass',
                  render: (text) => {
                    return (this.state.classMap[text]||{}).name
                  }
                },
                {
                  title: '用户状态',
                  key: 'userState',
                  dataIndex: 'userState',
                  render: (text, record) => {
                    return this.userStateMap[text]
                  }
                },
                {
                  title: '登录次数',
                  key: 'totalLoginTimes',
                  dataIndex: 'totalLoginTimes'
                },
                {
                  title: '状态',
                  key: 'state',
                  dataIndex: 'state',
                  render: (text) => {
                    return this.stateMap[text]
                  }
                },
                {
                  title: '操作',
                  key: 'actions',
                  render: (text, record) => {
                    return (
                      <span>
                        <a href='javascript:;' onClick={() => this.showStudentModalForm(true, record)}>编辑</a>
                        <Divider type='vertical'></Divider>
                        <a href='javascript:;' onClick={() => this.handleDeleteStudent(record)}>删除</a>
                      </span>
                    )
                  }
                }
              ]}
              >
            </Table>
          </Card>
          <StudentModalForm
            visible={this.state.isStudentModalFormVisible}
            isEdit={this.state.isStudentModalFormEdit}
            data={this.state.studentItem}
            onOk={() => this.handleStudentModalForm(true)}
            onCancel={() => this.handleStudentModalForm(false)}
            courseMap={this.state.courseMap}
            classMap={this.state.classMap}
            userStateMap={this.userStateMap}
            stateMap={this.stateMap}
            ref={this.studentModalFormRef}
            >
          </StudentModalForm>
          <BatchModalForm
            visible={this.state.isBatchModalFormVisible}
            onOk={() => this.handleBatchModalForm(true)}
            onCancel={() => this.handleBatchModalForm(false)}
            courseMap={this.state.courseMap}
            classMap={this.state.classMap}
            userStateMap={this.userStateMap}
            stateMap={this.stateMap}
            ref={this.batchModalFormRef}
            >
          </BatchModalForm>
        </Panel>
      </div>
    )
  }

  studentModalFormRef = (form) => {
    this.studentForm = form
  }

  showStudentModalForm = (isEdit, record) => {
    this.setState({
      isStudentModalFormEdit: isEdit,
      isStudentModalFormVisible: true,
      studentItem: record || {}
    })
  }

  handleStudentModalForm = (f) => {
    let hide = () => {
      this.setState({
        isStudentModalFormVisible: false
      })
      this.studentForm.resetFields()
    }
    if (!f) return hide()
    this.studentForm.validateFields((err, values) => {
      if (err) return
      values['lessonIds'] = (values['lessonIds']||[]).join(',')
      let url = ''
      let tip = ''
      if(this.state.isStudentModalFormEdit) {
        url = '/api/Ajax/AjaxUpdateStudent'
        tip = '修改成功'
      } else {
        url = '/api/Ajax/AjaxAddStudent'
        tip = '新增成功'
      }
      fetch({
        url: url,
        method: 'get',
        type: 'json',
        data: values
      }).then(() => {
        hide()
        message.success(tip)
        this.getStudentData()
      })
    })
  }

  handleDeleteStudent = (record) => {
    Modal.confirm({
      title: '学生删除',
      content: '确定要删除该学生？',
      onOk: () => {
        // TODO 删除
      }
    })
  }

  batchModalFormRef = (form) => {
    this.batchForm = form
  }

  showBatchModalForm = () => {
    this.setState({
      isBatchModalFormVisible: true
    })
  }

  handleBatchModalForm = (f) => {
    let hide = () => {
      this.setState({
        isBatchModalFormVisible: false
      })
    }
    if(!f) return hide()
    this.batchForm.validateFields((err, valuse) => {
      if(err) return
      // TODO 批量新增
      hide()
    })
  }
}