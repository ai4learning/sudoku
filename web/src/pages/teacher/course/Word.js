import React from 'react'
import { Card, Table, Select } from 'antd'
import Panel from '@components/Panel'
import fetch from '@common/fetch'
import WordSearchForm from './module/WordSearchForm'

export default class Word extends React.Component {
  constructor(props) {
    super(props)
  }

  state = {
    loading: false,
    searchData: {
      moduleCode: this.props.location.query.moduleCode,
      unitNbr: this.props.location.query.unitNbr || 1
    },
    list: [],
    courseMap: {}
  }

  componentWillReceiveProps(nextProps) {
    if (this.state.moduleCode != nextProps.location.query.moduleCode) {
      this.setState({
        moduleCode: nextProps.moduleCode
      }, () => {
        this.getUnitData()
      })
    }
  }

  componentWillMount() {
    this.getCourseData()
    this.getUnitData()
  }

  getUnitData = (data = {}) => {
    let searchData = Object.assign({}, this.state.searchData, data)
    this.setState({
      loading: true
    })
    fetch({
      url: '/api/Ajax/AjaxLoadCourse',
      method: 'post',
      type: 'json',
      data: searchData
    }).then(result => {
      this.setState({
        list: (((result.data || {}.books || {})[0]) || {}).CourseUnits || [],
        loading: false
      })
    })
  }

  getCourseData = () => {
    fetch({
      url: '/api/Ajax/AjaxGetCourses',
      method: 'get',
      type: 'json'
    }).then(result => {
      let courseMap = {}
        ; (result.data || []).forEach(item => {
          courseMap[item.moduleCode] = item
        })
      this.setState({
        courseMap: courseMap
      })
    })
  }

  render() {
    return (
      <div class="page_teacher_unit">
        <Panel title='课程单词管理'>
          <Card title='查询条件'>
            <WordSearchForm
              courseMap={this.state.courseMap}
              data={this.state.searchData}
              onSearch={this.getUnitData}
            >
            </WordSearchForm>
          </Card>
          <Card title='课程单词列表'>
            <Table
              loading={this.state.loading}
              dataSource={this.state.list}
              columns={[
                {
                  title: '单词编号',
                  key: 'Id',
                  dataIndex: 'Id'
                }
              ]}
            >
            </Table>
          </Card>
        </Panel>
      </div>
    )
  }
}