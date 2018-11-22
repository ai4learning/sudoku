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
      unit: this.props.location.query.unit || 1
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
      url: '/api/teacher/AjaxGetUnit',
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
              pagination={{ showTotal: (total) => `共 ${total} 条数据`, showQuickJumper: true, showSizeChanger: true, pageSizeOptions: [10, 20, 50, 100] }}
              columns={[
                {
                  title: '编号',
                  key: 'Id',
                  dataIndex: 'Id'
                },
                {
                  title: '拼写',
                  key: 'spelling',
                  dataIndex: 'spelling'
                },
                {
                  title: '释义',
                  key: 'meaning',
                  dataIndex: 'meaning'
                },
                {
                  title: '发音',
                  key: 'soundMarkUs',
                  dataIndex: 'soundMarkUs'
                },
                {
                  title: '单词Code',
                  key: 'vocCode',
                  dataIndex: 'vocCode'
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