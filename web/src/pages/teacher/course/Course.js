import React from 'react'
import { Table, Card } from 'antd'
import Panel from '@components/Panel'
import fetch from '@common/fetch'

export default class Course extends React.Component {
  constructor(props) {
    super(props)
  }

  state = {
    list: [],
    loading: false
  }

  componentWillMount() {
    this.setState({
      loading: true
    })
    fetch({
      url: '/api/Ajax/AjaxGetCourses',
      method: 'post',
      type: 'json'
    }).then(result => {
      this.setState({
        list: result.data || [],
        loading: false
      })
    }).catch(() => {
      this.setState({
        loading: false
      })
    })
  }

  render() {
    return (
      <div className="page_teacher_course">
        <Panel title="课程管理">
          <Card title='课程列表'>
            <Table
              loading={this.state.loading}
              dataSource={this.state.list}
              columns={[
                {
                  title: '课程编号',
                  key: 'id',
                  dataIndex: 'id'
                },
                {
                  title: '课程名称',
                  key: 'bookName',
                  dataIndex: 'bookName'
                },
                {
                  title: '单元数',
                  key: 'totalUnitNbr',
                  dataIndex: 'totalUnitNbr',
                  render: (text, record) => {
                    return <a onClick={() => this.goToUnit(record)} href='javascript:;' style={{textDecoration: 'underline'}}>{text}</a>
                  }
                },
                {
                  title: '课程Code',
                  key: 'moduleCode',
                  dataIndex: 'moduleCode'
                }
              ]}
              >
            </Table>
          </Card>
        </Panel>
      </div>
    )
  }

  goToUnit = (record) => {
    this.props.router.push('/teacher/unit?moduleCode=' + record.moduleCode)
  }
}