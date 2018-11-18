import React from 'react'
import { Table, Card, Button, Divider, Modal, Tag, message} from 'antd'
import Panel from '@components/Panel'
import fetch from '@common/fetch'
import StudentModalForm from './module/StudentModalForm'
import BatchStudentModalForm from './module/BatchStudentModalForm'
import StudentSearchForm from './module/StudentSearchForm'
import BatchCourseModalForm from './module/BatchCourseModalForm'

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
    isBatchStudentModalFormVisible: false,
    isBatchCourseModalFormVisible: false,
    list: [],
    studentItem: {},
    courseMap: {},
    classMap: {},
    searchData: {
      userId: '',
      currentClass: '',
      userState: '',
      state: ''
    }
  }

  componentWillMount() {
    this.getClassData()
    this.getCourseData()
    this.getStudentData()
  }

  getStudentData = (data = {}) => {
    let searchData = Object.assign({}, this.state.searchData, data)
    this.setState({
      loading: true
    })
    fetch({
      url: '/api/teacher/AjaxGetStudents',
      method: 'get',
      type: 'json',
      data: searchData
    }).then(result => {
      this.setState({
        list: result.data || [],
        loading: false
      })
    })
  }

  getClassData = () => {
    fetch({
      url: '/api/teacher/AjaxGetClassList',
      method: 'get',
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
      url: '/api/teacher/AjaxGetCourses',
      method: 'get',
      type: 'json',
      data: {
        bookName: ''
      }
    }).then(result => {
      let courseMap = {}
      ;(result.data || []).forEach(item => {
        courseMap[item.bookNumber] = item
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
          <Card title='查询条件'>
            <StudentSearchForm
              data={this.state.searchData}
              classMap={this.state.classMap}
              userStateMap={this.userStateMap}
              stateMap={this.stateMap}
              onSearch={this.getStudentData}
              >
            </StudentSearchForm>
          </Card>
          <Card
            title='学生列表'
            extra={
              <span>
                <Button type='primary' size='small' onClick={() => this.showStudentModalForm(false)}>学生新增</Button>
                <Button type='primary' size='small' onClick={this.showBatchStudentModalForm} style={{marginLeft: '5px'}}>批量新增</Button>
                <Button type='primary' size='small' onClick={this.showBatchCourseModalForm} style={{marginLeft: '5px'}}>批量关联</Button>
              </span>
            }
            >
            <Table
              loading={this.state.loading}
              dataSource={this.state.list}
              pagination={{ showTotal: (total) => `共 ${total} 条数据`, showQuickJumper: true, showSizeChanger: true, pageSizeOptions: [10, 20, 50, 100] }}
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
          <BatchStudentModalForm
            visible={this.state.isBatchStudentModalFormVisible}
            onOk={() => this.handleBatchStudentModalForm(true)}
            onCancel={() => this.handleBatchStudentModalForm(false)}
            courseMap={this.state.courseMap}
            classMap={this.state.classMap}
            userStateMap={this.userStateMap}
            stateMap={this.stateMap}
            ref={this.batchStudentModalRef}
            >
          </BatchStudentModalForm>
          <BatchCourseModalForm
            visible={this.state.isBatchCourseModalFormVisible}
            onOk={() => this.handleBatchCourseModalForm(true)}
            onCancel={() => this.handleBatchCourseModalForm(false)}
            courseMap={this.state.courseMap}
            classMap={this.state.classMap}
            ref={this.batchCourseModalRef}
            >
          </BatchCourseModalForm>
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
        url = '/api/teacher/AjaxUpdateStudent'
        tip = '修改成功'
      } else {
        url = '/api/teacher/AjaxAddStudent'
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

  batchStudentModalRef = (form) => {
    this.batchStudentForm = form
  }

  showBatchStudentModalForm = () => {
    this.setState({
      isBatchStudentModalFormVisible: true
    })
  }

  handleBatchStudentModalForm = (f) => {
    let hide = () => {
      this.setState({
        isBatchStudentModalFormVisible: false
      })
    }
    if(!f) return hide()
    this.batchStudentForm.validateFields((err, values) => {
      if(err) return
      fetch({
        url: '/api/teacher/AjaxBatchAddStudent',
        method: 'post',
        type: 'json',
        contentType: 'application/json',
        data: JSON.stringify(values)
      }).then(() => {
        hide()
        message.success('批量添加成功')
        this.getStudentData()
      })
    })
  }

  batchCourseModalRef = (form) => {
    this.batchCourseForm = form
  }

  showBatchCourseModalForm = () => {
    this.setState({
      isBatchCourseModalFormVisible: true
    })
  }

  handleBatchCourseModalForm = (f) => {
    let hide = () => {
      this.setState({
        isBatchCourseModalFormVisible: false
      })
    }
    if (!f) return hide()
    this.batchCourseForm.validateFields((err, values) => {
      if (err) return
      values['userIds'] = values['userIds'].join(',')
      values['lessonIds'] = values['lessonIds'].join(',')
      fetch({
        url: '/api/teacher/AjaxBatchAssignCourse',
        method: 'post',
        type: 'json',
        contentType: 'application/json',
        data: JSON.stringify(values)
      }).then(() => {
        hide()
        message.success('批量添加成功')
        this.getStudentData()
      })
    })
  }
}